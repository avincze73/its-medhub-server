package tdsdicominterface.site.varpalota;

import java.util.Date;

/**
 *
 * @author Kovacs Robert
 */
public class QueryAutomaticUpload
{
	public static final int AUTOMATIC_TYPE_1 = 1;
	public static final int AUTOMATIC_TYPE_2 = 2;
	public static final int AUTOMATIC_TYPE_3 = 3;

	private int type;
	private Date dateFrom;

	//------------------------------------------------------

	/**
	 * @return the type
	 */ public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */ public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the dateFrom
	 */ public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */ public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
}
