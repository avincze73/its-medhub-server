/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.service;

import javax.ejb.Remote;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author tdsadmin
 */
@Remote
public interface CaseUploadServiceRemote {

    void startUpload();

    void commitUpload();

    void saveDicomDataSet(DicomDataSet dicomDataSet);
}
