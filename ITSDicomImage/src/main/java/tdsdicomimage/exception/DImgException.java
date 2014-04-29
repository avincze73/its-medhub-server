package tdsdicomimage.exception;

import java.util.ResourceBundle;

public class DImgException extends Exception
{
	private static ResourceBundle bundle;

	public DImgException()
	{
		super();
	}

	public DImgException(String message)
	{
		super(message);
	}

	public DImgException(String message, Throwable th)
	{
		super(message, th);
	}

	public static void createAndThrow(String errCode, Object ... args) throws DImgException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicomimage/exception/DImgExceptionsBundle");
		String message = String.format(bundle.getString(errCode), args);
		throw new DImgException(message);
	}

	public static void createAndThrow(Throwable th, String errCode, Object ... args) throws DImgException
	{
		if(bundle == null) bundle = ResourceBundle.getBundle("tdsdicomimage/exception/DImgExceptionsBundle");
		String message = String.format(bundle.getString(errCode), args);
		throw new DImgException(message, th);
	}
}
