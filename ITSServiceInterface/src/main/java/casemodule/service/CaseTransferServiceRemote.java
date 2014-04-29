/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.CaseDataDTO;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import integrationmodule.dto.HistoryStudyUploadPackageDTO;
import javax.ejb.Remote;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface CaseTransferServiceRemote {

    void startCaseTransfer(CaseDataDTO dto);

    void commitCaseTransfer(String[] ids);

    void saveScannedReferralImage(ScannedReferralImageDTO dto);

    void saveScannedPatientDataImage(ScannedPatientDataImageDTO dto);

    void saveDicomDataSet(DicomDataSet dicomDataSet);

    void saveHistoryStudy(HistoryStudyUploadPackageDTO dto);
}
