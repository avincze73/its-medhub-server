package tdsdicominterface.exception;

public class DIInternalErrorException extends RuntimeException
{
	public DIInternalErrorException(String message, Object ... args)
	{
		super(String.format(message, args));
	}

	public DIInternalErrorException(Throwable th)
	{
		super(th);
	}
}
