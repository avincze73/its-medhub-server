/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import javax.swing.event.EventListenerList;

/**
 *
 * @author vincze.attila
 */
public class ITSEventManager {

    private final static EventListenerList eventListenerList;

    static {
        eventListenerList = new EventListenerList();
    }

    public static EventListenerList getEventListenerList() {
        return eventListenerList;
    }

    public static void fireEditHospitalEvent(EditHospitalEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditHospitalEventListener.class) {
                ((EditHospitalEventListener) listeners[i + 1]).editHospitalEventOccured(evt);
            }
        }
    }

    public static void fireEditHospitalContractEvent(EditHospitalContractEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditHospitalContractEventListener.class) {
                ((EditHospitalContractEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEditHospitalStaffEvent(EditHospitalStaffEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditHospitalStaffEventListener.class) {
                ((EditHospitalStaffEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEditRadiologistEvent(EditRadiologistEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditRadiologistEventListener.class) {
                ((EditRadiologistEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireShowEmptyViewEvent(ShowEmptyViewEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ShowEmptyViewEventListener.class) {
                ((ShowEmptyViewEventListener) listeners[i + 1]).showEmptyViewEventOccured(evt);
            }
        }
    }

    public static void fireSuccessfulLoginEvent(SuccessfulLoginEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SuccessfulLoginEventListener.class) {
                ((SuccessfulLoginEventListener) listeners[i + 1]).successfulLoginEventOccured(evt);
            }
        }
    }

    public static void fireModifyHospitalTitleEvent(ModifyHospitalTitleEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ModifyHospitalTitleEventListener.class) {
                ((ModifyHospitalTitleEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireCloseActiveViewEvent(CloseActiveViewEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CloseActiveViewEventListener.class) {
                ((CloseActiveViewEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireUpdateHospitalInListEvent(UpdateHospitalInListEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateHospitalInListEventListener.class) {
                ((UpdateHospitalInListEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireUpdateHospitalContractInListEvent(UpdateHospitalContractInListEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateHospitalContractInListEventListener.class) {
                ((UpdateHospitalContractInListEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireUpdateHospitalStaffInListEvent(UpdateHospitalStaffInListEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateHospitalStaffInListEventListener.class) {
                ((UpdateHospitalStaffInListEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireUpdateRadiologistInListEvent(UpdateRadiologistInListEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateRadiologistInListEventListener.class) {
                ((UpdateRadiologistInListEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireOpenCaseEvent(OpenCaseEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == OpenCaseEventListener.class) {
                ((OpenCaseEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireDicomDataSetDownloadedEvent(DicomDataSetDownloadedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DicomDataSetDownloadedEventListener.class) {
                ((DicomDataSetDownloadedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireModifyCaseTitleEvent(ModifyCaseTitleEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ModifyCaseTitleEventListener.class) {
                ((ModifyCaseTitleEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireEditCaseEvent(EditCaseEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditCaseEventListener.class) {
                ((EditCaseEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireEditTDSManagerEvent(EditTDSManagerEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == EditTDSManagerEventListener.class) {
                ((EditTDSManagerEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireShowProgressMessageEvent(ShowProgressMessageEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ShowProgressMessageEventListener.class) {
                ((ShowProgressMessageEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireHideProgressMessageEvent(HideProgressMessageEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == HideProgressMessageEventListener.class) {
                ((HideProgressMessageEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireStartScenarioEvent(StartScenarioEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == StartScenarioEventListener.class) {
                ((StartScenarioEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static synchronized void fireDeactivateScenarioEvent(DeactivateScenarioEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DeactivateScenarioEventListener.class) {
                ((DeactivateScenarioEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(CaseStatusModifiedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CaseStatusModifiedEventListener.class) {
                ((CaseStatusModifiedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(UpdateTDSManagerInList evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateTDSManagerInListListener.class) {
                ((UpdateTDSManagerInListListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(FileUploadingEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == FileUploadingEventListener.class) {
                ((FileUploadingEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(RejectCaseEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RejectCaseEventListener.class) {
                ((RejectCaseEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(NavigateBackEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == NavigateBackEventListener.class) {
                ((NavigateBackEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(ViewUserSessionsEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ViewUserSessionsEventListener.class) {
                ((ViewUserSessionsEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(UpdateDetailTitleEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == UpdateDetailTitleEventListener.class) {
                ((UpdateDetailTitleEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(ShowWaitingCursorEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ShowWaitingCursorEventListener.class) {
                ((ShowWaitingCursorEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(RefreshFrameContentEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshFrameContentEventListener.class) {
                ((RefreshFrameContentEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(RefreshHospitalStaffEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == RefreshHospitalStaffEventListener.class) {
                ((RefreshHospitalStaffEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(CloseFrameEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CloseFrameEventListener.class) {
                ((CloseFrameEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(CaseUploadStartedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CaseUploadStartedEventListener.class) {
                ((CaseUploadStartedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(CaseUploadFinishedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CaseUploadFinishedEventListener.class) {
                ((CaseUploadFinishedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(DownloadingFromPaxStartedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DownloadingFromPaxStartedEventListener.class) {
                ((DownloadingFromPaxStartedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }

    public static void fireEvent(DownloadingFromPaxFinishedEvent evt) {
        Object[] listeners = eventListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DownloadingFromPaxFinishedEventListener.class) {
                ((DownloadingFromPaxFinishedEventListener) listeners[i + 1]).eventOccured(evt);
            }
        }
    }
}
