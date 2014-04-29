/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class QueryObject implements Serializable {

    private List<ITSCriteria> criteriaList;

    public QueryObject() {
        criteriaList = new ArrayList<ITSCriteria>();
    }

    public List<ITSCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<ITSCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }
}
