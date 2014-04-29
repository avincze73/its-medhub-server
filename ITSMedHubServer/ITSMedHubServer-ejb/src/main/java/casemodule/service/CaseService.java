/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogEntryType;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.entity.DataProcLogEntry;
import casemodule.entity.DicomImage;
import casemodule.entity.ScenarioInstance;
import casemodule.entity.TDSCase;
import casemodule.repository.TDSCaseRepository;
import common.exception.AuthorizationFailedException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.repository.CaseStatusRepository;
import radiologistmodule.entity.ITSRadiologist;
import reportingmodule.entity.Reporting;
import reportingmodule.service.ReportingService;
import usermodule.service.UserServiceRemote;

/**
 * 
 * @author vincze.attila
 */
@LocalBean
@Stateless
public class CaseService implements CaseServiceRemote {

    @EJB(name = "caseRepository")
    private TDSCaseRepository caseRepository;
    @EJB(name = "userService")
    private UserServiceRemote userService;
    //@Resource(name = "mail/hssmed")
    //private Session mailhssmed;
    @Resource
    private SessionContext ctx;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "reportingService")
    private ReportingService reportingService;
    @EJB(name = "systemMessageService")
    private SystemMessageService systemMessageService;
    @EJB(name = "dataProcLogService")
    private DataProcLogService dataProcLogService;
    @EJB(name = "caseStatusRepository")
    private CaseStatusRepository caseStatusRepository;

    @Override
    public DicomImageDTO getDicomDataSet(long id) {
        DicomImage entity = em.find(DicomImage.class, id);
        DicomImageDTO dto = CaseAssembler.toDTO(entity);
        dto.setDicomDataSetArray(entity.getDicomDataSet());
        return dto;
    }

    @Override
    public DicomImageDTO getDicomDataSetIcon(long id) {
        DicomImage entity = em.find(DicomImage.class, id);
        DicomImageDTO dto = CaseAssembler.toDTO(entity);
        try {
            dto.setDicomDataSetIconArray(entity.getDicomDataSetIcon());
        } catch (IOException ex) {
            Logger.getLogger(CaseService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public List<CaseDTO> getCaseListByRadiologist(long id) {
        List<CaseDTO> ret = new ArrayList<CaseDTO>();
        String strJQL = "select c from TDSCase c, Reporting r "
                + "where c.id = r.tDSCase.id and r.tDSRadiologist.id = :id";
        List<TDSCase> resultList = em.createQuery(strJQL, TDSCase.class).setParameter("id", id).getResultList();
        for (TDSCase tDSCase : resultList) {
            ret.add(CaseAssembler.toDTO(tDSCase, true));
        }
        return ret;
    }

    @Override
    public List<CaseDTO> getCaseList() throws TooManyResultsException, ZeroResultException, AuthorizationFailedException {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();

//        if (!userService.canExecute("getCaseList")) {
//            throw new AuthorizationFailedException();
//        }

        List<TDSCase> items = em.createNamedQuery("TDSCase.findAll", TDSCase.class).getResultList();
        for (TDSCase item : items) {
            dtos.add(CaseAssembler.toDTO(item, true));

            dataProcLogService.saveEntry(
                    item.getDataProcLog().getId(),
                    (isOwner(item) ? DataProcLogEntryType.FullDisplayInWorkList : DataProcLogEntryType.AnonimizedDisplayInWorkList),
                    String.format("TDSCase: %d;User: %s", item.getId(), ctx.getCallerPrincipal().getName()));
        }
        return dtos;
    }

    private boolean isOwner(TDSCase entity) {
        boolean result = false;
        for (Reporting reporting : entity.getReportingCollection()) {
            if (reporting.getActive() && reporting.getTDSRadiologist().getItsUser().getLoginName().equals(ctx.getCallerPrincipal().getName())) {
                result = true;
            }
        }
        return result;
    }

    private CaseStatus getCaseStatusByName(String name) {
        String strJQL = "select cs from CaseStatus cs "
                + "where cs.englishName = :name";
        return em.createQuery(strJQL, CaseStatus.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public CaseDTO getCase(long id) {

        CaseDTO ret = null;
        TDSCase entity = em.find(TDSCase.class, id);
        if (entity.getOpened() == null) {
            dataProcLogService.saveEntry(entity.getDataProcLog(), DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", entity.getId().toString(), entity.getCaseStatus().getEnglishName(), "opened");
            entity.setOpened(new Date());
            entity.setCaseStatus(caseStatusRepository.findByEnglishName("opened"));
            em.merge(entity);
        }
        ret = CaseAssembler.toDTO(entity, false);

        String strJQL = "select di.id from DicomImage di "
                + "where di.series.tDSStudy.referralInfo.tDSCase.id = :id";

        List<Long> ids =
                em.createQuery(strJQL, Long.class).setParameter("id", id).getResultList();
        ret.setDicomImageIds(ids.toArray(new Long[0]));

//        if (entity.getCaseStatus().getEnglishName().equals("assigned")) {
//            DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
//            dataProcLogEntry.setEntryGeneration(new Date());
//            dataProcLogEntry.setEntryType(DataProcLogEntryType.AutomaticModification.name());
//            dataProcLogEntry.setEntry("TDSCase;caseStatus;"
//                    + entity.getCaseStatus().getEnglishName()
//                    + ";opened");
//            dataProcLogEntry.setDataProcLog(entity.getDataProcLog());
//            entity.setCaseStatus(getCaseStatusByName("opened"));
//            entity.setOpened(new Date());
//            em.merge(entity);
//            em.persist(dataProcLogEntry);
//        }
        dataProcLogService.saveEntry(entity.getDataProcLog(), DataProcLogEntryType.CaseOpenedDataSheet, null);
//        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
//        dataProcLogEntry.setEntryGeneration(new Date());
//        dataProcLogEntry.setEntryType(DataProcLogEntryType.CaseOpenedDataSheet.name());
//        dataProcLogEntry.setEntry(String.format("TDSCase: %d;User: %s", entity.getId(), "admin"));
//        dataProcLogEntry.setDataProcLog(entity.getDataProcLog());
//        em.persist(dataProcLogEntry);

        return ret;
    }

    @Override
    public void viewCase(long caseId) {
        TDSCase entity = em.createNamedQuery("TDSCase.findById", TDSCase.class).setParameter("id", caseId).getSingleResult();
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(DataProcLogEntryType.CaseOpenedViewer.name());
        dataProcLogEntry.setEntry(String.format("TDSCase: %d;User: %s", entity.getId(), "admin"));
        dataProcLogEntry.setDataProcLog(entity.getDataProcLog());
        em.persist(dataProcLogEntry);
        //sendMail("avincze@fotnet.hu", "tds", "hello from tds");

    }

    @Override
    public void saveReporting(long reportingId, String unfinishedTest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doneReporting(long reportingId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
//        MimeMessage message = new MimeMessage(mailhssmed);
//        message.setSubject(subject);
//        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
//        message.setText(body);
//        Transport.send(message);
    }

    @Override
    public long saveScenarioInstance(ScenarioInstanceDTO dto) {
        long res = dto.getId();
        ScenarioInstance entity = CaseAssembler.toEntity(dto);
        if (dto.getId() == 0) {
            em.persist(entity);
            res = entity.getId();
            if ("Urgent outcome of reporting".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if (dto.getScenario().getEnglishName().startsWith("Data problem")) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if ("Report is not transfered until set time".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                systemMessageService.save(3, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if ("Need to consult clinician".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                systemMessageService.save(3, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if ("One of the studies is not possible to evaluate".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if ("None of the studies are possible to evaluate".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                reportingService.reject(dto.getTdsCase().getId());
            } else if ("Reject case - not in my knowledge".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                reportingService.reject(dto.getTdsCase().getId());
            } else if ("Taking away a case from radiologist - it goes into the given waiting list".equals(dto.getScenario().getEnglishName())) {
                //systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                reportingService.taken(dto.getTdsCase().getId());
            } else if ("TDS rejects a case from the hospital".equals(dto.getScenario().getEnglishName())) {
                //systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
                reportingService.rejectedByTDS(dto.getTdsCase().getId());
            } else if ("Mildly late reporting".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            } else if ("Seriously late reporting".equals(dto.getScenario().getEnglishName())) {
                systemMessageService.save(4, dto.getTdsCase().getId(), 0, dto.getScenario().getEnglishName());
            }
        } else {
            em.merge(entity);
        }

        return res;
    }

    @Override
    public List<CaseDTO> getWaitingCaseList() {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.getWaitingCaseList();
        for (TDSCase tDSCase : entities) {
            dtos.add(CaseAssembler.toDTO(tDSCase, true));
        }
        return dtos;
    }

    @Override
    public void setCaseStatus(long caseId, String caseStatus) {
        TDSCase entity = caseRepository.find(new Long(caseId));
        String oldStatus = entity.getCaseStatus().getEnglishName();
        entity.setCaseStatus(caseStatusRepository.findByEnglishName(caseStatus));
        caseRepository.edit(entity);
        if (!caseStatus.equals(oldStatus)) {
            dataProcLogService.saveEntry(entity.getDataProcLog(), DataProcLogEntryType.UserModification,
                    "TDSCase", "caseStatus", Long.toString(entity.getId()), oldStatus, caseStatus);
        }

    }

    @Override
    public List<CaseDTO> getAssignedCases() {
        List<CaseDTO> result = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.findByCaseStatus("assigned");
        for (TDSCase tDSCase : entities) {
            result.add(CaseAssembler.toMcDTO(tDSCase));
        }
        return result;
    }

    @Override
    public List<CaseDTO> getNormalWaitingCases() {
        List<CaseDTO> result = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.findByCaseStatus("waiting - normal");
        for (TDSCase tDSCase : entities) {
            result.add(CaseAssembler.toMcDTO(tDSCase));
        }
        return result;
    }

    @Override
    public List<CaseDTO> getUrgentWaitingCases() {
        List<CaseDTO> result = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.findByCaseStatus("waiting - urgent");
        for (TDSCase tDSCase : entities) {
            result.add(CaseAssembler.toMcDTO(tDSCase));
        }
        return result;
    }

    @Override
    public void takenCase(long caseId) {
        //setCaseStatus(caseId, "waiting - normal");
        reportingService.taken(caseId);
    }

    @Override
    public void takenAwayCase(long caseId, long radiologistId) {
        reportingService.takenAwayCase(caseId, radiologistId);
//        Reporting reporting = new Reporting();
//        reporting.setTDSCase(new TDSCase(caseId));
//        reporting.setTDSRadiologist(new TDSRadiologist(radiologistId));
//        reporting.setAssigned(new Date());
//        reporting.setActive(true);
//        reporting.setOpened(false);
//        reporting.setUnfinishedText("");
//        em.persist(reporting);
//
//        TDSCase tdsCase = caseRepository.find(caseId);
//        dataProcLogService.saveEntry(
//                tdsCase.getDataProcLog(),
//                DataProcLogEntryType.InsertedToDatabase,
//                "Reporting", null, reporting.getId().toString(), null, null);
//
//        //tdsCase.setCaseStatus(getCaseStatusByName("assigned"));
//        setCaseStatus(caseId, "assigned");
//        //caseRepository.edit(tdsCase);
    }

    @Override
    public void assignCase(long caseId, long radiologistId) {
        Reporting reporting = new Reporting();
        reporting.setTDSCase(new TDSCase(caseId));
        reporting.setTDSRadiologist(new ITSRadiologist(radiologistId));
        reporting.setAssigned(new Date());
        reporting.setActive(true);
        reporting.setOpened(false);
        em.persist(reporting);

        //setCaseStatus(caseId, "assigned");

        TDSCase tdsCase = caseRepository.find(caseId);
        String oldStatus = tdsCase.getCaseStatus().getEnglishName();
        tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("assigned"));
        tdsCase.setAcceptedAndAssigned(new Date());
        caseRepository.edit(tdsCase);

        dataProcLogService.saveEntry(
                tdsCase.getDataProcLog(),
                DataProcLogEntryType.InsertedToDatabase,
                "Reporting", null, reporting.getId().toString(), null, null);

        dataProcLogService.saveEntry(
                tdsCase.getDataProcLog(),
                DataProcLogEntryType.AutomaticModification,
                "TDSCase", "caseStatus", Long.toString(tdsCase.getId()), oldStatus, "assigned");
    }

    @Override
    public String getReport(long caseId) {
        String ret = "";
        TDSCase tdsCase = caseRepository.find(caseId);

        return ret;
    }

    @Override
    public List<CaseDTO> getNotWaitingCases() {
        List<CaseDTO> result = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.getNotWaitingCaseList();
        for (TDSCase tDSCase : entities) {
            result.add(CaseAssembler.toMcDTO(tDSCase));
        }
        return result;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
