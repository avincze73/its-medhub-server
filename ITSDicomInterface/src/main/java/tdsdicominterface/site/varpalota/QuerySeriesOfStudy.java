package tdsdicominterface.site.varpalota;

public class QuerySeriesOfStudy
{
	private String patientID;
	private String studyInstanceUID;

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
	 * @return the studyInstanceUID
	 */ public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	/**
	 * @param studyInstanceUID the studyInstanceUID to set
	 */ public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}
}
