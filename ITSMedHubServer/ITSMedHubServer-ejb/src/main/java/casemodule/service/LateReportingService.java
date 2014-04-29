/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.entity.TDSCase;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.ITSConstant;
import reportingmodule.service.AutoFunctionLogService;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class LateReportingService {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "systemMessageService")
    private SystemMessageService systemMessageService;
    @EJB(name = "caseService")
    private CaseService caseService;
    @EJB(name = "autoFunctionLogService")
    private AutoFunctionLogService autoFunctionLogService;

    @Schedule(minute = "*/59", hour = "*/2")
    public void mildlyLate() {
        String strJQL = "select c from TDSCase c where c.caseStatus.englishName not like :ready";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("ready", "ready").getResultList();
        for (TDSCase tDSCase : caseList) {
            Date deadLine = tDSCase.getDeadLine();
            double normalCloseness = tDSCase.getHospitalContract().getNormalClosenessToDeadlineDaysHosp();
            Calendar threshold = Calendar.getInstance();
            threshold.setTime(deadLine);
            threshold.add(Calendar.HOUR, -(int) (normalCloseness * 24));
            Date now = new Date();
            if (now.after(threshold.getTime())) {
                systemMessageService.save(4, tDSCase.getId(), 0, "Mildly late reporting");
            }
        }
    }

    @Schedule(minute = "*/59", hour = "*/2")
    public void seriouslyLate() {
        String strJQL = "select c from TDSCase c where c.caseStatus.englishName not like :ready";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("ready", "ready").getResultList();
        for (TDSCase tDSCase : caseList) {
            Date deadLine = tDSCase.getDeadLine();
            double normalCloseness = tDSCase.getHospitalContract().getNormalClosenessToDeadlineDaysHosp() / 2;
            Calendar threshold = Calendar.getInstance();
            threshold.setTime(deadLine);
            threshold.add(Calendar.HOUR, -(int) (normalCloseness * 24));
            Date now = new Date();
            if (now.after(threshold.getTime())) {
                autoFunctionLogService.save("Scenario");
                systemMessageService.save(4, tDSCase.getId(), 0, "Seriously late reporting");
            }
        }
    }

    @Schedule(minute = "*/59", hour = "*/2")
    public void reportIsNotTransfered() {
        String strJQL = "select c from TDSCase c where c.caseStatus.englishName like :ready";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("ready", "ready").getResultList();
        for (TDSCase tDSCase : caseList) {

            if (tDSCase.getReportFirstDownloaded() != null) {
                continue;
            }
            if (tDSCase.getMadeReady() == null) {
                continue;
            }

            int days = em.find(ITSConstant.class, (long) 2).getConstantValue();
            Date readyDate = tDSCase.getMadeReady();
            Calendar threshold = Calendar.getInstance();
            threshold.setTime(readyDate);
            threshold.add(Calendar.HOUR, days * 24);
            Date now = new Date();
            if (now.after(threshold.getTime())) {
                autoFunctionLogService.save("Scenario");
                systemMessageService.save(4, tDSCase.getId(), 0, "Report is not transfered until set time");
            }
        }
    }

    @Schedule(minute = "*/59", hour = "*/2")
    public void madeReady() {
        String strJQL = "select c from TDSCase c where c.caseStatus.englishName like :confirm";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("confirm", "confirm").getResultList();
        for (TDSCase tDSCase : caseList) {

            Date confirmed = tDSCase.getConfirmedDone();
            Calendar threshold = Calendar.getInstance();
            threshold.setTime(confirmed);
            threshold.add(Calendar.HOUR, 2);
            Date now = new Date();
            if (now.after(threshold.getTime())) {
                caseService.setCaseStatus(tDSCase.getId(), "ready");
                if (tDSCase.getMadeReady() == null) {
                    tDSCase.setMadeReady(new Date());
                    autoFunctionLogService.save("Basic");
                    em.merge(tDSCase);
                }
            }
        }
    }
}
