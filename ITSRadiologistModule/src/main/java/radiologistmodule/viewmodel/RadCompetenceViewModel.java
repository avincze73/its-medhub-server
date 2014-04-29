/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.IListEditViewBase;
import base.ITDSBaseView;
import base.ListEditViewModelBase;
import base.TDSListEditViewModelBase;
import common.exception.ConstraintException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.ModalityDTO;
import masterdatamodule.service.BodyRegionServiceRemote;
import masterdatamodule.service.ModalityServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadCompetenceServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class RadCompetenceViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected RadCompetenceDTO selectedEntity;
    protected RadCompetenceDTO newEntity;
    protected ObservableList<RadCompetenceDTO> entityList;
    private ObservableList<ModalityDTO> modalityList;
    private ObservableList<BodyRegionDTO> bodyRegionList;
    private ArrayList<String> bodyRegionEnglishNames;
    private String remoteServiceJndiName;
    private long tdsRadiologistId;
    private DefaultTableModel dtmRadCompetences;
    private Vector modalityColumns;
    private TDSRadiologistDTO radiologist;

    public RadCompetenceViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RadCompetenceServiceRemote";
        selectedEntity = new RadCompetenceDTO();
        entityList = ObservableCollections.observableList(new ArrayList<RadCompetenceDTO>());
        modalityList = ObservableCollections.observableList(new ArrayList<ModalityDTO>());
        bodyRegionList = ObservableCollections.observableList(new ArrayList<BodyRegionDTO>());
        loadModalities();
        loadBodyRegions();
        createTable();


    }

    private void createTable() {
        dtmRadCompetences = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Integer.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex == 0 || rowIndex == 0 || rowIndex == 1) ? false : true;
            }
        };
        modalityColumns = new Vector();
        modalityColumns.add("");
        for (ModalityDTO modalityDTO : modalityList) {
            modalityColumns.add(modalityDTO.getName());
        }
        dtmRadCompetences.setColumnIdentifiers(modalityColumns);

        for (BodyRegionDTO bodyRegionDTO : bodyRegionList) {
            ArrayList<Object> row = new ArrayList<Object>();
            row.add(bodyRegionDTO.getEnglishName());
            for (int i = 0; i < modalityList.size(); i++) {
                row.add(null);
            }
            dtmRadCompetences.addRow(row.toArray());
        }


    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(RadCompetenceDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(RadCompetenceDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(RadCompetenceDTO.class);
    }

    @Override
    protected void doSaveAction() {


        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            for (int rowNumber = 0; rowNumber < dtmRadCompetences.getRowCount(); rowNumber++) {
                for (int columnNumber = 1; columnNumber < dtmRadCompetences.getColumnCount(); columnNumber++) {
                    if (dtmRadCompetences.getValueAt(rowNumber, columnNumber) != null) {
                        String bodyRegion = (String) dtmRadCompetences.getValueAt(rowNumber, 0);
                        String modality = (String) modalityColumns.get(columnNumber);
                        Integer competence = (Integer) dtmRadCompetences.getValueAt(rowNumber, columnNumber);
                        RadCompetenceDTO dto = new RadCompetenceDTO();
                        dto.setModality(new ModalityDTO(modality));
                        dto.setBodyRegion(new BodyRegionDTO(bodyRegion, "", "", ""));
                        dto.setCompetenceLevel(competence);
                        dto.setTdsRadiologist(new TDSRadiologistDTO(tdsRadiologistId));
                        RadCompetenceServiceRemote service =
                                (RadCompetenceServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
                        long id = service.save(dto);
                    }
                }
            }
            view.saveEntity(RadCompetenceDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (NamingException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadModalities() {
        try {
            ModalityServiceRemote modalityService = (ModalityServiceRemote) new InitialContext().lookup("masterdatamodule.service.ModalityServiceRemote");
            List<ModalityDTO> res = modalityService.findAll();
            modalityList.addAll(res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadBodyRegions() {
        try {
            BodyRegionServiceRemote bodyRegionService = (BodyRegionServiceRemote) new InitialContext().lookup("masterdatamodule.service.BodyRegionServiceRemote");
            List<BodyRegionDTO> res = bodyRegionService.findAll();
            bodyRegionList.addAll(res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RadCompetenceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<BodyRegionDTO> getBodyRegionList() {
        return bodyRegionList;
    }

    public void setBodyRegionList(ObservableList<BodyRegionDTO> bodyRegionList) {
        this.bodyRegionList = bodyRegionList;
    }

    public ObservableList<ModalityDTO> getModalityList() {
        return modalityList;
    }

    public void setModalityList(ObservableList<ModalityDTO> modalityList) {
        this.modalityList = modalityList;
    }

    public ObservableList<RadCompetenceDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<RadCompetenceDTO> entityList) {
        ObservableList<RadCompetenceDTO> oldValue = this.entityList;
        this.entityList = entityList;
        propertyChangeSupport.firePropertyChange("entityList", oldValue, this.entityList);

        for (RadCompetenceDTO radCompetenceDTO : entityList) {
            for (int i = 0; i < dtmRadCompetences.getRowCount(); i++) {
                if (radCompetenceDTO.getBodyRegion().getEnglishName().equals(dtmRadCompetences.getValueAt(i, 0))) {
                    int index = modalityColumns.indexOf(radCompetenceDTO.getModality().getName());
                    dtmRadCompetences.setValueAt(radCompetenceDTO.getCompetenceLevel(), i, index);
                }
            }
        }
    }

    public RadCompetenceDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(RadCompetenceDTO newEntity) {
        RadCompetenceDTO oldValue = this.newEntity;
        this.newEntity = newEntity;
        propertyChangeSupport.firePropertyChange("newEntity", oldValue, this.newEntity);
    }

    public RadCompetenceDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(RadCompetenceDTO selectedEntity) {
        RadCompetenceDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }

    public DefaultTableModel getDtmRadCompetences() {
        return dtmRadCompetences;
    }

    public void setDtmRadCompetences(DefaultTableModel dtmRadCompetences) {
        this.dtmRadCompetences = dtmRadCompetences;
    }

    public TDSRadiologistDTO getRadiologist() {
        return radiologist;
    }

    public void setRadiologist(TDSRadiologistDTO radiologist) {
        this.radiologist = radiologist;
    }
}
