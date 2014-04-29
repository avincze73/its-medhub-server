package tdsdicominterface.site.varpalota;

import java.util.Date;

public class QueryData
{
	private String patientName;
	private Date studyDateFrom;
	private Date studyDateTo;
	private String[] modalities;

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

	/**
	 * @return the studyDateTo
	 */ public Date getStudyDateTo() {
		return studyDateTo;
	}

	/**
	 * @param studyDateTo the studyDateTo to set
	 */ public void setStudyDateTo(Date studyDateTo) {
		this.studyDateTo = studyDateTo;
	}

	/**
	 * @return the modalities
	 */ public String[] getModalities() {
		return modalities;
	}

	/**
	 * @param modalities the modalities to set
	 */ public void setModalities(String[] modalities) {
		this.modalities = modalities;
	}
}
