package tdsdicominterface.exception;

import java.util.ResourceBundle;

public class DIAssociationContentErrorException extends DIContentErrorException
{
	private static ResourceBundle bundle;

	public DIAssociationContentErrorException(String message)
	{
		super(message);
	}

	public static void createAndThrow(String message, Object ... args) throws DIAssociationContentErrorException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String s = bundle.getString("ERR_1401") + "  " + String.format(message, args);
		throw new DIAssociationContentErrorException(s);
	}

	public static void createAndThrow(long position, String message, Object ... args) throws DIAssociationContentErrorException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String s = bundle.getString("ERR_1401") + "  " + String.format(message, args) + String.format(" Position: %1$d (0x%1$X)", position);
		throw new DIAssociationContentErrorException(s);
	}
}
