/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.dto;

import base.BaseDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class PaxStudyDTO extends BaseDTO {

    private String studyInstanceUID;
    private String patientID;
    private Date studyDateTime;
    private List<String> seriesInstanceUidList;

    public PaxStudyDTO(long id) {
        super(id);
    }

    public PaxStudyDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PaxStudyDTO)) {
            return false;
        }
        return super.equals(obj);
    }
}
