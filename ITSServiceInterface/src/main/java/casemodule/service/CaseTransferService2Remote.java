/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.ScannedPatientDataImageDTO;
import integrationmodule.entity.ITSCasePackage;
import integrationmodule.entity.PatientDataFile;
import integrationmodule.entity.ReferralFile;
import javax.ejb.Remote;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface CaseTransferService2Remote {

    void startCaseTransfer(ITSCasePackage itsCasePackage);

    void init();

    void commitCaseTransfer();
    
    void saveScannedReferralImage(ReferralFile image);
    
    void saveScannedPatientDataImage(PatientDataFile image);
    
    void saveDicomDataSet(DicomDataSet dicomDataSet);
}
