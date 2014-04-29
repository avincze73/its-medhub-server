package tdsdicominterface.site.varpalota;

import java.util.Date;

public class QueryFindStudy
{
	private String patientID;
	private Date studyDateFrom;

	//------------------------------------------------------------

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
	 * @return the studyDateFrom
	 */ public Date getStudyDateFrom() {
		return studyDateFrom;
	}

	/**
	 * @param studyDateFrom the studyDateFrom to set
	 */ public void setStudyDateFrom(Date studyDateFrom) {
		this.studyDateFrom = studyDateFrom;
	}
}
