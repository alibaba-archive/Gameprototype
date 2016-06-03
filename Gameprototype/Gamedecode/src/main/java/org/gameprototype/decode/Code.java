package org.gameprototype.decode;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zhoubo on 15-9-6.
 */


public class Code {
	 /**
    	反序列化
	 * @throws Exception 
    */
   public static Object aceDecode(byte[] data) throws Exception 
   {

           Object t = decode(data);
           return t;

   }
   
   /**
    反序列化为指定类型对象
 * @throws Exception 
    */
//   @SuppressWarnings("unchecked")
//public  T aceDecode(byte[] data,boolean b) throws Exception
//   {
//	   a=(T)decode(data);
//       return a;
//   }
   /**
    反序列化类型分析
 * @throws Exception 
    */
   private static Object decode(byte[] data) throws Exception 
   {
       ByteArray arr = new ByteArray(data);
       int type = arr.ReadInt();
       switch (type) { 
           case TypeCode.ARRAY://数组
               return arrayDecode(arr.ReadBytes(arr.ReadInt()));
           case TypeCode.BYTE:// byte
               return arr.ReadByte();
           case TypeCode.DATE://时间
              return dateDecode(arr.ReadLong());
           case TypeCode.DOUBLE://double
               return arr.ReadDouble();
           case TypeCode.FLOAT://float
               return arr.ReadFloat();
           case TypeCode.INT://int
               return arr.ReadInt();
           case TypeCode.LONG://long
               return arr.ReadLong();
           case TypeCode.OBJECT://对象
               return objDecode(arr.ReadBytes(arr.ReadInt()));
           case TypeCode.STRING://字符串
               return arr.ReadUTFBytes();
           case TypeCode.NULL:
               return null;
           case TypeCode.BOOLEAN:
               return arr.ReadBoolean();
           default:
               throw new Exception("decode value type is undefine");
       }
   
   }
   
   /// <summary>
   /// 序列化
   /// </summary>
   /// <param name="data">需要序列化的对象</param>
   /// 目前支持类型
   /// int double float long byte string boolean object array null
   /// 注：float精确到小数点后4位 double精确到小数点后8位
   /// <returns></returns>
   public static byte[] aceEncode(Object data) throws Exception {
       ByteArray arr = new ByteArray();
       writeType(data, arr);
       encode(data, arr);
       return arr.getBuffer();
   }

   /**
    序列化
 * @throws java.io.UnsupportedEncodingException
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
    */
   private static void encode(Object data,ByteArray arr) throws Exception {
       if (data == null) return;
       if(data instanceof Boolean){
           arr.WriteBoolean((Boolean)data);
       }
       else if (data instanceof Integer) {
           arr.WriteInt((Integer)data);
       }
       else if (data instanceof Long) {
           arr.WriteLong((Long)data);
       }
       else if (data instanceof Double)
       {
           arr.WriteDouble((Double)data);
       }
       else if (data instanceof Float)
       {
           arr.WriteFloat((Float)data);
       }
       else if (data instanceof Byte)
       {
           arr.WriteByte((Byte)data);
       }
       else if (data.getClass().isArray())
       {
           arrayEncode((Object[])data, arr);
       }
       else if (data instanceof String)
       {
           arr.WriteUTFBytes((String)data);
       }
       else if (data instanceof Date) {
           dateEncode((Date)data, arr);
       }
       else
       {
           //obj
           objectEncode(data, arr);
       }
   }

   private static void writeType(Object obj,ByteArray arr) {
       if (obj == null) {
           arr.WriteInt(TypeCode.NULL);
       }
       else if (obj instanceof Boolean) {
           arr.WriteInt(TypeCode.BOOLEAN);
       }else
       if (obj instanceof Integer)
       {
           arr.WriteInt(TypeCode.INT);
       }
       else if (obj instanceof Long) {
           arr.WriteInt(TypeCode.LONG);
       }
       else if (obj instanceof Double)
       {
           arr.WriteInt(TypeCode.DOUBLE);
       }
       else if (obj instanceof Float)
       {
           arr.WriteInt(TypeCode.FLOAT);
       }
       else if (obj instanceof Byte)
       {
           arr.WriteInt(TypeCode.BYTE);
       }
       else if (obj.getClass().isArray())
       {
           arr.WriteInt(TypeCode.ARRAY);
       }
       else if (obj instanceof String)
       {
           arr.WriteInt(TypeCode.STRING);
       }
       else if (obj instanceof Date) {
           arr.WriteInt(TypeCode.DATE);
       }
       else
       {
           //obj
           arr.WriteInt(TypeCode.OBJECT);
       }
   }

   private static void arrayEncode(Object[] array, ByteArray arr) throws Exception
   {
       ByteArray temp = new ByteArray(); 
     /*  String fName=array.getClass().getSimpleName();
       fName=fName.substring(0,fName.length()-2);
       temp.WriteUTFBytes(fName);*/
       int i = 0;
       for (Object obj : array) {
           if (i == 0) {
               writeType(obj, temp);
        
               i++;
           }
           encode(obj, temp);
       }
       arr.WriteInt(temp.getLength());
       arr.WriteBytes(temp.getBuffer());
   }
   private static void objectEncode(Object obj, ByteArray arr) throws Exception
   {
       ByteArray temp = new ByteArray();
       temp.WriteUTFBytes(obj.getClass().getName());
       
       Field[] fields= obj.getClass().getDeclaredFields();
       
       for (Field field : fields) {
    	   field.setAccessible(true);
    	   String fieldName= field.getName();
           temp.WriteUTFBytes(fieldName);           
           writeType(field.get(obj), temp);
           encode(field.get(obj), temp);
       }
       arr.WriteInt(temp.getLength());
       
       arr.WriteBytes(temp.getBuffer());
   }
   /**
    解析数组
 * @throws Exception 
    */
   private static Object arrayDecode(byte[] data) throws Exception {
       
       ByteArray arr=new ByteArray(data);
    //   String arrayType= arr.ReadUTFBytes();
      // arrayType=typeConv(arrayType);
       if (!arr.readnable())
       {
           return null;
       }
       int listType = arr.ReadInt();//获取list中存储对象的类型
       List<Object> list=new ArrayList<Object>();
       switch (listType)
       {
           case TypeCode.ARRAY://数组
               while (arr.readnable()) {
                   int arrayLength = arr.ReadInt();
                   list.add(arrayDecode(arr.ReadBytes(arrayLength)));
               }
               break;
           case TypeCode.BYTE:// byte
               while (arr.readnable())
               {
                   list.add(arr.ReadByte());
               }
               break;
           case TypeCode.DATE://时间
               while (arr.readnable())
               {
                   list.add(dateDecode( arr.ReadLong()));
               }
               break;
           case TypeCode.DOUBLE://double
               while (arr.readnable())
               {
                   list.add(arr.ReadDouble());
               }
               break;
               
           case TypeCode.FLOAT://float
               while (arr.readnable())
               {
                   list.add(arr.ReadFloat());
               }
               break;
           case TypeCode.INT://int
               while (arr.readnable())
               {
                   list.add(arr.ReadInt());
               }
               break;
           case TypeCode.LONG://long
               while (arr.readnable())
               {
                   list.add(arr.ReadLong());
               }
               break;
               
           case TypeCode.OBJECT://对象
               while (arr.readnable()) {
                   int objLength = arr.ReadInt();
                   list.add(objDecode(arr.ReadBytes(objLength)));
               }
               break;
           case TypeCode.STRING://字符串
               while (arr.readnable())
               {
                   list.add(arr.ReadUTFBytes());
               }
               break;
           case TypeCode.BOOLEAN:
               while (arr.readnable())
               {
                   list.add(arr.ReadBoolean());
               }
               break;
           default:
               throw new Exception("decode array type is undefine");
       }
       Class<?> t=list.get(0).getClass();
       
       Object ar1 = Array.newInstance(t, list.size());
       System.arraycopy(list.toArray(), 0, ar1, 0, list.size());
           return ar1;
   }
   /**
    解析对象
 * @throws Exception 
    */
   private static Object objDecode(byte[] data) throws Exception {
       ByteArray arr = new ByteArray(data);
       String calssName= arr.ReadUTFBytes();
       Class<?> t= Class.forName(calssName);
        Object obj= t.newInstance();
       while (arr.readnable()) {
           String fieldName = arr.ReadUTFBytes();
           int fieldType = arr.ReadInt();
           Field fi = t.getField(fieldName);
           if (fi == null) throw new Exception("decode object is not field");
           fi.setAccessible(true);
           switch (fieldType) {
               case TypeCode.ARRAY://数组
                      int arrayLength = arr.ReadInt();   
                   Object objs=arrayDecode( arr.ReadBytes(arrayLength));
                      fi.set(obj, objs); 
                   break;
               case TypeCode.BYTE:// byte
                   fi.set(obj,arr.ReadByte());
                   break;
               case TypeCode.DATE://时间
                    fi.set(obj,dateDecode(arr.ReadLong()));
                   break;
               case TypeCode.DOUBLE://double
                   fi.set(obj,arr.ReadDouble());                        
                   break;

               case TypeCode.FLOAT://float
                   fi.set(obj,arr.ReadFloat());                        
                   break;
               case TypeCode.INT://int
                   fi.set(obj,arr.ReadInt());                        
                   break;
               case TypeCode.LONG://long
                   fi.set(obj,arr.ReadLong());                        
                   break;

               case TypeCode.OBJECT://对象
                       int objLength = arr.ReadInt();
                       fi.set(obj,objDecode( arr.ReadBytes(objLength)));
                   
                   break;
               case TypeCode.STRING://字符串
                   fi.set(obj,arr.ReadUTFBytes());                        
                   break;
               case TypeCode.NULL:
                   fi.set(obj, null);
                   break;
               case TypeCode.BOOLEAN:
                   fi.set(obj, arr.ReadBoolean());
                   break;
               default:
                   throw new Exception("decode object type is undefine");
           }
       }
       return obj;
   }
   /**
    解析时间
    */
   private static Object dateDecode(long date) {
       Date d = new Date(date);
       return d;
   }

   private static void dateEncode(Date date,ByteArray arr) {
       arr.WriteLong( date.getTime());
   }
   
//   private static String typeConv(String name){
//	   name=name.replaceAll("System.Int32", "java.lang.Integer");
//
//	   name= name.replaceAll("System.Double", "java.lang.Double");
//	   name= name.replaceAll("System.Single", "java.lang.Float");
//	   name= name.replaceAll("System.Int64", "java.lang.Long");
//	   name= name.replaceAll("System.String", "java.lang.String");
//	   return name;
//   }
}
