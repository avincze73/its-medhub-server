/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import base.ITSEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSCasePackage")
public class ITSCasePackage extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "nonDicomCaseIdInHospital")
    private String nonDicomCaseIdInHospital;
    @Column(name = "nonDicomCaseIdInHospital1Name")
    private String nonDicomCaseIdInHospital1Name;
    @Column(name = "nonDicomCaseIdInHospital2Name")
    private String nonDicomCaseIdInHospital2Name;
    @Column(name = "nonDicomCaseIdInHospital1ShortName")
    private String nonDicomCaseIdInHospital1ShortName;
    @Column(name = "nonDicomCaseIdInHospital2ShortName")
    private String nonDicomCaseIdInHospital2ShortName;
    @Column(name = "nonDicomCaseIdInHospital1Value")
    private String nonDicomCaseIdInHospital1Value;
    @Column(name = "nonDicomCaseIdInHospital2Value")
    private String nonDicomCaseIdInHospital2Value;
    @Column(name = "clientHostMacAddress")
    private String clientHostMacAddress;
    @Column(name = "uploading")
    private boolean uploading;
    @Column(name = "uploaded")
    private boolean uploaded;
    @Column(name = "uploadStarted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadStarted;
    @Column(name = "uploadEnded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadEnded;
    @Column(name = "transfering")
    private boolean transfering;
    @Column(name = "transfered")
    private boolean transfered;
    @Column(name = "transferStarted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferStarted;
    @Column(name = "transferEnded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferEnded;
    @Column(name = "loginName")
    private String loginName;
    @JoinColumn(name = "electronicPatientDataPackageId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ElectronicPatientDataPackage electronicPatientDataPackage;
    @JoinColumn(name = "electronicReferralPackageId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ElectronicReferralPackage electronicReferralPackage;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "itsCasePackage")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "itsCasePackageId")
    private List<ReferralFile> scannedReferralImageList;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "itsCasePackage")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "itsCasePackageId")
    private List<DicomFile> dicomImageList;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "itsCasePackage")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "itsCasePackageId")
    private List<PatientDataFile> scannedPatientDataImageList;

    public ITSCasePackage() {
        this(null);
    }

    public ITSCasePackage(Long id) {
        super(id);
    }

    @Override
    public ITSCasePackage clone() throws CloneNotSupportedException {
        ITSCasePackage result = (ITSCasePackage) super.clone();
        return result;
    }

    public String getNonDicomCaseIdInHospital() {
        return nonDicomCaseIdInHospital;
    }

    public void setNonDicomCaseIdInHospital(String nonDicomCaseIdInHospital) {
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
    }

    public String getNonDicomCaseIdInHospital1Name() {
        return nonDicomCaseIdInHospital1Name;
    }

    public void setNonDicomCaseIdInHospital1Name(String nonDicomCaseIdInHospital1Name) {
        this.nonDicomCaseIdInHospital1Name = nonDicomCaseIdInHospital1Name;
    }

    public String getNonDicomCaseIdInHospital2Name() {
        return nonDicomCaseIdInHospital2Name;
    }

    public void setNonDicomCaseIdInHospital2Name(String nonDicomCaseIdInHospital2Name) {
        this.nonDicomCaseIdInHospital2Name = nonDicomCaseIdInHospital2Name;
    }

    public String getNonDicomCaseIdInHospital1ShortName() {
        return nonDicomCaseIdInHospital1ShortName;
    }

    public void setNonDicomCaseIdInHospital1ShortName(String nonDicomCaseIdInHospital1ShortName) {
        this.nonDicomCaseIdInHospital1ShortName = nonDicomCaseIdInHospital1ShortName;
    }

    public String getNonDicomCaseIdInHospital2ShortName() {
        return nonDicomCaseIdInHospital2ShortName;
    }

    public void setNonDicomCaseIdInHospital2ShortName(String nonDicomCaseIdInHospital2ShortName) {
        this.nonDicomCaseIdInHospital2ShortName = nonDicomCaseIdInHospital2ShortName;
    }

    public String getNonDicomCaseIdInHospital1Value() {
        return nonDicomCaseIdInHospital1Value;
    }

    public void setNonDicomCaseIdInHospital1Value(String nonDicomCaseIdInHospital1Value) {
        this.nonDicomCaseIdInHospital1Value = nonDicomCaseIdInHospital1Value;
    }

    public String getNonDicomCaseIdInHospital2Value() {
        return nonDicomCaseIdInHospital2Value;
    }

    public void setNonDicomCaseIdInHospital2Value(String nonDicomCaseIdInHospital2Value) {
        this.nonDicomCaseIdInHospital2Value = nonDicomCaseIdInHospital2Value;
    }

    public ElectronicPatientDataPackage getElectronicPatientDataPackage() {
        return electronicPatientDataPackage;
    }

    public void setElectronicPatientDataPackage(ElectronicPatientDataPackage electronicPatientDataPackage) {
        this.electronicPatientDataPackage = electronicPatientDataPackage;
    }

    public ElectronicReferralPackage getElectronicReferralPackage() {
        return electronicReferralPackage;
    }

    public void setElectronicReferralPackage(ElectronicReferralPackage electronicReferralPackage) {
        this.electronicReferralPackage = electronicReferralPackage;
    }

    public List<ReferralFile> getScannedReferralImageList() {
        return scannedReferralImageList;
    }

    public void setScannedReferralImageList(List<ReferralFile> scannedReferralImageList) {
        this.scannedReferralImageList = scannedReferralImageList;
    }

    public List<DicomFile> getDicomImageList() {
        return dicomImageList;
    }

    public void setDicomImageList(List<DicomFile> dicomImageList) {
        this.dicomImageList = dicomImageList;
    }

    public List<PatientDataFile> getScannedPatientDataImageList() {
        return scannedPatientDataImageList;
    }

    public void setScannedPatientDataImageList(List<PatientDataFile> scannedPatientDataImageList) {
        this.scannedPatientDataImageList = scannedPatientDataImageList;
    }

    public String getClientHostMacAddress() {
        return clientHostMacAddress;
    }

    public void setClientHostMacAddress(String clientHostMacAddress) {
        this.clientHostMacAddress = clientHostMacAddress;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public boolean isUploading() {
        return uploading;
    }

    public void setUploading(boolean uploading) {
        this.uploading = uploading;
    }

    public Date getUploadStarted() {
        return uploadStarted;
    }

    public void setUploadStarted(Date uploadStarted) {
        this.uploadStarted = uploadStarted;
    }

    public Date getUploadEnded() {
        return uploadEnded;
    }

    public void setUploadEnded(Date uploadEnded) {
        this.uploadEnded = uploadEnded;
    }

    public boolean isTransfering() {
        return transfering;
    }

    public void setTransfering(boolean transfering) {
        this.transfering = transfering;
    }

    public boolean isTransfered() {
        return transfered;
    }

    public void setTransfered(boolean transfered) {
        this.transfered = transfered;
    }

    public Date getTransferStarted() {
        return transferStarted;
    }

    public void setTransferStarted(Date transferStarted) {
        this.transferStarted = transferStarted;
    }

    public Date getTransferEnded() {
        return transferEnded;
    }

    public void setTransferEnded(Date transferEnded) {
        this.transferEnded = transferEnded;
    }
    
    
}
