package tdsdicominterface.ulprotocol;

import java.util.ArrayList;
import tdsdicominterface.DicomInterfaceSettings;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;

public class AssociationSettings
{
	public static final int PvdListMaxSizeForReceivingDefault = 16384;
//	public static final int PvdListMaxSizeForReceivingDefault = 0;
	public static final int ProtocolVersion = 0x0001;		// TODO: ellenorizni, hogy tenyleg 1-e
	public static final int EndianType = TransferSyntax.BigEndianType;

	private boolean isServerSide;
	private ArrayList<PresentationContext> presentationContexts;
	private int pdvListMaxSizeForSending;
	private int pdvListMaxSizeForReceiving;

	private String callingAETitle;
	private String calledAETitle;
	private long associationMessageResponseTimeout;		// millisecodns
	private long serviceMessageResponseTimeout;		// millisecodns
	private long pdataTimeout;		// millisecodns

	public AssociationSettings(boolean isServerSide)
	{
		this.isServerSide = isServerSide;
		presentationContexts = new ArrayList<PresentationContext>();
		pdvListMaxSizeForReceiving = PvdListMaxSizeForReceivingDefault;
	}

	/**
	 * A DicomInterfaceSettings alapjan inicializalja a belso valtozokat.
	 * @param diSettings
	 */
	public AssociationSettings(DicomInterfaceSettings diSettings, boolean isServerSide)
	{
		this.isServerSide = isServerSide;
		presentationContexts = new ArrayList<PresentationContext>();
		pdvListMaxSizeForReceiving = PvdListMaxSizeForReceivingDefault;

		calledAETitle = diSettings.getPacsServerAETitle();
		callingAETitle = diSettings.getAeTitle();
		associationMessageResponseTimeout = diSettings.getAssociationMessageResponseTimeout();
		serviceMessageResponseTimeout = diSettings.getServiceMessageResponseTimeout();
		pdataTimeout = diSettings.getPdataTimeout();
	}

	public void addPresentationContext(String abstractSyntax, ArrayList<String> transferSyntaxes)
	{
		PresentationContext pc = new PresentationContext();
		pc.setAbstractSyntax(abstractSyntax);
		pc.setTransferSyntaxes(transferSyntaxes);
		pc.setId(presentationContexts.size() * 2 + 1);
		presentationContexts.add(pc);
	}

	/**
	 * Server eseten a Presentation context hozzaadasa.
	 * @param pc
	 */
	public void addPresentationContext(PresentationContext pc)
	{
		presentationContexts.add(pc);
	}

	public PresentationContext getPresentationContext(int index)
	{
		return presentationContexts.get(index);
	}

	public PresentationContext getPresentationContextByID(int id)
	{
		int i = 0;
		for(; i < presentationContexts.size(); i++)
		{
			if(presentationContexts.get(i).getId() == id) break;
		}
		if(i == presentationContexts.size()) return null;
		return presentationContexts.get(i);
	}

	/**
	 * PresentationContext elkerese abstractSyntax alapjan. Akkor is visszaadja a PresentationContext-et, ha az
	 * nem lett elfogadva a masik AE altal.
	 * @param abstractSyntax
	 * @return PresentationContext
	 */
	public PresentationContext getPresentationContextByAbstractSyntax(String abstractSyntax)
	{
		int i = 0;
		for(; i < presentationContexts.size(); i++)
		{
			if(presentationContexts.get(i).getAbstractSyntax().equals(abstractSyntax)) break;
		}
		if(i == presentationContexts.size()) return null;
		return presentationContexts.get(i);
	}

	public void updatePresentationContext(int id, int result, String acceptedTransferSyntax) throws DIException
	{
		PresentationContext pc = getPresentationContextByID(id);
		if(pc == null)
		{
			if(isServerSide)
				throw new DIInternalErrorException("AssociationSettings.getPresentationContextByID() returned null on the server side.");
			else 
				DIException.createAndThrow("ERR_1301", id);
		}
		if(result == 0)
			pc.setAccepted(true);
		else
			pc.setAccepted(false);
		pc.setRejectionType(result);
		pc.setAccTransferSyntax(acceptedTransferSyntax);
	}

	public int getPresentationContextCount()
	{
		return presentationContexts.size();
	}

	//--------------------------------------------------------------------

	/**
	 * @return the callingAETitle
	 */ public String getCallingAETitle() {
		return callingAETitle;
	}

	/**
	 * @param callingAETitle the callingAETitle to set
	 */ public void setCallingAETitle(String callingAETitle) {
		this.callingAETitle = callingAETitle;
	}

	/**
	 * @return the calledAETitle
	 */ public String getCalledAETitle() {
		return calledAETitle;
	}

	/**
	 * @param calledAETitle the calledAETitle to set
	 */ public void setCalledAETitle(String calledAETitle) {
		this.calledAETitle = calledAETitle;
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
	 * @return the pdvListMaxSizeForSending
	 */ public int getPdvListMaxSizeForSending() {
		return pdvListMaxSizeForSending;
	}

	/**
	 * @param pdvListMaxSizeForSending the pdvListMaxSizeForSending to set
	 */ public void setPdvListMaxSizeForSending(int pdvListMaxSizeForSending) {
		this.pdvListMaxSizeForSending = pdvListMaxSizeForSending;
	}

	/**
	 * @return the pdvListMaxSizeForReceiving
	 */ public int getPdvListMaxSizeForReceiving() {
		return pdvListMaxSizeForReceiving;
	}

	/**
	 * @param pdvListMaxSizeForReceiving the pdvListMaxSizeForReceiving to set
	 */ public void setPdvListMaxSizeForReceiving(int pdvListMaxSizeForReceiving) {
		this.pdvListMaxSizeForReceiving = pdvListMaxSizeForReceiving;
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

	//================================================================================================

	public static class PresentationContext
	{
		private String abstractSyntax;
		private ArrayList<String> transferSyntaxes;
		private int id;

		private boolean accepted;
		private int rejectionType;
		private String accTransferSyntax;

		public PresentationContext()
		{
//			transferSyntaxes = new ArrayList<String>();
		}

		//--------------------------------------------------------------------

		/**
		 * @return the abstractSyntax
		 */ public String getAbstractSyntax() {
			return abstractSyntax;
		}

		/**
		 * @param abstractSyntax the abstractSyntax to set
		 */ public void setAbstractSyntax(String abstractSyntax) {
			this.abstractSyntax = abstractSyntax;
		}

		/**
		 * @return the transferSyntaxes
		 */ public ArrayList<String> getTransferSyntaxes() {
			return transferSyntaxes;
		}

		/**
		 * @return the id
		 */ public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */ public void setId(int id) {
			this.id = id;
		}

		/**
		 * @param transferSyntaxes the transferSyntaxes to set
		 */ public void setTransferSyntaxes(ArrayList<String> transferSyntaxes) {
			this.transferSyntaxes = transferSyntaxes;
		}

		/**
		 * @return the accepted
		 */ public boolean isAccepted() {
			return accepted;
		}

		/**
		 * @param accepted the accepted to set
		 */ public void setAccepted(boolean accepted) {
			this.accepted = accepted;
		}

		/**
		 * @return the accTransferSyntax
		 */ public String getAccTransferSyntax() {
			return accTransferSyntax;
		}

		/**
		 * @param accTransferSyntax the accTransferSyntax to set
		 */ public void setAccTransferSyntax(String accTransferSyntax) {
			this.accTransferSyntax = accTransferSyntax;
		}

		/**
		 * @return the rejectionType
		 */ public int getRejectionType() {
			return rejectionType;
		}

		/**
		 * @param rejectionType the rejectionType to set
		 */ public void setRejectionType(int rejectionType) {
			this.rejectionType = rejectionType;
		}
	}
}
