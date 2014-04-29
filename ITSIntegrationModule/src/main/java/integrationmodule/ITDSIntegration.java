/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule;

import casemodule.dto.CaseDataDTO;
import java.io.File;

/**
 *
 * @author vincze.attila
 */
public interface ITDSIntegration {

    CaseDataDTO createCaseDataDTO();
    File[] getDicomFilePaths();
    File[] getScannedPatientDataPaths();
    File[] getScannedReferralPaths();
    long getCaseSize();
}
