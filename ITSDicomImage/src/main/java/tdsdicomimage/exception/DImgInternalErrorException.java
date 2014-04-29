package tdsdicomimage.exception;

public class DImgInternalErrorException extends RuntimeException
{
	public DImgInternalErrorException(String message, Object ... args)
	{
		super(String.format(message, args));
	}

	public DImgInternalErrorException(Throwable th)
	{
		super(th);
	}
}
