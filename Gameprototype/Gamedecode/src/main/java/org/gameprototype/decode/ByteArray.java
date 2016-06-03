package org.gameprototype.decode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhoubo on 15-9-6.
 */

public class ByteArray {
	 private List<Byte> bytes = new ArrayList<Byte>();
     public ByteArray()
     {
     }


     public ByteArray(byte[] buffer)
     {
         for (int i = 0; i < buffer.length; i++)
         {
             bytes.add(buffer[i]);
         }
     }


     public int Length;
    

     public int Postion;
     

     
     
     public int getLength() {
    	 Length=bytes.size();
		return Length;
	}


	public void setLength(int length) {
		Length = length;
	}


	public int getPostion() {
		return Postion;
	}


	public void setPostion(int postion) {
		Postion = postion;
	}

     public boolean ReadBoolean()
     {
         byte b = bytes.get(Postion);
         Postion += 1;
         return b == (byte)0 ? false : true;
     }

     public byte ReadByte()
     {
         byte result = bytes.get(Postion);
         Postion += 1;
         return result;
     }

     public void WriteInt(int value)
     {
         byte[] bs = new byte[8];
         int temp=(value >> 24) & 0xff;
         bs[0] = (byte)(temp/127);
         bs[1] = (byte)(temp%127);
         
         temp=(value >> 16) & 0xff;
         bs[2] = (byte)(temp/127);
         bs[3] = (byte)(temp%127);
         
         temp=(value >> 8) & 0xff;
         bs[4] = (byte)(temp/127);
         bs[5] = (byte)(temp%127);

         temp=(value>>0)&0xff;
         bs[6] = (byte)(temp/127);
         bs[7] = (byte)(temp%127);
         for (byte b : bs) {
        	 bytes.add(b);
		}
        
     }

     public int ReadInt()
     {
         byte[] bs = new byte[8];
         for (int i = 0; i < 8; i++)
         {
             bs[i] = bytes.get(i + Postion);
         }
         Postion += 8;
         int g=(int)bs[7]+(int)bs[6]*127;
         int g1=(int)bs[5]+(int)bs[4]*127;
         int g2=(int)bs[3]+(int)bs[2]*127;
         int g3=(int)bs[1]+(int)bs[0]*127;
         int result = g | ((int)g1 << 8) | ((int)g2 << 16) | ((int)g3 << 24);
         return result;
     }

     public String ReadUTFBytes()
     {
         int length = ReadInt();
         if (length <= 0)
             return "";
         byte[] b = new byte[length];
         for (int i = 0; i < length; i++)
         {
             b[i] = bytes.get(i + Postion);
         }
         Postion += length;
         String decodedString = new String(b, Charset.forName("utf-8"));
         return decodedString;
     }
     public void WriteUTFBytes(String value) throws UnsupportedEncodingException
     {
         byte[] bs = value.getBytes("utf-8");
         WriteInt(bs.length);
         for (byte b : bs) {
        	 bytes.add(b);			
		}
     }

     public void WriteBoolean(boolean value)
     {
         bytes.add(value ? ((byte)1) : ((byte)0));
     }
     public void WriteByte(byte value)
     {
         bytes.add(value);
     }
     public void WriteBytes(byte[] value, int offset, int length)
     {
         for (int i = 0; i < length; i++)
         {
             bytes.add(value[i + offset]);
         }
     }
     public void WriteBytes(byte[] value)
     {
    	 for (byte b : value) {
    		 bytes.add(b);			
		}
     }

     public boolean readnable() {
    	 Length=bytes.size();
         return Length > Postion;
     }

     public byte[] ReadBytes()
     {
    	 Length=bytes.size();
         byte[] bs = new byte[Length - Postion];
         for (int i = 0; i < Length-Postion; i++)
         {
             bs[i] = bytes.get(i + Postion);
         }
         Postion = Length;
         return bs;
     }

     public byte[] ReadBytes(int length)
     {
         byte[] bs = new byte[length];
         for (int i = 0; i < length; i++)
         {
             bs[i] = bytes.get(i + Postion);
         }
         Postion += length;
         return bs;
     }
     private float FLOAT_C = 10000.0f;
     private double DOUBLE_C = 100000000.0;
     public double ReadDouble() {
         long value=ReadLong();
         return value/DOUBLE_C;
     }

     public float ReadFloat()
     {
         int value = ReadInt();
         return value / FLOAT_C;
     }

     public long ReadLong()
     {
         byte[] bs = new byte[16];
         for (int i = 0; i < 16; i++)
         {
             bs[i] = bytes.get(i + Postion);
         }
         Postion += 16;
         long g=(long)bs[15]+(long)bs[14]*127;
         long g1=(long)bs[13]+(long)bs[12]*127;
         long g2=(long)bs[11]+(long)bs[10]*127;
         long g3=(long)bs[9]+(long)bs[8]*127;
         long g4=(long)bs[7]+(long)bs[6]*127;
         long g5=(long)bs[5]+(long)bs[4]*127;
         long g6=(long)bs[3]+(long)bs[2]*127;
         long g7=(long)bs[1]+(long)bs[0]*127;
         long result = g | ((long)g1 << 8) | ((long)g2 << 16) | ((long)g3 << 24) | ((long)g4 << 32) | ((long)g5 << 40) | ((long)g6 << 48) | ((long)g7 << 56);
         return result;
     }
     public void WriteDouble(double value)
     {
         long v = (long)(value * DOUBLE_C);
         WriteLong(v);
     }

     public void WriteFloat(float value)
     {
         int v = (int)(value * FLOAT_C);
         WriteInt(v);
     }

     public void WriteLong(long value)
     {
         byte[] bs = new byte[16];
         int temp=(int)(value >> 56)&0xff;
         bs[0] = (byte)(temp/127);
         bs[1] = (byte)(temp%127);
         
         temp=(int)(value >> 48)&0xff;
         bs[2] = (byte)(temp/127);
         bs[3] = (byte)(temp%127);
         
         temp=(int)(value >> 40)&0xff;
         bs[4] = (byte)(temp/127);
         bs[5] = (byte)(temp%127);
         
         temp=(int)(value >> 32)&0xff;
         bs[6] = (byte)(temp/127);
         bs[7] = (byte)(temp%127);
         
         temp=(int)(value >> 24)&0xff;
         bs[8] = (byte)(temp/127);
         bs[9] = (byte)(temp%127);
         
         temp=(int)(value >> 16)&0xff;
         bs[10] = (byte)(temp/127);
         bs[11] = (byte)(temp%127);
         
         temp=(int)(value >> 8)&0xff;
         bs[12] = (byte)(temp/127);
         bs[13] = (byte)(temp%127);

         temp=(int)(value&0xff);
         bs[14] = (byte)(temp/127);
         bs[15] = (byte)(temp%127);
         for (byte b : bs) {
        	 bytes.add(b);			
		}
     }
     
     public byte[] getBuffer(){
    	 byte[] r=new byte[bytes.size()];
    	 int i=0;
    	 for (byte b : bytes) {
			r[i]=b;
			i++;
		}
    	 return r;
     }
}
