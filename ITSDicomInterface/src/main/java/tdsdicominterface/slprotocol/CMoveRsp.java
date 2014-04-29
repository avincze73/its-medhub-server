package tdsdicominterface.slprotocol;

import tdsdicominterface.DicomItem;
import tdsdicominterface.exception.DIException;

public class CMoveRsp
{
	public static final int CommandNum = 0x8021;

	public static final int StatusSuccess = 0x0000;		// Sub-operations Complete – No Failures
	public static final int StatusPending = 0xff00;		// Sub-operations are continuing
	public static final int StatusWarning = 0xb000;		// Sub-operations Complete – One or more Failures
	public static final int StatusCancel = 0xfe00;		// Sub-operations terminated due to Cancel Indication
	public static final int StatusFailureRefused1 = 0xa701;		// Refused: Out of Resources – Unable to calculate number of matches
	public static final int StatusFailureRefused2 = 0xa702;		// Refused: Out of Resources – Unable to perform sub-operations
	public static final int StatusFailureUnknownDest = 0xa801;	// Refused: Move Destination unknown
	public static final int StatusFailureIdentifierDoesNotMatchSOPClass = 0xa900;		// Identifier does not match SOP Class

	// valtozok a kiirashoz
//	private byte[] commandData;
//	private byte[] dataSetData;

//	public CMoveRsp(CMoveRq rq, int status, DicomItem record, TransferSyntax dataTransferSyntax) throws DIException
//	{
//	}

	public CMoveRsp(DicomItem commandSet) throws DIException
	{
		// nincs teendo
	}

//	/**
//	 * @return the commandData
//	 */ public byte[] getCommandData() {
//		return commandData;
//	}
//
//	/**
//	 * @return the dataSetData
//	 */ public byte[] getDataSetData() {
//		return dataSetData;
//	}
}
