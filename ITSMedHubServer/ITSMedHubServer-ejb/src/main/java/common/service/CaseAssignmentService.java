/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import casemodule.dto.CaseUrgency;
import casemodule.dto.DataProcLogEntryType;
import casemodule.entity.TDSCase;
import casemodule.service.DataProcLogService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.repository.CaseStatusRepository;
import radiologistmodule.entity.ITSRadiologist;
import reportingmodule.entity.Reporting;
import reportingmodule.service.AutoFunctionLogService;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class CaseAssignmentService {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "caseStatusRepository")
    private CaseStatusRepository caseStatusRepository;
    @EJB(name = "dataProcLogService")
    private DataProcLogService dataProcLogService;
    @EJB(name = "autoFunctionLogService")
    private AutoFunctionLogService autoFunctionLogService;

    private CaseStatus getCaseStatusByName(String name) {
        String strJQL = "select cs from CaseStatus cs "
                + "where cs.englishName = :name";
        CaseStatus caseStatus = em.createQuery(strJQL, CaseStatus.class).setParameter("name", name).getSingleResult();
        return caseStatus;
    }

    public boolean assignCaseToRadiologistRandomly(TDSCase tdsCase) {
        boolean result = false;
        Random randomGenerator = new Random();
        randomGenerator.setSeed(new Date().getTime());
        System.out.println(randomGenerator.nextBoolean());
        //if (randomGenerator.nextBoolean()) {
        if (true) {
            Logger.getLogger(CaseAssignmentService.class.getName()).log(Level.INFO,
                    String.format("assignCaseToRadiologistRandomly %d", tdsCase.getId()));
            result = true;
            String strJQL = "select rad.id from ITSRadiologist rad";
            List<Long> radiologistIds = em.createQuery(strJQL, Long.class).getResultList();
            int radiologistId = randomGenerator.nextInt(radiologistIds.size());
            Reporting reporting = new Reporting();
            reporting.setTDSCase(tdsCase);
            reporting.setTDSRadiologist(new ITSRadiologist(radiologistIds.get(radiologistId)));
            reporting.setAssigned(new Date());
            reporting.setActive(true);
            reporting.setOpened(false);
            em.persist(reporting);

            //itt valami miatt null pointer exception dobódik, emiatt lett kikommentálva ez a rész
            //ezt meg kell még nézni.
//            dataProcLogService.saveEntry(
//                    tdsCase.getDataProcLog(),
//                    DataProcLogEntryType.InsertedToDatabase,
//                    "Reporting", null, reporting.getId().toString(), null, null);
//            //"Reporting [id]: " + reporting.getId());
//
//            dataProcLogService.saveEntry(
//                    tdsCase.getDataProcLog(),
//                    DataProcLogEntryType.AutomaticModification,
//                    "TDSCase", "caseStatus", tdsCase.getTdsCaseUniqueId(), tdsCase.getCaseStatus().getEnglishName(),
//                    "assigned");

            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("assigned"));
            tdsCase.setAcceptedAndAssigned(new Date());
            em.merge(tdsCase);
        } else {
            Date deadLine = tdsCase.getDeadLine();
            double normalCloseness = tdsCase.getHospitalContract().getNormalClosenessToDeadlineDaysHosp();
            Calendar threshold = Calendar.getInstance();
            threshold.setTime(deadLine);
            threshold.add(Calendar.HOUR, -(int) (normalCloseness * 24));
            Date now = new Date();
            if (now.after(threshold.getTime())) {
                result = true;
                dataProcLogService.saveEntry(
                        tdsCase.getDataProcLog(),
                        DataProcLogEntryType.AutomaticModification,
                        "TDSCase", "caseStatus", tdsCase.getTdsCaseUniqueId(), tdsCase.getCaseStatus().getEnglishName(),
                        "waiting - urgent");
                tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("waiting - urgent"));
                tdsCase.setUrgency(CaseUrgency.urgent.name());
                autoFunctionLogService.save("Basic");
                em.merge(tdsCase);
            }
        }
        return result;
    }

    public boolean assignCaseToRadiologistByAvailability(TDSCase tdsCase) {
        boolean result = false;
        Calendar today = Calendar.getInstance();
        int dayOfWeek = (today.get(Calendar.DAY_OF_WEEK) - 1);
        if (dayOfWeek == 1 || dayOfWeek == 6) {
            //weekend
        } else {
        }


        return result;
    }
}
