/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.gws.service;

import integrationmodule.entity.ITSCasePackage;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ITSCasePackageServiceRemote {

    void upload(ITSCasePackage itsCasePackage);

    ITSCasePackage getUploadableITSCasePackage(String clientMacAddress);
    
}
