/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common.service;

import javax.ejb.Remote;

/**
 *
 * @author root
 */
@Remote
public interface ConnectionServiceRemote {

    void init();
    
}
