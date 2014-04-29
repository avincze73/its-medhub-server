package tdsdicominterface.site.varpalota;

import java.util.Date;

public class QueryFindPatient
{
	private String patientName;
	private Date studyDateFrom;

	//-----------------------------------------------------

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
