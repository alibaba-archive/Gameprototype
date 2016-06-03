package org.gameprototype.base.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * redis 缓存序列化 反序列化服务
 * @author echo.ch
 * 
 */
public class SerializeUtil {

	public static Object deserialize(byte[] in) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			// logger.warn("Caught IOException decoding %d bytes of data",
			// in == null ? 0 : in.length, e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// logger.warn("Caught CNFE decoding %d bytes of data",
			// in == null ? 0 : in.length, e);
			e.printStackTrace();
		} finally {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rv;
	}
	
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			try {
				os.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rv;
	}
	
	/**对象转byte[]*/
	public static byte[] ObjectToByte(Object obj){
		byte[] bytes=null;
		try{
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
	        oos.writeObject(obj);
	        bytes=out.toByteArray();
	        oos.close();
	        out.close();
		}catch(Exception e){e.printStackTrace();}
		return bytes;
	}
	
	
	/**byte[]转对象*/
	public static Object ByteToObject(byte[] bytes){
		Object obj=null;
		try{
			ByteArrayInputStream in=new ByteArrayInputStream(bytes);
			ObjectInputStream ois=new ObjectInputStream(in);
			obj=ois.readObject();
			in.close();
			ois.close();
		}catch(Exception e){}
		return obj;
	}
}
