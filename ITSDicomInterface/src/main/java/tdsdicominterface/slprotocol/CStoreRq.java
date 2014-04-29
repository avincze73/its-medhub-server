package tdsdicominterface.slprotocol;

import tdsdicominterface.DicomItem;
import tdsdicominterface.exception.DIException;

public class CStoreRq
{
	public static final int CommandNum = 0x0001;

	// valtozok a beolvasashoz
	private String sopClassUID;
	private int messageID;
	private String moveOriginatorAET;
	private int moveOriginatorMessageID;


	public CStoreRq(DicomItem commandSet) throws DIException
	{
		sopClassUID = commandSet.getString(0x0000, 0x0002);
		messageID = commandSet.getFirstInt(0x0000, 0x0110);
		moveOriginatorAET = commandSet.getString(0x0000, 0x1030);
		moveOriginatorMessageID = commandSet.getFirstInt(0x0000, 0x1031);
	}

	//--------------------------------------------------------------------------------------------------------

	/**
	 * @return the sopClassUID
	 */ public String getSopClassUID() {
		return sopClassUID;
	}

	/**
	 * @return the messageID
	 */ public int getMessageID() {
		return messageID;
	}

	/**
	 * @return the moveOriginatorAET
	 */ public String getMoveOriginatorAET() {
		return moveOriginatorAET;
	}

	/**
	 * @return the moveOriginatorMessageID
	 */ public int getMoveOriginatorMessageID() {
		return moveOriginatorMessageID;
	}

}
