/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;
import masterdatamodule.dto.ModalityDTO;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class WorkBandTableDTO extends BaseDTO {

    private HospitalContractDTO hospitalContract;
    private ModalityDTO modality;
    private int bandNumber;
    private List<BodyRegionWithBandDTO> bodyRegionWithBandList;
    private List<BandInfoDTO> bandInfoList;
    private transient ObservableList<BodyRegionWithBandDTO> obsBodyRegionWithBandList;
    private transient ObservableList<BandInfoDTO> obsBandInfoList;

    public WorkBandTableDTO(long id) {
        super(id);
        bodyRegionWithBandList = new ArrayList<BodyRegionWithBandDTO>();
        bandInfoList = new ArrayList<BandInfoDTO>();
        obsBodyRegionWithBandList = ObservableCollections.observableList(bodyRegionWithBandList);
        obsBandInfoList = ObservableCollections.observableList(bandInfoList);
    }

    public WorkBandTableDTO() {
        super();
        bodyRegionWithBandList = new ArrayList<BodyRegionWithBandDTO>();
        bandInfoList = new ArrayList<BandInfoDTO>();
        obsBodyRegionWithBandList = ObservableCollections.observableList(bodyRegionWithBandList);
        obsBandInfoList = ObservableCollections.observableList(bandInfoList);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WorkBandTableDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }

    public List<BodyRegionWithBandDTO> getBodyRegionWithBandList() {
        return bodyRegionWithBandList;
    }

    public void setBodyRegionWithBandList(List<BodyRegionWithBandDTO> bodyRegionWithBandList) {
        List<BodyRegionWithBandDTO> oldValue = this.bodyRegionWithBandList;
        this.bodyRegionWithBandList = bodyRegionWithBandList;
        propertyChangeSupport.firePropertyChange("bodyRegionWithBandList", oldValue, this.bodyRegionWithBandList);
        setObsBodyRegionWithBandList(ObservableCollections.observableList(bodyRegionWithBandList));
    }

    public int getBandNumber() {
        return bandNumber;
    }

    public void setBandNumber(int bandNumber) {
        int oldValue = this.bandNumber;
        this.bandNumber = bandNumber;
        propertyChangeSupport.firePropertyChange("bandNumber", oldValue, this.bandNumber);
    }

    public ObservableList<BandInfoDTO> getObsBandInfoList() {
        return obsBandInfoList;
    }

    public void setObsBandInfoList(ObservableList<BandInfoDTO> obsBandInfoList) {
        ObservableList<BandInfoDTO> oldValue = this.obsBandInfoList;
        this.obsBandInfoList = obsBandInfoList;
        propertyChangeSupport.firePropertyChange("obsBandInfoList", oldValue, this.obsBandInfoList);
        
    }

    public ObservableList<BodyRegionWithBandDTO> getObsBodyRegionWithBandList() {
        return obsBodyRegionWithBandList;
    }

    public void setObsBodyRegionWithBandList(ObservableList<BodyRegionWithBandDTO> obsBodyRegionWithBandList) {
        ObservableList<BodyRegionWithBandDTO> oldValue = this.obsBodyRegionWithBandList;
        this.obsBodyRegionWithBandList = obsBodyRegionWithBandList;
        propertyChangeSupport.firePropertyChange("obsBodyRegionWithBandList", oldValue, this.obsBodyRegionWithBandList);
    }

//
//    public double[] getBandPricesForNormalWork() {
//        return bandPricesForNormalWork;
//    }
//
//    public void setBandPricesForNormalWork(double[] bandPricesForNormalWork) {
//        double[] oldValue = this.bandPricesForNormalWork;
//        this.bandPricesForNormalWork = bandPricesForNormalWork;
//        propertyChangeSupport.firePropertyChange("bandPricesForNormalWork", oldValue,
//                this.bandPricesForNormalWork);
//    }
//
//    public byte[] getBandPricesForNormalWorkArray() throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(bandPricesForNormalWork);
//        return baos.toByteArray();
//    }
//
//    public void setBandPricesForNormalWorkArray(byte[] array) throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(
//                new ByteArrayInputStream(array));
//        bandPricesForNormalWork = (double[]) ois.readObject();
//    }
//
//    public double[] getBandWorkTimeMinutes() {
//        return bandWorkTimeMinutes;
//    }
//
//    public void setBandWorkTimeMinutes(double[] bandWorkTimeMinutes) {
//        double[] oldValue = this.bandWorkTimeMinutes;
//        this.bandWorkTimeMinutes = bandWorkTimeMinutes;
//        propertyChangeSupport.firePropertyChange("bandWorkTimeMinutes", oldValue,
//                this.bandWorkTimeMinutes);
//    }
//
//    public byte[] getBandWorkTimeMinutesArray() throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(bandWorkTimeMinutes);
//        return baos.toByteArray();
//    }
//
//    public void setBandWorkTimeMinutesArray(byte[] array) throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(
//                new ByteArrayInputStream(array));
//        bandWorkTimeMinutes = (double[]) ois.readObject();
//    }
//
//    public double[] getBandPricesForExtraWork() {
//        return bandPricesForExtraWork;
//    }
//
//    public void setBandPricesForExtraWork(double[] bandPricesForExtraWork) {
//        double[] oldValue = this.bandPricesForExtraWork;
//        this.bandPricesForExtraWork = bandPricesForExtraWork;
//        propertyChangeSupport.firePropertyChange("bandPricesForExtraWork", oldValue, this.bandWorkTimeMinutes);
//    }
//
//    public byte[] getBandPricesForExtraWorkArray() throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(bandPricesForExtraWork);
//        return baos.toByteArray();
//    }
//
//    public void setBandPricesForExtraWorkArray(byte[] array) throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(
//                new ByteArrayInputStream(array));
//        bandPricesForExtraWork = (double[]) ois.readObject();
//    }
//
//    public double[] getBandPricesForPayAsYouGoWork() {
//        return bandPricesForPayAsYouGoWork;
//    }
//
//    public void setBandPricesForPayAsYouGoWork(double[] bandPricesForPayAsYouGoWork) {
//        double[] oldValue = this.bandPricesForPayAsYouGoWork;
//        this.bandPricesForPayAsYouGoWork = bandPricesForPayAsYouGoWork;
//        propertyChangeSupport.firePropertyChange("bandPricesForPayAsYouGoWork", oldValue, this.bandPricesForPayAsYouGoWork);
//    }
//
//    public byte[] getBandPricesForPayAsYouGoWorkArray() throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(bandPricesForPayAsYouGoWork);
//        return baos.toByteArray();
//    }
//
//    public void setBandPricesForPayAsYouGoWorkArray(byte[] array) throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(
//                new ByteArrayInputStream(array));
//        bandPricesForPayAsYouGoWork = (double[]) ois.readObject();
//    }
    public ModalityDTO getModality() {
        return modality;
    }

    public void setModality(ModalityDTO modality) {
        ModalityDTO oldValue = this.modality;
        this.modality = modality;
        propertyChangeSupport.firePropertyChange("modality", oldValue, this.modality);
    }

    public List<BandInfoDTO> getBandInfoList() {
        return bandInfoList;
    }

    public void setBandInfoList(List<BandInfoDTO> bandInfoList) {
        List<BandInfoDTO> oldValue = this.bandInfoList;
        this.bandInfoList = bandInfoList;
        propertyChangeSupport.firePropertyChange("bandInfoList", oldValue, this.bandInfoList);
        setObsBandInfoList(ObservableCollections.observableList(bandInfoList));
    }
}
