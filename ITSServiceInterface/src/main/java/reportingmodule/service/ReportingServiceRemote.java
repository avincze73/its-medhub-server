/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reportingmodule.service;

import casemodule.dto.SeriesDTO;
import java.util.List;
import javax.ejb.Remote;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ReportingServiceRemote {

    long save(ReportingDTO reportingDTO);

    void done(ReportingDTO dto);

    void confirm(ReportingDTO dto, List<SeriesDTO> seriesList, String note);

    long save(CommentDTO dto);

    
}
