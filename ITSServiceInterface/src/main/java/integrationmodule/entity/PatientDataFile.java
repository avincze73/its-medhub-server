/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author vincze.attila
 */
@Entity
@DiscriminatorValue("patientData")
public class PatientDataFile extends LocalFile {



    public PatientDataFile(Long id) {
        super(id);
    }

    public PatientDataFile() {
        super();
    }

    public PatientDataFile(String filePath) {
        super(filePath);
    }
    
    

    @Override
    public PatientDataFile clone() throws CloneNotSupportedException {
        PatientDataFile result = (PatientDataFile) super.clone();
        return result;
    }

    
}
