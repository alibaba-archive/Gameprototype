package org.gameprototype.endingCode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.gameprototype.decode.ByteArray;
import org.gameprototype.decode.Code;

import java.io.IOException;

/**
 * Created by zhoubo on 15-9-2.
 */
public class Amf3CodeUtils {

    public static ByteBuf encode(SocketModel model) throws IOException{
        return amf3ChannelBufferEncode(model);
    }

    private static ByteBuf amf3ChannelBufferEncode(SocketModel model) throws IOException{

        ByteBuf out = ByteBufUtil.threadLocalDirectBuffer();
        ByteArray arr=new ByteArray();

        arr.WriteInt(model.getType());
        arr.WriteInt(model.getArea());
        arr.WriteInt(model.getCommand());
        amfEncode(model.getMessage(), arr);
        out.writeBytes(arr.getBuffer());
        arr=null;
        return out;


    }


    private static void amfEncode(Object message, ByteArray out) {
        try {
            byte[] bs= Code.aceEncode(message);
            out.WriteBytes(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
