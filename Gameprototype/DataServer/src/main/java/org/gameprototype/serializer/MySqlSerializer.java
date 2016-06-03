package org.gameprototype.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MySqlSerializer


{

	public static final byte[] EMPTY_ARRAY = new byte[0];
	
	public static boolean isEmpty(byte[] data)
	{
		return (data == null || data.length == 0);
	}
	
	public static byte[] serialize(Object object) throws SerializationException
	{
		if (object == null)
		{
			return EMPTY_ARRAY;
		}
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
		try
		{
			if (!(object instanceof Serializable))
			{
				throw new IllegalArgumentException(MySqlSerializer.class.getClass().getSimpleName()
						+ " requires a Serializable payload "
						+ "but received an object of type ["
						+ object.getClass().getName() + "]");
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
			return byteStream.toByteArray();
		}
		catch (Throwable ex)
		{
			throw new SerializationException("Failed to serialize object", ex);
		}
	}

	
	public static Object deserialize(byte[] bytes) throws SerializationException
	{
		if (isEmpty(bytes))
		{
			return null;
		}

		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		try
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(
					byteStream);
			Object obj = objectInputStream.readObject();
			objectInputStream.close();
			return obj;
		}
		catch (Throwable ex)
		{
			throw new SerializationException(
					"Failed to deserialize object type", ex);
		}
	}

}
