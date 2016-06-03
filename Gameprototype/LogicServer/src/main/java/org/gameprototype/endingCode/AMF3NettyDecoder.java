package org.gameprototype.endingCode;

import java.lang.Exception;import java.lang.Object;import java.lang.Override;import java.lang.SuppressWarnings;import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.gameprototype.decode.ByteArray;
import org.gameprototype.decode.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhoubo on 15-9-6.
 */

public class AMF3NettyDecoder extends MessageToMessageDecoder<Object>
 {
	
	//SerializationContext serializationContext = new SerializationContext();
	     public static final Logger logger = LoggerFactory
	             .getLogger(AMF3NettyDecoder.class);
	     /**
	      * 
	      * @param maxFrameLength
	      *            包的最大大小
	     * @param lengthFieldOffset
	      *            包头信息，长度的偏移位
	      * @param lengthFieldLength
	      *            包头信息，长度位数
	      */
	
	     /**
	      * 
	     */
		@Override
		protected void decode(ChannelHandlerContext ctx, Object msg,
				List<Object> out) throws Exception {
			ByteBuf frame=(ByteBuf)msg;  
			if (frame == null)
		         {
		             return;
		         }
			ByteArray arr=new ByteArray(frame.readBytes(frame.readableBytes()).array());
	         int type = arr.ReadInt();
	 		int area = arr.ReadInt();
	 		int command = arr.ReadInt();
	//         logger.info("magic type={},area={}", type, area);
	         // 读AMF3字节流的内容
	         Object message =null;
	         if(arr.readnable()){
	        	 byte[] bs=arr.ReadBytes();
	        	 try {
					message= Code.aceDecode(bs);
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	         frame=null;
			@SuppressWarnings({ "rawtypes", "unchecked" })
			SocketModel model = new SocketModel(type, area, command, message);
			out.add(model);
			
		}
	 }