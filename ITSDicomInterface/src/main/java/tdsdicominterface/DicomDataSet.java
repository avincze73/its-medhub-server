package tdsdicominterface;

import java.io.Serializable;


/**
 *
 * @author Rob
 */
public class DicomDataSet extends DicomItem implements Serializable, Cloneable
{
	private String transferSyntax;
	private String sopInstanceUID;
	private String filePath;

	public DicomDataSet()
	{
		super();
	}

	@Override
	public Object clone()
	{
		return super.clone();

//		DicomDataSet ds = (DicomDataSet) super.clone();
//		ds.setTransferSyntax(this.transferSyntax);
//		ds.setSopInstanceUID(this.sopInstanceUID);
//		ds.setFilePath(this.filePath);
//
//		return ds;
	}

	//-----------------------------------------------------------------------------------------

	/**
	 * @return the filePath
	 */ public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */ public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the transferSyntax
	 */
	public String getTransferSyntax() {
		return transferSyntax;
	}

	/**
	 * @param transferSyntax the transferSyntax to set
	 */
	public void setTransferSyntax(String transferSyntax) {
		this.transferSyntax = transferSyntax;
	}

	/**
	 * @return the sopInstanceUID
	 */
	public String getSopInstanceUID() {
		return sopInstanceUID;
	}

	/**
	 * @param sopInstanceUID the sopInstanceUID to set
	 */
	public void setSopInstanceUID(String sopInstanceUID) {
		this.sopInstanceUID = sopInstanceUID;
	}

}
