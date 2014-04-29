/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.service;

import casemodule.dto.DataProcLogEntryType;
import casemodule.dto.SeriesDTO;
import casemodule.entity.TDSCase;
import casemodule.repository.TDSCaseRepository;
import casemodule.service.CaseService;
import casemodule.service.DataProcLogService;
import casemodule.service.SystemMessageService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.repository.CaseStatusRepository;
import masterdatamodule.repository.WayOfClosingRepository;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.entity.Comment;
import radiologistmodule.entity.ITSRadiologist;
import reportingmodule.assembler.ReportingAssembler;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;
import reportingmodule.entity.Reporting;
import reportingmodule.repository.ReportingRepository;

/**
 *
 * @author vincze.attila
 */
@LocalBean
@Stateless
public class ReportingService implements ReportingServiceRemote {

    @EJB(name = "systemMessageService")
    private SystemMessageService systemMessageService;
    @EJB(name = "caseService")
    private CaseService caseService;
    @EJB(name = "repository")
    private ReportingRepository repository;
    @EJB(name = "wayOfClosingRepository")
    private WayOfClosingRepository wayOfClosingRepository;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "dataProcLogService")
    private DataProcLogService dataProcLogService;
    @EJB(name = "caseRepository")
    private TDSCaseRepository caseRepository;
    @EJB(name = "caseStatusRepository")
    private CaseStatusRepository caseStatusRepository;
    @Resource
    private SessionContext ctx;

    @Override
    public long save(ReportingDTO dto) {
        Reporting item = ReportingAssembler.toEntity(dto);
        long res = dto.getId();
        TDSCase tdsCase = em.find(TDSCase.class, dto.getCaseItBelongsTo().getId());
        if (dto.getId() == 0) {
            repository.create(item);
            caseService.setCaseStatus(dto.getCaseItBelongsTo().getId(), "in progress");
            em.merge(tdsCase);
            res = item.getId();
        } else {
            Reporting old = em.find(Reporting.class, dto.getId());
            if (item.getTechnicalDetails() != old.getTechnicalDetails()) {
                dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                        "Reporting", "technicalDetails", Long.toString(dto.getId()), old.getTechnicalDetails(), item.getTechnicalDetails());
            }

            if (item.getMedicalHistory() != old.getMedicalHistory()) {
                dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                        "Reporting", "medicalHistory", Long.toString(dto.getId()), old.getMedicalHistory(), item.getMedicalHistory());
            }

            if (item.getDescription() != old.getDescription()) {
                dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                        "Reporting", "description", Long.toString(dto.getId()), old.getDescription(), item.getDescription());
            }

            if (item.getConclusion() != old.getConclusion()) {
                dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                        "Reporting", "conclusion", Long.toString(dto.getId()), old.getConclusion(), item.getConclusion());
            }

            repository.edit(item);
            if (tdsCase.getInProgress() == null) {
                dataProcLogService.saveEntry(
                        tdsCase.getDataProcLog(),
                        DataProcLogEntryType.AutomaticModification,
                        "TDSCase", "caseStatus", Long.toString(tdsCase.getId()), tdsCase.getCaseStatus().getEnglishName(),
                        "in progress");
                tdsCase.setInProgress(new Date());
                tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("in progress"));
                em.merge(tdsCase);
            }
        }

        return res;
    }

    @Override
    public void done(ReportingDTO dto) {
        Reporting old = em.find(Reporting.class, dto.getId());
        Reporting item = ReportingAssembler.toEntity(dto);
        TDSCase tdsCase = em.find(TDSCase.class, dto.getCaseItBelongsTo().getId());


        if (item.getTechnicalDetails() != old.getTechnicalDetails()) {
            dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                    "Reporting", "technicalDetails", Long.toString(dto.getId()), old.getTechnicalDetails(), item.getTechnicalDetails());
        }

        if (item.getMedicalHistory() != old.getMedicalHistory()) {
            dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                    "Reporting", "medicalHistory", Long.toString(dto.getId()), old.getMedicalHistory(), item.getMedicalHistory());
        }

        if (item.getDescription() != old.getDescription()) {
            dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                    "Reporting", "description", Long.toString(dto.getId()), old.getDescription(), item.getDescription());
        }

        if (item.getConclusion() != old.getConclusion()) {
            dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.UserModification,
                    "Reporting", "conclusion", Long.toString(dto.getId()), old.getConclusion(), item.getConclusion());
        }
        repository.edit(item);


        if (tdsCase.getDone() == null) {
            dataProcLogService.saveEntry(
                    tdsCase.getDataProcLog(),
                    DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", Long.toString(tdsCase.getId()), tdsCase.getCaseStatus().getEnglishName(),
                    "done");
            tdsCase.setDone(new Date());
            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("done"));
            em.merge(tdsCase);
        }
    }

    @Override
    public void confirm(ReportingDTO dto, List<SeriesDTO> seriesList, String note) {
        Reporting item = ReportingAssembler.toEntity(dto);
        TDSCase tdsCase = caseRepository.find(dto.getCaseItBelongsTo().getId());
        item.setFinishedAndSignedText(
                "Technical details\n" + item.getTechnicalDetails() + "\n\n"
                + "Past medical history & current clinical information\n" + item.getMedicalHistory() + "\n\n"
                + "Description\n" + item.getDescription() + "\n\n"
                + "Conclusion\n" + item.getConclusion());
        repository.edit(item);

        if (tdsCase.getConfirmedDone() == null) {
            dataProcLogService.saveEntry(
                    tdsCase.getDataProcLog(),
                    DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", Long.toString(tdsCase.getId()), tdsCase.getCaseStatus().getEnglishName(),
                    "done");
            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("confirmed"));
            tdsCase.setConfirmedDone(new Date());
            tdsCase.setFinalizedReport(item.getFinishedAndSignedText().getBytes());
        }


        //ez ideiglenes kód
        if (tdsCase.getMadeReady() == null) {
            dataProcLogService.saveEntry(
                    tdsCase.getDataProcLog(),
                    DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", Long.toString(tdsCase.getId()), tdsCase.getCaseStatus().getEnglishName(),
                    "ready");
            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("ready"));
            tdsCase.setMadeReady(new Date());
        }


//        Date deadLine = tdsCase.getDeadLine();
//        Calendar threshold = Calendar.getInstance();
//        threshold.setTime(deadLine);
//        threshold.add(Calendar.HOUR, -2);
//        Date now = new Date();
//        if (now.after(threshold.getTime())) {
//            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("ready"));
//            if (tdsCase.getMadeReady() == null) {
//                tdsCase.setMadeReady(new Date());
//            }
//        }
        caseRepository.edit(tdsCase);

//        for (SeriesDTO seriesDTO : seriesList) {
//            Series entity = em.find(Series.class, seriesDTO.getId());
//            BodyRegion bodyRegion = em.createNamedQuery("BodyRegion.findByEnglishName", BodyRegion.class).setParameter("englishName", seriesDTO.getRadiologistBodyRegion().getEnglishName()).getSingleResult();
//            entity.setBodyRegion(bodyRegion);
//            em.merge(entity);
//        }
//        dataProcLogService.saveEntry(tdsCase.getDataProcLog(), note);
//
    }

    public void reject(long caseId) {
        Reporting entity = repository.findActiveOfCase(caseId);
        entity.setWayOfClosing(wayOfClosingRepository.findSingleByName("rejected"));
        entity.setActive(false);
        repository.edit(entity);
    }

    public void taken(long caseId) {
        Reporting entity = repository.findActiveOfCase(caseId);
        System.out.println(entity.getTDSCase().getDataProcLog());
        dataProcLogService.saveEntry(entity.getTDSCase().getDataProcLog(), DataProcLogEntryType.UserModification,
                "Reporting", "wayOfClosing", Long.toString(entity.getId()), entity.getWayOfClosing() == null ? null : entity.getWayOfClosing().getName(), "taken");
        dataProcLogService.saveEntry(entity.getTDSCase().getDataProcLog(), DataProcLogEntryType.UserModification,
                "Reporting", "active", Long.toString(entity.getId()), Boolean.toString(true), Boolean.toString(false));
        entity.setWayOfClosing(wayOfClosingRepository.findSingleByName("taken"));
        entity.setActive(false);
        repository.edit(entity);
        caseService.setCaseStatus(caseId, "waiting - normal");
    }

    public void takenAwayCase(long caseId, long radiologistId) {
        Reporting entity = repository.findActiveOfCase(caseId);
        System.out.println(entity.getTDSCase().getDataProcLog());
        dataProcLogService.saveEntry(entity.getTDSCase().getDataProcLog(), DataProcLogEntryType.UserModification,
                "Reporting", "wayOfClosing", Long.toString(entity.getId()), entity.getWayOfClosing() == null ? null : entity.getWayOfClosing().getName(), "taken away");
        dataProcLogService.saveEntry(entity.getTDSCase().getDataProcLog(), DataProcLogEntryType.UserModification,
                "Reporting", "active", Long.toString(entity.getId()), Boolean.toString(true), Boolean.toString(false));
        entity.setWayOfClosing(wayOfClosingRepository.findSingleByName("taken away"));
        entity.setActive(false);
        repository.edit(entity);

        Reporting reporting = new Reporting();
        reporting.setTDSCase(new TDSCase(caseId));
        reporting.setTDSRadiologist(new ITSRadiologist(radiologistId));
        reporting.setAssigned(new Date());
        reporting.setActive(true);
        reporting.setOpened(false);
        em.persist(reporting);

        //TDSCase tdsCase = caseRepository.find(caseId);
        dataProcLogService.saveEntry(
                entity.getTDSCase().getDataProcLog(),
                DataProcLogEntryType.InsertedToDatabase,
                "Reporting", null, reporting.getId().toString(), null, null);

        //tdsCase.setCaseStatus(getCaseStatusByName("assigned"));
        caseService.setCaseStatus(caseId, "assigned");
        //caseRepository.edit(tdsCase);
    }

    public void rejectedByTDS(long caseId) {
        Reporting entity = repository.findActiveOfCase(caseId);
        entity.setWayOfClosing(wayOfClosingRepository.findSingleByName("rejected by TDS"));
        entity.setActive(false);
        repository.edit(entity);
        caseService.setCaseStatus(caseId, "rejected by TDS");
        TDSCase tdsCase = caseRepository.find(caseId);
        if (tdsCase.getRejectedByTDS() == null) {
            tdsCase.setRejectedByTDS(new Date());
            em.merge(tdsCase);
        }
    }

    @Override
    public long save(CommentDTO dto) {
        Long radiologistId =
                em.createQuery("SELECT r.id FROM TDSRadiologist r WHERE r.tDSUser.loginName like :loginName", Long.class)
                .setParameter("loginName", ctx.getCallerPrincipal().getName() ).getSingleResult();
        dto.setTdsRadiologist(new TDSRadiologistDTO(radiologistId));
        Comment item = ReportingAssembler.toEntity(dto);
        item.setAdded(new Date());
        item.setTitle(dto.getTitle() == null ? "" : dto.getTitle());
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
