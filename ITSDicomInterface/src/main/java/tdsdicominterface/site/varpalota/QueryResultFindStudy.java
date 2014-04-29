package tdsdicominterface.site.varpalota;

import java.util.ArrayList;
import java.util.Date;

public class QueryResultFindStudy
{
	private String studyInstanceUID;
	private Date studyDate;
	private ArrayList<Series> seriesList;

	private ArrayList<String> modalityList;
	private ArrayList<String> bodyPartList;

	public QueryResultFindStudy()
	{
		seriesList = new ArrayList<Series>();
		modalityList = new ArrayList<String>();
		bodyPartList = new ArrayList<String>();
	}

	public void addSeries(String seriesInstanceUID, String modality, String bodyPart)
	{
		Series s = new Series();
		s.setSeriesInstanceUID(seriesInstanceUID);
		s.setModality(modality);
		s.setBodyPart(bodyPart);
		seriesList.add(s);

		addModality(modality);
		addBodyPart(bodyPart);
	}

	private void addModality(String modality)
	{
		if(modality != null && !modalityList.contains(modality))
		{
			getModalityList().add(modality);
		}
	}

	private void addBodyPart(String bodyPart)
	{
		if(bodyPart != null && !bodyPartList.contains(bodyPart))
		{
			getBodyPartList().add(bodyPart);
		}
	}

	//----------------------------------------

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
	 * @return the studyDate
	 */ public Date getStudyDate() {
		return studyDate;
	}

	/**
	 * @param studyDate the studyDate to set
	 */ public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}

	/**
	 * @return the seriesList
	 */ public ArrayList<Series> getSeriesList() {
		return seriesList;
	}

	/**
	 * @param seriesList the seriesList to set
	 */ public void setSeriesList(ArrayList<Series> seriesList) {
		this.seriesList = seriesList;
	}

	/**
	 * @return the modalityList
	 */ public ArrayList<String> getModalityList() {
		return modalityList;
	}

	/**
	 * @return the bodyPartList
	 */ public ArrayList<String> getBodyPartList() {
		return bodyPartList;
	}

	//===========================================================================

	public static class Series
	{
		private String seriesInstanceUID;
		private String modality;
		private String bodyPart;

		//-------------------------------------

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
		 * @return the bodyPart
		 */ public String getBodyPart() {
			return bodyPart;
		}

		/**
		 * @param bodyPart the bodyPart to set
		 */ public void setBodyPart(String bodyPart) {
			this.bodyPart = bodyPart;
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
