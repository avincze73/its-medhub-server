package tdsdicominterface.cmoveserver;

import java.util.ArrayList;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;

public class ServerAssociationSettings
{
//	public static final int PvdListMaxSizeForReceivingDefault = 16384;
//	public static final int EndianType = TransferSyntax.BigEndianType;
//
//	private ArrayList<PresentationContext> presentationContexts;
//	private int pdvListMaxSizeForSending;
//	private int pdvListMaxSizeForReceiving;
//
//	private String callingAETitle;
//	private String calledAETitle;
//
//	public ServerAssociationSettings()
//	{
//		presentationContexts = new ArrayList<PresentationContext>();
//		pdvListMaxSizeForReceiving = PvdListMaxSizeForReceivingDefault;
//	}
//
//	/**
//	 * Presentation context hozzaadasa.
//	 * @param pc
//	 */
//	public void addPresentationContext(PresentationContext pc)
//	{
//		presentationContexts.add(pc);
//	}
//
//	public void updatePresentationContext(int id, int result, String acceptedTransferSyntax) throws DIException
//	{
//		PresentationContext pc = getPresentationContextByID(id);
//		if(pc == null) throw new DIInternalErrorException("ServerAssociationSettings.getPresentationContextByID() returned null.");
//		if(result == 0)
//			pc.setAccepted(true);
//		else
//			pc.setAccepted(false);
//		pc.setRejectionType(result);
//		pc.setAccTransferSyntax(acceptedTransferSyntax);
//	}
//
//	public PresentationContext getPresentationContextByID(int id)
//	{
//		int i = 0;
//		for(; i < presentationContexts.size(); i++)
//		{
//			if(presentationContexts.get(i).getId() == id) break;
//		}
//		if(i == presentationContexts.size()) return null;
//		return presentationContexts.get(i);
//	}
//
//	//----------------------------------------------------------------------------------------------------------
//
//	/**
//	 * @return the pdvListMaxSizeForReceiving
//	 */ public int getPdvListMaxSizeForReceiving() {
//		return pdvListMaxSizeForReceiving;
//	}
//
//	/**
//	 * @param pdvListMaxSizeForReceiving the pdvListMaxSizeForReceiving to set
//	 */ public void setPdvListMaxSizeForReceiving(int pdvListMaxSizeForReceiving) {
//		this.pdvListMaxSizeForReceiving = pdvListMaxSizeForReceiving;
//	}
//
//	/**
//	 * @return the callingAETitle
//	 */ public String getCallingAETitle() {
//		return callingAETitle;
//	}
//
//	/**
//	 * @param callingAETitle the callingAETitle to set
//	 */ public void setCallingAETitle(String callingAETitle) {
//		this.callingAETitle = callingAETitle;
//	}
//
//	/**
//	 * @return the calledAETitle
//	 */ public String getCalledAETitle() {
//		return calledAETitle;
//	}
//
//	/**
//	 * @param calledAETitle the calledAETitle to set
//	 */ public void setCalledAETitle(String calledAETitle) {
//		this.calledAETitle = calledAETitle;
//	}
//
//	/**
//	 * @return the pdvListMaxSizeForSending
//	 */ public int getPdvListMaxSizeForSending() {
//		return pdvListMaxSizeForSending;
//	}
//
//	/**
//	 * @param pdvListMaxSizeForSending the pdvListMaxSizeForSending to set
//	 */ public void setPdvListMaxSizeForSending(int pdvListMaxSizeForSending) {
//		this.pdvListMaxSizeForSending = pdvListMaxSizeForSending;
//	}
//
//	//===========================================================================================================================
//
//	public static class PresentationContext
//	{
//		private String abstractSyntax;
//		private ArrayList<String> transferSyntaxes;
//		private int id;
//
//		private boolean accepted;
//		private int rejectionType;
//		private String accTransferSyntax;
//
//		public PresentationContext()
//		{
//		}
//
//		//--------------------------------------------------------------------
//
//		/**
//		 * @return the abstractSyntax
//		 */ public String getAbstractSyntax() {
//			return abstractSyntax;
//		}
//
//		/**
//		 * @param abstractSyntax the abstractSyntax to set
//		 */ public void setAbstractSyntax(String abstractSyntax) {
//			this.abstractSyntax = abstractSyntax;
//		}
//
//		/**
//		 * @return the transferSyntaxes
//		 */ public ArrayList<String> getTransferSyntaxes() {
//			return transferSyntaxes;
//		}
//
//		/**
//		 * @return the id
//		 */ public int getId() {
//			return id;
//		}
//
//		/**
//		 * @param id the id to set
//		 */ public void setId(int id) {
//			this.id = id;
//		}
//
//		/**
//		 * @param transferSyntaxes the transferSyntaxes to set
//		 */ public void setTransferSyntaxes(ArrayList<String> transferSyntaxes) {
//			this.transferSyntaxes = transferSyntaxes;
//		}
//
//		/**
//		 * @return the accepted
//		 */ public boolean isAccepted() {
//			return accepted;
//		}
//
//		/**
//		 * @param accepted the accepted to set
//		 */ public void setAccepted(boolean accepted) {
//			this.accepted = accepted;
//		}
//
//		/**
//		 * @return the accTransferSyntax
//		 */ public String getAccTransferSyntax() {
//			return accTransferSyntax;
//		}
//
//		/**
//		 * @param accTransferSyntax the accTransferSyntax to set
//		 */ public void setAccTransferSyntax(String accTransferSyntax) {
//			this.accTransferSyntax = accTransferSyntax;
//		}
//
//		/**
//		 * @return the rejectionType
//		 */ public int getRejectionType() {
//			return rejectionType;
//		}
//
//		/**
//		 * @param rejectionType the rejectionType to set
//		 */ public void setRejectionType(int rejectionType) {
//			this.rejectionType = rejectionType;
//		}
//	}
}
