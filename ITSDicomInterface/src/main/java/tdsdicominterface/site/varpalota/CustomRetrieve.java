package tdsdicominterface.site.varpalota;

import java.io.BufferedWriter;
import java.util.ArrayList;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.slprotocol.SLManager;

public class CustomRetrieve
{
	private BufferedWriter diagOut;

	public CustomRetrieve(BufferedWriter diagOut)
	{
		this.diagOut = diagOut;
	}

	public ArrayList<DicomDataSet> retrieve(SLManager slManager,
										String patientID,
										String studyInstanceUID,
										String seriesInstaceUID) throws DIException
	{
		// A retrieve funkcio a C_MOVE segitsegevel tortenik.

		ArrayList<DicomDataSet> dataSetList = slManager.retrieveByCMove(patientID, studyInstanceUID, seriesInstaceUID);

		return dataSetList;
	}
}
