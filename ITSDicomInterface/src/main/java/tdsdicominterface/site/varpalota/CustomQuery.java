package tdsdicominterface.site.varpalota;

import java.io.BufferedWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.slprotocol.SLManager;
import tdsdicominterface.slprotocol.SOP;

public class CustomQuery
{
	private BufferedWriter diagOut;

	public CustomQuery(BufferedWriter diagOut)
	{
		this.diagOut = diagOut;
	}

	public ArrayList<Object> query(SLManager slManager, String queryType, Object queryData) throws DIException
	{
		if("findPatient".equals(queryType))
		{
			QueryFindPatient q = (QueryFindPatient) queryData;

			DicomItem query = new DicomItem();
			String studyDate = "";
			if(q.getStudyDateFrom() != null)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(q.getStudyDateFrom());
				studyDate = String.format("%04d%02d%02d-", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
			}
			query.addDataElement(new DataElement("StudyDate", studyDate));
			query.addDataElement(new DataElement(0x0008, 0x0052, "STUDY"));			// QueryRetrieveLevel
			query.addDataElement(new DataElement("PatientsName", q.getPatientName()));
			query.addDataElement(new DataElement("PatientID", ""));
			query.addDataElement(new DataElement("PatientsBirthDate", ""));

			ArrayList<Object> queryResultList = new ArrayList<Object>();
			HashMap<String, QueryResultFindPatient> queryResultListHash = new HashMap<String, QueryResultFindPatient>();

			ArrayList<DicomItem> resultItems = slManager.query(SOP.StudyRootQueryRetrieveFIND, query);
			slManager.printDiag("Result: " + Integer.toString(resultItems.size()) + " elem.");

			for(int i = 0; i < resultItems.size(); i++)
			{
				if(queryResultListHash.get(resultItems.get(i).getString("PatientID")) == null)
				{
					QueryResultFindPatient res = new QueryResultFindPatient();
					res.setPatientID(resultItems.get(i).getString("PatientID"));
					res.setPatientName(resultItems.get(i).getString("PatientsName"));

					String dateOfBirthStr = resultItems.get(i).getString("PatientsBirthDate");
					if(dateOfBirthStr != null)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						try {
							res.setDateOfBirth(sdf.parse(dateOfBirthStr));
						}
						catch (ParseException ex) {
							// ha nem helyes a datum, akkor uresnek vesszuk
							slManager.printDiag("PatientsBirthDate is incorrect: " + dateOfBirthStr +
									" (PatientID: " + res.getPatientID() + ")");
						}
					}
					else
						res.setDateOfBirth(null);
					queryResultList.add(res);
					queryResultListHash.put(res.getPatientID(), res);
				}
			}

			return queryResultList;
		}
		else if("findStudy".equals(queryType))
		{
			ArrayList<Object> queryResultList = new ArrayList<Object>();
			QueryFindStudy q = (QueryFindStudy) queryData;

			DicomItem query = new DicomItem();
			if(q.getStudyDateFrom() != null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-");
				query.addDataElement(new DataElement("StudyDate", sdf.format(q.getStudyDateFrom())));
			}
			else
				query.addDataElement(new DataElement("StudyDate", ""));
			query.addDataElement(new DataElement(0x0008, 0x0052, "STUDY"));			// QueryRetrieveLevel
			query.addDataElement(new DataElement("PatientID", q.getPatientID()));
			query.addDataElement(new DataElement("StudyInstanceUID", ""));		// unique key for study level

			ArrayList<DicomItem> resultItems = slManager.query(SOP.StudyRootQueryRetrieveFIND, query);
			slManager.printDiag("Result: " + Integer.toString(resultItems.size()) + " elem.");

			ArrayList<QueryResultFindStudy> studyList = new ArrayList<QueryResultFindStudy>();
			for(int i = 0; i < resultItems.size(); i++)
			{
				QueryResultFindStudy res = new QueryResultFindStudy();
				res.setStudyInstanceUID(resultItems.get(i).getString("StudyInstanceUID"));

				String dateStr = resultItems.get(i).getString("StudyDate");
				if(dateStr != null && !dateStr.isEmpty())
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date date = null;
					try {
						date = sdf.parse(dateStr);
					}
					catch(ParseException ex) {
						// Ha hibas a datum, akkor null lesz, nem dobunk exception-t
						slManager.printDiag("study date is incorrect: " + dateStr + " (studyInstanceUID: " + res.getStudyInstanceUID() + ")");
					}
					res.setStudyDate(date);
				}

				studyList.add(res);
			}

			// Series adatok lekerdezese - PATIENT root
			//-----------------------------------------
			for(int i = 0; i < studyList.size(); i++)	// minden study-ra le kell kerdezni
			{
				QueryResultFindStudy study = studyList.get(i);
				query = new DicomItem();
				query.addDataElement(new DataElement(0x0008, 0x0052, "SERIES"));			// QueryRetrieveLevel
				query.addDataElement(new DataElement("Modality", ""));
				query.addDataElement(new DataElement("PatientID", q.getPatientID()));
				query.addDataElement(new DataElement("BodyPartExamined", ""));
				query.addDataElement(new DataElement("StudyInstanceUID", study.getStudyInstanceUID()));
				query.addDataElement(new DataElement("SeriesInstanceUID", ""));

				resultItems = slManager.query(SOP.PatientRootQueryRetrieveFIND, query);
				slManager.printDiag("Result: " + Integer.toString(resultItems.size()) + " elem.");

				for(int s = 0; s < resultItems.size(); s++)
				{
					study.addSeries(resultItems.get(s).getString("SeriesInstanceUID"),
							resultItems.get(s).getString("Modality"),
							resultItems.get(s).getString("BodyPartExamined"));
				}
			}

			queryResultList.addAll(studyList);
			return queryResultList;
		}
		else if("automaticUpload".equals(queryType))
		{
			ArrayList<Object> queryResultList = new ArrayList<Object>();
			QueryAutomaticUpload q = (QueryAutomaticUpload) queryData;

			DicomItem query = new DicomItem();
			String studyDate = "";
			if(q.getDateFrom() != null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				studyDate = sdf.format(q.getDateFrom());
			}
			query.addDataElement(new DataElement("StudyDate", studyDate));
			query.addDataElement(new DataElement("StudyTime", ""));
			query.addDataElement(new DataElement(0x0008, 0x0052, "STUDY"));			// QueryRetrieveLevel
			query.addDataElement(new DataElement("PatientsName", ""));
			query.addDataElement(new DataElement("PatientID", ""));
			query.addDataElement(new DataElement("StudyInstanceUID", ""));

			ArrayList<DicomItem> resultItems = slManager.query(SOP.StudyRootQueryRetrieveFIND, query);
			slManager.printDiag("Result: " + Integer.toString(resultItems.size()) + " elem.");

			ArrayList<QueryResultAutomaticUpload> studyList = new ArrayList<QueryResultAutomaticUpload>();
			for(int i = 0; i < resultItems.size(); i++)
			{
				QueryResultAutomaticUpload study = new QueryResultAutomaticUpload();
				study.setStudyInstanceUID(resultItems.get(i).getString("StudyInstanceUID"));
				study.setPatientID(resultItems.get(i).getString("PatientID"));
				study.setPatientName(resultItems.get(i).getString("PatientsName"));

				// stuidy date and time
				String dateStr = resultItems.get(i).getString("StudyDate");
				String timeStr = resultItems.get(i).getString("StudyTime");
				if(dateStr != null && !dateStr.isEmpty())
				{
					Date dateTime = null;
					Date dateOnly = null;
					if(timeStr != null)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
						try {
							dateTime = sdf.parse(dateStr + "-" + timeStr);

							sdf = new SimpleDateFormat("yyyyMMdd");
							try {
								dateOnly = sdf.parse(dateStr);
							} catch(ParseException ex2) {
								// Ha hibas a datum, akkor null lesz, nem dobunk exception-t
							}
						}
						catch(ParseException ex) {
							// Ha hibas a datum + ido, akkor csak a datum lesz
							slManager.printDiag("study time is incorrect: " + timeStr + " (studyInstanceUID: " + study.getStudyInstanceUID() + ")");
							sdf = new SimpleDateFormat("yyyyMMdd");
							try {
								dateTime = sdf.parse(dateStr);
								dateOnly = dateTime;
							}
							catch(ParseException ex2) {
								// Ha hibas a datum, akkor null lesz, nem dobunk exception-t
								slManager.printDiag("study date is incorrect: " + dateStr + " (studyInstanceUID: " + study.getStudyInstanceUID() + ")");
							}
						}
					}
					else
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						try {
							dateTime = sdf.parse(dateStr);
							dateOnly = dateTime;
						}
						catch(ParseException ex) {
							// Ha hibas a datum, akkor null lesz, nem dobunk exception-t
							slManager.printDiag("study date is incorrect: " + dateStr + " (studyInstanceUID: " + study.getStudyInstanceUID() + ")");
						}
					}
					study.setStudyDateTime(dateTime);
					study.setStudyDateOnly(dateOnly);
				}

				studyList.add(study);
			}

			queryResultList.addAll(studyList);
			return queryResultList;
		}
		else if("seriesOfStudy".equals(queryType))
		{
			ArrayList<Object> queryResultList = new ArrayList<Object>();
			QuerySeriesOfStudy q = (QuerySeriesOfStudy) queryData;
			DicomItem query = new DicomItem();
			query.addDataElement(new DataElement(0x0008, 0x0052, "SERIES"));			// QueryRetrieveLevel
			query.addDataElement(new DataElement("PatientID", q.getPatientID()));
			query.addDataElement(new DataElement("StudyInstanceUID", q.getStudyInstanceUID()));
			query.addDataElement(new DataElement("SeriesInstanceUID", ""));

			ArrayList<DicomItem> resultItems = slManager.query(SOP.PatientRootQueryRetrieveFIND, query);
			slManager.printDiag("Result: " + Integer.toString(resultItems.size()) + " elem.");

			ArrayList<QueryResultSeriesOfStudy> seriesList = new ArrayList<QueryResultSeriesOfStudy>();
			for(int i = 0; i < resultItems.size(); i++)
			{
				QueryResultSeriesOfStudy s = new QueryResultSeriesOfStudy();
				s.setSeriesInstanceUID(resultItems.get(i).getString("SeriesInstanceUID"));
				seriesList.add(s);
			}

			queryResultList.addAll(seriesList);
			return queryResultList;
		}
		else
			throw new DIInternalErrorException("Unknown query type: " + queryType);
	}
}
