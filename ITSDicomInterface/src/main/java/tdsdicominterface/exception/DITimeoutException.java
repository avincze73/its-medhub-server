package tdsdicominterface.exception;

import java.util.ResourceBundle;

public class DITimeoutException extends DIException
{
	private static ResourceBundle bundle;

	public DITimeoutException(String message)
	{
		super(message);
	}

	public static void createAndThrow(String errCode, Object ... args) throws DITimeoutException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String message = String.format(bundle.getString(errCode), args);
		throw new DITimeoutException(message);
	}
}
