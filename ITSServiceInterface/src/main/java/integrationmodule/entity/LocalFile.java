/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import base.ITSEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "LocalFile")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "fileType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("local")
public class LocalFile extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "filePath")
    private String filePath;
    @Lob
    @Column(name = "fileContent")
    private byte[] fileContent;
    //@JoinColumn(name = "itsCasePackageId", referencedColumnName = "id")
    //@ManyToOne(optional = false)
    //private ITSCasePackage itsCasePackage;

    public LocalFile() {
        this(null, null);
    }

    public LocalFile(Long id) {
        this(id, null);
    }

    public LocalFile(String filePath) {
        this(null, filePath);
    }

    public LocalFile(Long id, String filePath ) {
        super(id);
        this.filePath = filePath;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public LocalFile clone() throws CloneNotSupportedException {
        LocalFile result = (LocalFile) super.clone();
        return result;
    }
//    public ITSCasePackage getItsCasePackage() {
//        return itsCasePackage;
//    }
//
//    public void setItsCasePackage(ITSCasePackage itsCasePackage) {
//        this.itsCasePackage = itsCasePackage;
//    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    
    
    

    
}
