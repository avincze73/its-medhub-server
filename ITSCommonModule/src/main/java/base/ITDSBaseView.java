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
public interface ITDSBaseView<T> {

    void setTableSelectionIndex(int index);

    void FindEntities();
    
    void NewEntity();

    void EditEntity();

    void SaveEntity();

    void CancelEntity();

    T GetEntity() throws ConstraintException;

}
