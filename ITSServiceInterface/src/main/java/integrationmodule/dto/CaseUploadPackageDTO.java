/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package integrationmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseUploadPackageDTO extends BaseDTO {

    public CaseUploadPackageDTO(long id) {
        super(id);
    }

    public CaseUploadPackageDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseUploadPackageDTO)) {
            return false;
        }
        return super.equals(obj);
    }


}
