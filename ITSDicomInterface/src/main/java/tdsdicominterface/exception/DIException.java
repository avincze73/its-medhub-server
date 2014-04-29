package tdsdicominterface.exception;

import java.util.ResourceBundle;

public class DIException extends Exception
{
	private static ResourceBundle bundle;
	private String errorCode;

	public DIException()
	{
		super();
	}

	public DIException(String message)
	{
		super(message);
	}

	public DIException(String message, String errCode)
	{
		super(message);
		this.errorCode = errCode;
	}

	public DIException(String message, Throwable th)
	{
		super(message, th);
	}

	public DIException(String message, Throwable th, String errCode)
	{
		super(message, th);
		this.errorCode = errCode;
	}

	public static void createAndThrow(String errCode, Object ... args) throws DIException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String message = String.format(bundle.getString(errCode), args);
		throw new DIException(message, errCode);
	}

	public static void createAndThrow(Throwable th, String errCode, Object ... args) throws DIException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicominterface/exception/DIExceptionsBundle");
		String message = String.format(bundle.getString(errCode), args);
		throw new DIException(message, th, errCode);
	}

	public String getErrorCode()
	{
		return errorCode;
	}
}
