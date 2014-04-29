/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;

/**
 *
 * @author vincze.attila
 */
public interface MasterDataService<T> {

    List<T> getList();

    long save(T dto);

    T find(long id);
}
