/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.gws.service;

import javax.ejb.Remote;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ITSCasePackageUploadServiceRemote {

    void startTransfer(Long itsCasePackageId);

    void commitTransfer();

    void saveReferralFileContent(Long id, byte[] fileContent);

    void savePatientDataFileContent(Long id, byte[] fileContent);

    void saveDicomFileContent(Long id, byte[] fileContent);
    
    void saveDicomDataSet(Long id, DicomDataSet dicomDataSet);
}
