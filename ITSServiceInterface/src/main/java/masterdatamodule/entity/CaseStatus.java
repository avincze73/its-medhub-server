/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "CaseStatus")
public class CaseStatus extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "englishName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String hungarianName;
    @Column(name = "itsManagerSeesEng")
    private String itsManagerSeesEng;
    @Column(name = "itsRadiologistSeesEng")
    private String itsRadiologistSeesEng;
    @Column(name = "hospitalSeesEng1")
    private String hospitalSeesEng1;
    @Column(name = "hospitalSeesEng2")
    private String hospitalSeesEng2;

    public CaseStatus() {
        this(null, null, null, null, null, null, null);
    }

    public CaseStatus(Long id) {
        this(id, null, null, null, null, null, null);
    }

    public CaseStatus(Long id, String englishName, String hungarianName,
            String itsManagerSeesEng, String itsRadiologistSeesEng,
            String hospitalSeesEng1, String hospitalSeesEng2) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.itsManagerSeesEng = itsManagerSeesEng;
        this.itsRadiologistSeesEng = itsRadiologistSeesEng;
        this.hospitalSeesEng1 = hospitalSeesEng1;
        this.hospitalSeesEng2 = hospitalSeesEng2;
    }

    public CaseStatus(Long id, String englishName, String hungarianName) {
        this(id, englishName, hungarianName, null, null, null, null);
    }

    public CaseStatus(String englishName, String hungarianName, String itsManagerSeesEng,
            String itsRadiologistSeesEng, String hospitalSeesEng1, String hospitalSeesEng2) {
        this(null, englishName, hungarianName, itsManagerSeesEng, itsRadiologistSeesEng, hospitalSeesEng1, hospitalSeesEng2);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        this.hungarianName = hungarianName;
    }

    public String getHospitalSeesEng1() {
        return hospitalSeesEng1;
    }

    public void setHospitalSeesEng1(String hospitalSeesEng1) {
        this.hospitalSeesEng1 = hospitalSeesEng1;
    }

    public String getHospitalSeesEng2() {
        return hospitalSeesEng2;
    }

    public void setHospitalSeesEng2(String hospitalSeesEng2) {
        this.hospitalSeesEng2 = hospitalSeesEng2;
    }

    public String getItsManagerSeesEng() {
        return itsManagerSeesEng;
    }

    public void setItsManagerSeesEng(String itsManagerSeesEng) {
        this.itsManagerSeesEng = itsManagerSeesEng;
    }

    public String getItsRadiologistSeesEng() {
        return itsRadiologistSeesEng;
    }

    public void setItsRadiologistSeesEng(String itsRadiologistSeesEng) {
        this.itsRadiologistSeesEng = itsRadiologistSeesEng;
    }

    @Override
    public CaseStatus clone() throws CloneNotSupportedException {
        CaseStatus result = (CaseStatus) super.clone();
        return result;
    }
}
