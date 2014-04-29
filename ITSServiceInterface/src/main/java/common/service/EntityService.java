/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import base.ITSEntity;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public interface EntityService<T extends ITSEntity> {

    List<T> getList();

    T save(T entity);

    T find(Long id);
}
