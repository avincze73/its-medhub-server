/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import casemodule.dto.DicomImageDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author vincze.attila
 */
@Entity
@DiscriminatorValue("dicom")
public class DicomFile extends LocalFile {

    //private DicomDataSet dicomDataSet;
    
    public DicomFile(Long id) {
        super(id);
    }

    public DicomFile() {
        super();
    }

    public DicomFile(String filePath) {
        super(filePath);
    }
    

    @Override
    public DicomFile clone() throws CloneNotSupportedException {
        DicomFile result = (DicomFile) super.clone();
        return result;
    }
    
    
    

    
}
