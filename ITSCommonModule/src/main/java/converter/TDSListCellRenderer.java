/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import casemodule.dto.SystemMessageTypeDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalDTO;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.dto.ContractTypeDTO;
import masterdatamodule.dto.CountryDTO;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.GoverningAuthorityDTO;
import masterdatamodule.dto.HospContrOptionDTO;
import masterdatamodule.dto.ModalityDTO;
import masterdatamodule.dto.RegLicQualDTO;
import masterdatamodule.dto.RegLicQualTypeDTO;
import masterdatamodule.dto.RegionDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import usermodule.dto.TDSManagerRoleDTO;
import usermodule.dto.TDSRadiologistRoleDTO;
import usermodule.dto.UserDTO;

/**
 *
 * @author vincze.attila
 */
public class TDSListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component cell = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof CaseStatusDTO) {
            CaseStatusDTO c = (CaseStatusDTO) value;
            if (c != null) {
                setText(c.getEnglishName());
            }
        } else if (value instanceof UserDTO) {
            UserDTO c = (UserDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof SystemMessageTypeDTO) {
            SystemMessageTypeDTO c = (SystemMessageTypeDTO) value;
            if (c != null) {
                setText(c.getMessageType());
            }
        } else if (value instanceof TDSRadiologistRoleDTO) {
            TDSRadiologistRoleDTO c = (TDSRadiologistRoleDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof TDSManagerRoleDTO) {
            TDSManagerRoleDTO c = (TDSManagerRoleDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof ModalityDTO) {
            ModalityDTO c = (ModalityDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof BodyRegionDTO) {
            BodyRegionDTO c = (BodyRegionDTO) value;
            if (c != null) {
                setText(c.getEnglishName());
            }
        } else if (value instanceof RegLicQualDTO) {
            RegLicQualDTO c = (RegLicQualDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof TDSRadiologistDTO) {
            TDSRadiologistDTO c = (TDSRadiologistDTO) value;
            if (c != null) {
                setText(c.getUserInfo().getName());
            }
        } else if (value instanceof CompanyDTO) {
            CompanyDTO c = (CompanyDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof CountryDTO) {
            CountryDTO c = (CountryDTO) value;
            if (c != null) {
                setText(c.getEnglishName());
            }
        } else if (value instanceof HospitalDTO) {
            HospitalDTO c = (HospitalDTO) value;
            if (c != null) {
                setText(c.getAbbrevName());
            }
        } else if (value instanceof ContactPersonDTO) {
            ContactPersonDTO c = (ContactPersonDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof RegionDTO) {
            RegionDTO c = (RegionDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof GoverningAuthorityDTO) {
            GoverningAuthorityDTO c = (GoverningAuthorityDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof HospContrOptionDTO) {
            HospContrOptionDTO c = (HospContrOptionDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof ContractTypeDTO) {
            ContractTypeDTO c = (ContractTypeDTO) value;
            if (c != null) {
                setText(c.getEnglishName());
            }
        } else if (value instanceof CurrencyDTO) {
            CurrencyDTO c = (CurrencyDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof RegLicQualTypeDTO) {
            RegLicQualTypeDTO c = (RegLicQualTypeDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } else if (value instanceof TDSManagerRoleDTO) {
            TDSManagerRoleDTO c = (TDSManagerRoleDTO) value;
            if (c != null) {
                setText(c.getName());
            }
        } 

        return this;
    }
}
