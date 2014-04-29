/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.CaseDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface HcCaseServiceRemote {

    public List<CaseDTO> getCaseList();

    byte[] viewReport(long caseId);

    byte[] downloadReport(long caseId);
}
