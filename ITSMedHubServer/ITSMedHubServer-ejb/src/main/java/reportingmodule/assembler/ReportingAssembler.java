/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.assembler;

import casemodule.dto.CaseDTO;
import casemodule.entity.AutoFunctionLogEntry;
import casemodule.entity.TDSCase;
import masterdatamodule.dto.WayOfClosingDTO;
import masterdatamodule.entity.WayOfClosing;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.entity.Comment;
import radiologistmodule.entity.ITSRadiologist;
import reportingmodule.dto.AutoFunctionLogEntryDTO;
import reportingmodule.dto.AutoFunctionTypeDTO;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;
import reportingmodule.entity.Reporting;

/**
 *
 * @author vincze.attila
 */
public class ReportingAssembler {

    public static ReportingDTO toDTO(Reporting entity) {
        ReportingDTO dto = new ReportingDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setAssigned(entity.getAssigned());
        dto.setCaseItBelongsTo(new CaseDTO(entity.getTDSCase().getId()));
        dto.setDone(entity.getDone());
        dto.setDoneConfirmed(entity.getDoneConfirmed());
        dto.setFinishedAndSignedText(entity.getFinishedAndSignedText());
        dto.setOpened(entity.getOpened());
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        dto.setUnfinishedText(entity.getUnfinishedText());
        dto.setReady(entity.getReady());
        dto.setTechnicalDetails(entity.getTechnicalDetails());
        dto.setMedicalHistory(entity.getMedicalHistory());
        dto.setDescription(entity.getDescription());
        dto.setConclusion(entity.getConclusion());
        if (entity.getWayOfClosing() != null) {
            dto.setWayOfClosing(new WayOfClosingDTO(entity.getWayOfClosing().getId(), entity.getWayOfClosing().getName()));
        }
        return dto;
    }

    public static Reporting toEntity(ReportingDTO dto) {
        Reporting entity = new Reporting();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setActive(dto.isActive());
        entity.setAssigned(dto.getAssigned());
        entity.setDone(dto.getDone());
        entity.setDoneConfirmed(dto.getDoneConfirmed());
        entity.setFinishedAndSignedText(dto.getFinishedAndSignedText());
        entity.setOpened(dto.isOpened());
        entity.setTDSCase(new TDSCase(dto.getCaseItBelongsTo().getId()));
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        entity.setUnfinishedText(dto.getUnfinishedText());
        entity.setReady(dto.getReady());
        entity.setTechnicalDetails(dto.getTechnicalDetails());
        entity.setMedicalHistory(dto.getMedicalHistory());
        entity.setDescription(dto.getDescription());
        entity.setConclusion(dto.getConclusion());
        if (dto.getWayOfClosing() != null) {
            entity.setWayOfClosing(new WayOfClosing(dto.getWayOfClosing().getId(), dto.getWayOfClosing().getName()));
        }
        return entity;
    }

    public static AutoFunctionLogEntryDTO toDTO(AutoFunctionLogEntry entity) {
        AutoFunctionLogEntryDTO dto = new AutoFunctionLogEntryDTO(entity.getId());
        dto.setAdded(entity.getAdded());
        dto.setAutoFunction(
                new AutoFunctionTypeDTO(
                entity.getAutoFunctionType().getId(),
                entity.getAutoFunctionType().getName(),
                entity.getAutoFunctionType().getTypeClass()));
        return dto;
    }

    public static CommentDTO toDTO(Comment entity) {
        CommentDTO dto = new CommentDTO(entity.getId());
        dto.setAdded(entity.getAdded());
        dto.setTitle(entity.getTitle());
        dto.setFinishedAndSignedText(entity.getFinishedAndSignedText());
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        dto.setCaseItBelongsTo(new CaseDTO(entity.getTDSCase().getId()));
        return dto;
    }

    public static Comment toEntity(CommentDTO dto) {
        Comment entity = new Comment(dto.getId() == 0 ? null : dto.getId());
        entity.setAdded(dto.getAdded());
        entity.setTitle(dto.getTitle());
        entity.setFinishedAndSignedText(dto.getFinishedAndSignedText());
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        entity.setTDSCase(new TDSCase(dto.getCaseItBelongsTo().getId()));
        return entity;
    }
}
