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
@Table(name = "BodyRegion")
public class BodyRegion extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "englishName")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String hungarianName;
    @Column(name = "dicomName")
    private String dicomName;
    @Column(name = "snomedCode")
    private String snomedCode;
    @Column(name = "groupNumber")
    private Integer groupNumber;

    public BodyRegion() {
        this(null, null, null, null, null);
    }

    public BodyRegion(Long id) {
        this(id, null, null, null, null);
    }

    public BodyRegion(Long id, String englishName, String hungarianName) {
        this(id, englishName, hungarianName, null, null);
    }

    public BodyRegion(Long id, String englishName, String hungarianName,
            String dicomName, String snomedCode) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.dicomName = dicomName;
        this.snomedCode = snomedCode;
    }

    public BodyRegion(String englishName, String hungarianName,
            String dicomName, String snomedCode) {
        this(null, englishName, hungarianName, dicomName, snomedCode);
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

    public String getDicomName() {
        return dicomName;
    }

    public void setDicomName(String dicomName) {
        this.dicomName = dicomName;
    }

    public String getSnomedCode() {
        return snomedCode;
    }

    public void setSnomedCode(String snomedCode) {
        this.snomedCode = snomedCode;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public BodyRegion clone() throws CloneNotSupportedException {
        BodyRegion result = (BodyRegion) super.clone();
        return result;
    }
}
