/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.iview;

import base.IEditViewBase;
import base.IListEditViewBase;
import base.ITDSBaseView;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.dto.TDSRadiologistDTO;

/**
 *
 * @author vincze.attila
 */
public interface IRadiologistEditView extends IEditViewBase<TDSRadiologistDTO>, IListEditViewBase {

    void setTableSelectionIndex(int index);
}
