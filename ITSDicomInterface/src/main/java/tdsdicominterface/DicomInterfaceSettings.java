package tdsdicominterface;

public class DicomInterfaceSettings
{
	private String ipAddress;
	private int port;
	private String aeTitle;

	private String pacsServerIpAddress;
	private int pacsServerPort;
	private String pacsServerAETitle;

	private long associationMessageResponseTimeout;		// millisecodns
	private long serviceMessageResponseTimeout;		// millisecodns
	private long pdataTimeout;		// millisecodns

	//----------------------------------------------------------------------------

	/**
	 * @return the pacsServerIpAddress
	 */
	public String getPacsServerIpAddress() {
		return pacsServerIpAddress;
	}

	/**
	 * @param pacsServerIpAddress the pacsServerIpAddress to set
	 */
	public void setPacsServerIpAddress(String pacsServerIpAddress) {
		this.pacsServerIpAddress = pacsServerIpAddress;
	}

	/**
	 * @return the pacsServerPort
	 */
	public int getPacsServerPort() {
		return pacsServerPort;
	}

	/**
	 * @param pacsServerPort the pacsServerPort to set
	 */
	public void setPacsServerPort(int pacsServerPort) {
		this.pacsServerPort = pacsServerPort;
	}

	/**
	 * @return the ipAddress
	 */ public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */ public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the port
	 */ public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */ public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the aeTitle
	 */ public String getAeTitle() {
		return aeTitle;
	}

	/**
	 * @param aeTitle the aeTitle to set
	 */ public void setAeTitle(String aeTitle) {
		this.aeTitle = aeTitle;
	}

	/**
	 * @return the pacsServerAETitle
	 */ public String getPacsServerAETitle() {
		return pacsServerAETitle;
	}

	/**
	 * @param pacsServerAETitle the pacsServerAETitle to set
	 */ public void setPacsServerAETitle(String pacsServerAETitle) {
		this.pacsServerAETitle = pacsServerAETitle;
	}

	/**
	 * @return the associationMessageResponseTimeout
	 */ public long getAssociationMessageResponseTimeout() {
		return associationMessageResponseTimeout;
	}

	/**
	 * @param associationMessageResponseTimeout the associationMessageResponseTimeout to set
	 */ public void setAssociationMessageResponseTimeout(long associationMessageResponseTimeout) {
		this.associationMessageResponseTimeout = associationMessageResponseTimeout;
	}

	/**
	 * @return the serviceMessageResponseTimeout
	 */ public long getServiceMessageResponseTimeout() {
		return serviceMessageResponseTimeout;
	}

	/**
	 * @param serviceMessageResponseTimeout the serviceMessageResponseTimeout to set
	 */ public void setServiceMessageResponseTimeout(long serviceMessageResponseTimeout) {
		this.serviceMessageResponseTimeout = serviceMessageResponseTimeout;
	}

	/**
	 * @return the pdataTimeout
	 */ public long getPdataTimeout() {
		return pdataTimeout;
	}

	/**
	 * @param pdataTimeout the pdataTimeout to set
	 */ public void setPdataTimeout(long pdataTimeout) {
		this.pdataTimeout = pdataTimeout;
	}

}
