/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import common.RemoteMessage;

/**
 *
 * @author itsadmin
 */
public interface IMasterDataServiceRemoteBase {

    RemoteMessage getList(RemoteMessage input);

    RemoteMessage save(RemoteMessage input);

    RemoteMessage find(RemoteMessage input);
    
    void delete(RemoteMessage input);
}
