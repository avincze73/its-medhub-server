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
@DiscriminatorValue("referral")
public class ReferralFile extends LocalFile {

    public ReferralFile(Long id) {
        super(id);
    }

    public ReferralFile() {
        super();
    }

    public ReferralFile(String filePath) {
        super(filePath);
    }
    
    

    @Override
    public ReferralFile clone() throws CloneNotSupportedException {
        ReferralFile result = (ReferralFile) super.clone();
        return result;
    }

    
    
}
