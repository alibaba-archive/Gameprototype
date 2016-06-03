package org.gameprototype.serializer;

public class SerializationException extends RuntimeException
{
	private static final long serialVersionUID = -2984298659951130021L;

	public SerializationException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public SerializationException(String msg)
	{
		super(msg);
	}
}
