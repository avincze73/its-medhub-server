package tdsdicominterface.site.varpalota;

import java.util.Date;

public class QueryResultFindPatient
{
	private String patientName;
	private String patientID;
	private Date dateOfBirth;

	//-----------------------------------------------------------------

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
	 * @return the dateOfBirth
	 */ public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */ public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
