package tdsdicominterface;

import tdsdicominterface.exception.DIException;
import tdsdicominterface.media.DataImport;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.site.varpalota.CustomQuery;
import tdsdicominterface.site.varpalota.CustomRetrieve;
import tdsdicominterface.slprotocol.SLManager;

/**
 *
 * @author Rob
 */
public class DicomManager
{
	public static final String DicomInterfaceVersion = "B1.0.01";

	private BufferedWriter diagOut;

	private SLManager slManager;

	/**
	 * A megadott konyvtarban talalhato osszes DICOM file beolvasasa (azoke is, amelyek alkonyvtarakban vannak).
	 * @param directoryPath	a konyvtar teljes eleresi utvonala
	 * @return DicomDataSet objektum lista. Egy DicomDataSet objektum tartalmazza egy beolvasott file tartalmat.
	 * @throws java.io.FileNotFoundException
	 * @throws tdsdicominterface.DIException
	 */
	public static ArrayList<DicomDataSet> importDirectory(String directoryPath) throws FileNotFoundException, DIException
	{
		ArrayList<DicomDataSet> dataSets = new ArrayList<DicomDataSet>();
		File dir = new File(directoryPath);
		if(!dir.exists() || !dir.isDirectory()) throw new FileNotFoundException();
		readDirectory(dir, dataSets);
		return dataSets;
	}

	/**
	 * A megadott DICOM file beolvasása.
	 * @param filePath a file teljes elérési útvonala
	 * @return DicomFile objektum, amely tartalmazza a beolvasott file tartalmát.
	 * @throws tdsdicommediainterface.DMIException
	 * @throws java.io.FileNotFoundException
	 */
	public static DicomDataSet importFile(File file) throws DIException, FileNotFoundException
	{
			if(!file.exists() || !file.isFile()) throw new FileNotFoundException();
			if(!isDicomFile(file)) throw new DIException("The given file is not a DICOM file!");
			DataImport di = new DataImport();
			return di.importFile(file);
	}

	private static void readDirectory(File dir, ArrayList<DicomDataSet> dataSets) throws DIException, FileNotFoundException
	{
		File[] entriesArr = dir.listFiles();
		ArrayList<File> entries = new ArrayList<File>();
		entries.addAll(Arrays.asList(entriesArr));
		Collections.sort(entries);

		DataImport dataImport = new DataImport();
		for(int i = 0; i < entries.size(); i++)
		{
			File entry = entries.get(i);
			if(entry.isDirectory()) readDirectory(entry, dataSets);
			else
			{
				if(isDicomFile(entry))
				{
					DicomDataSet dicomFile = dataImport.importFile(entry);
					dataSets.add(dicomFile);
				}
			}
		}
	}

	private static boolean isDicomFile(File file) throws DIException
	{
		try
		{
			boolean ret = false;

			if("DICOMDIR".equals(file.getName())) return false;

			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);

			// Preamble
			bis.skip(128);		// 128 byte preamble

			// DICM
			byte[] dicm = new byte[4];
			bis.read(dicm);
			if(dicm[0] == 'D' && dicm[1] == 'I' && dicm[2] == 'C' && dicm[3] == 'M')
				ret = true;

			bis.close();
			fis.close();
			return ret;
		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_2001");
			return false;
		}
	}

	//===================================================================================================================

	public DicomManager(BufferedWriter diagOut)
	{
		this.diagOut = diagOut;
	}

	public void createAssociation(DicomInterfaceSettings diSettings) throws DIException
	{
		if(slManager != null && slManager.isAssociationCreated())
			slManager.closeAssociation();
		
		slManager = new SLManager(diSettings, diagOut);
	}

	public boolean isAssociationCreated()
	{
		if(slManager == null) return false;
		return slManager.isAssociationCreated();
	}

	public void closeAssociation() throws DIException
	{
		slManager.closeAssociation();
		slManager = null;
	}

	/**
	 * A masik AE elerhetosegenek ellenorzese (C-Echo)
	 */
	public void verification() throws DIException
	{
		if(slManager == null) throw new DIInternalErrorException("verification(): Association is not created!");

		slManager.verification();
	}

	/**
	 * Lista lekerdezese szuro feltetelek alapjan (C-Find).
	 * @param diSettings
	 * @param queryData
	 * @return
	 * @throws DIException
	 */
	public ArrayList<Object> query(String queryType, Object queryData) throws DIException
	{
		if(slManager == null) throw new DIInternalErrorException("query(): Association is not created!");

		CustomQuery cq = new CustomQuery(diagOut);		// az adott telepiteshez tartozo lekerdezo osztaly
		return cq.query(slManager, queryType, queryData);
	}

	public ArrayList<DicomDataSet> retrieve(String patientID,
										String studyInstanceUID,
										String seriesInstaceUID) throws DIException
	{
		if(slManager == null) throw new DIInternalErrorException("retrieve(): Association is not created!");

		CustomRetrieve cr = new CustomRetrieve(diagOut);
		return cr.retrieve(slManager, patientID, studyInstanceUID, seriesInstaceUID);
	}

}
