/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.helper;

/**
 *
 * @author vincze.attila
 */
public class SeriesGroup {

    private String study;
    private String modality;
    private String bodyRegion;
    private int seriesNumber;

    public SeriesGroup() {
    }

    public SeriesGroup(String study, String modality, String bodyRegion) {
        this.study = study;
        this.modality = modality;
        this.bodyRegion = bodyRegion;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SeriesGroup)) {
            return false;
        }
        SeriesGroup other = (SeriesGroup) obj;
        return this.bodyRegion.equals(other.getBodyRegion())
                && this.modality.equals(other.getModality())
                && this.study.equals(other.getStudy());
    }

    public String getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }
}
