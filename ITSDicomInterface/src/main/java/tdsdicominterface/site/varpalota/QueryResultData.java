package tdsdicominterface.site.varpalota;

import java.util.ArrayList;
import java.util.Date;

public class QueryResultData
{
	private String patientID;
	private String patientName;
	private Date studyDateAndTime;
	private String studyInstanceUID;
	private String accessionNumber;
	private String studyID;
	private String referringPhysiciansName;
	private String studyDescription;
	private ArrayList<Series> seriesList;

	public QueryResultData()
	{
		seriesList = new ArrayList<Series>();
	}

	public void addSeries(String modality, String seriesNumber, String seriesInstanceUID, String bodyPartExamined)
	{
		Series s = new Series();
		s.setModality(modality);
		s.setSeriesNumber(seriesNumber);
		s.setSeriesInstanceUID(seriesInstanceUID);
		s.setBodyPartExamined(bodyPartExamined);
		seriesList.add(s);
	}

	//-----------------------------------------------------

	/**
	 * @return the patientID
	 */ public String getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID the patientID to set
	 */ public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	/**
	 * @return the patientName
	 */ public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */ public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the studyDateAndTime
	 */ public Date getStudyDateAndTime() {
		return studyDateAndTime;
	}

	/**
	 * @param studyDateAndTime the studyDateAndTime to set
	 */ public void setStudyDateAndTime(Date studyDateAndTime) {
		this.studyDateAndTime = studyDateAndTime;
	}

	/**
	 * @return the studyInstanceUID
	 */ public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	/**
	 * @param studyInstanceUID the studyInstanceUID to set
	 */ public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}

	/**
	 * @return the accessionNumber
	 */ public String getAccessionNumber() {
		return accessionNumber;
	}

	/**
	 * @param accessionNumber the accessionNumber to set
	 */ public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	/**
	 * @return the referringPhysiciansName
	 */ public String getReferringPhysiciansName() {
		return referringPhysiciansName;
	}

	/**
	 * @param referringPhysiciansName the referringPhysiciansName to set
	 */ public void setReferringPhysiciansName(String referringPhysiciansName) {
		this.referringPhysiciansName = referringPhysiciansName;
	}

	/**
	 * @return the studyDescription
	 */ public String getStudyDescription() {
		return studyDescription;
	}

	/**
	 * @param studyDescription the studyDescription to set
	 */ public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	/**
	 * @return the seriesList
	 */ public ArrayList<Series> getSeriesList() {
		return seriesList;
	}

	/**
	 * @return the studyID
	 */ public String getStudyID() {
		return studyID;
	}

	/**
	 * @param studyID the studyID to set
	 */ public void setStudyID(String studyID) {
		this.studyID = studyID;
	}

	//================================================================================================================

	public class Series
	{
		private String seriesNumber;
		private String modality;
		private String bodyPartExamined;
		private String seriesInstanceUID;

		/**
		 * @return the seriesNumber
		 */ public String getSeriesNumber() {
			return seriesNumber;
		}

		/**
		 * @param seriesNumber the seriesNumber to set
		 */ public void setSeriesNumber(String seriesNumber) {
			this.seriesNumber = seriesNumber;
		}

		/**
		 * @return the modality
		 */ public String getModality() {
			return modality;
		}

		/**
		 * @param modality the modality to set
		 */ public void setModality(String modality) {
			this.modality = modality;
		}

		/**
		 * @return the bodyPartExamined
		 */ public String getBodyPartExamined() {
			return bodyPartExamined;
		}

		/**
		 * @param bodyPartExamined the bodyPartExamined to set
		 */ public void setBodyPartExamined(String bodyPartExamined) {
			this.bodyPartExamined = bodyPartExamined;
		}

		/**
		 * @return the seriesInstanceUID
		 */ public String getSeriesInstanceUID() {
			return seriesInstanceUID;
		}

		/**
		 * @param seriesInstanceUID the seriesInstanceUID to set
		 */ public void setSeriesInstanceUID(String seriesInstanceUID) {
			this.seriesInstanceUID = seriesInstanceUID;
		}
	}
}
