package tdsdicominterface.cmoveserver;

import java.io.BufferedWriter;
import tdsdicominterface.DicomInterfaceSettings;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.slprotocol.SLManager;

public class CMoveServer extends Thread
{
	private ServerULManager ulManager;
	private String errCode;
	private Throwable error;
	private SLManager caller;

	public CMoveServer(DicomInterfaceSettings diSettings, int cMoveRqMessageID, BufferedWriter diagOut) throws DIException
	{
		errCode = null;
		ulManager = new ServerULManager(diSettings, cMoveRqMessageID, diagOut);
		error = null;
	}

	@Override
	public void run()
	{
		try
		{
			getUlManager().runServer();
		}
		catch (Throwable th)
		{
			error = th;
		}
//		catch (DIException ex)
//		{
//			errCode = ex.getErrorCode();
//			if(errCode == null) errCode = "error without error code";
//		}
	}

	//------------------------------------------------------------------------------------

	/**
	 * @return the errCode
	 */ public String getErrCode() {
		return errCode;
	}

	/**
	 * @return the ulManager
	 */ public ServerULManager getUlManager() {
		return ulManager;
	}

	/**
	 * @return the error
	 */ public Throwable getError() {
		return error;
	}
}
