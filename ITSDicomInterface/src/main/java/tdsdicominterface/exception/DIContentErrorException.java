package tdsdicominterface.exception;

import java.util.ResourceBundle;

public class DIContentErrorException extends DIException
{
	private static ResourceBundle bundle;

	public DIContentErrorException(String message)
	{
		super(message);
	}

	public static void createAndThrow(long position, String message, Object ... args) throws DIContentErrorException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String s = bundle.getString("ERR_4001") + "  " + String.format(message, args) + String.format(" Position: %1$d (0x%1$X)", position);
		throw new DIContentErrorException(s);
	}
}
