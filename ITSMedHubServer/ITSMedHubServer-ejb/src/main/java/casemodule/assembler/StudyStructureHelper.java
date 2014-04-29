/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.assembler;

import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class StudyStructureHelper {

    public String accessionNumber;
    public Date studyDate;
    public String modality;
    public String bodyRegion;

    public StudyStructureHelper(String accessionNumber, Date studyDate, String modality, String bodyRegion) {
        this.accessionNumber = accessionNumber;
        this.studyDate = studyDate;
        this.modality = modality;
        this.bodyRegion = bodyRegion;
    }

    @Override
    public int hashCode() {
        return (accessionNumber + studyDate + modality + bodyRegion).hashCode();
    }
}
