package tdsdicominterface.site.varpalota;

import java.util.Date;

/**
 *
 * @author Kovacs Robert
 */
public class QueryResultAutomaticUpload
{
	private String patientID;
	private String patientName;
	private String studyInstanceUID;
	private Date studyDateTime;
	private Date studyDateOnly;

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
	 * @return the studyDateTime
	 */ public Date getStudyDateTime() {
		return studyDateTime;
	}

	/**
	 * @param studyDateTime the studyDateTime to set
	 */ public void setStudyDateTime(Date studyDateTime) {
		this.studyDateTime = studyDateTime;
	}

	/**
	 * @return the studyDateOnly
	 */ public Date getStudyDateOnly() {
		return studyDateOnly;
	}

	/**
	 * @param studyDateOnly the studyDateOnly to set
	 */ public void setStudyDateOnly(Date studyDateOnly) {
		this.studyDateOnly = studyDateOnly;
	}
}
