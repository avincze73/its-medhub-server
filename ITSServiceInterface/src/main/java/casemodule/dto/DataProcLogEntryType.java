/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

/**
 *
 * @author vincze.attila
 */
public enum DataProcLogEntryType {

    ErasedFromDatabase, InsertedToDatabase, AutomaticModification,
    UserModification, AnonimizedDisplayInWorkList, FullDisplayInWorkList,
    CaseOpenedViewer, UserComment, Downloading, CaseOpenedDataSheet
}
