/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;

/**
 *
 * @author vincze.attila
 */
public interface IListEditViewBase {

    void setTableSelectionIndex(int index, Class entityClass);

    void newEntity(Class entityClass);

    void editEntity(Class entityClass);

    void saveEntity(Class entityClass);

    void cancelEntity(Class entityClass);

    Object getEntity(Class entityClass) throws ConstraintException;
}
