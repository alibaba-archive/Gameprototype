package org.gameprototype.endingCode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.gameprototype.decode.ByteArray;
import org.gameprototype.decode.Code;

import java.util.List;

/**
 * Created by zhoubo on 15-9-9.
 */
public class AMF3NettyEncoder extends MessageToMessageEncoder<Object> {


    private void amfEncode(Object message, ByteArray out) {
        try {
            byte[] bs= Code.aceEncode(message);
            out.WriteBytes(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx, Object msg,
                          List<Object> out) throws Exception {
        if (msg instanceof ByteBuf) {
            out.add(((ByteBuf) msg).copy());
        } else {
            @SuppressWarnings("rawtypes")
            SocketModel model = (SocketModel) msg;
            ByteBuf buf = ByteBufUtil.threadLocalDirectBuffer();
            ByteArray arr=new ByteArray();

            arr.WriteInt(model.getType());
            arr.WriteInt(model.getArea());
            arr.WriteInt(model.getCommand());
            amfEncode(model.getMessage(), arr);
            buf.writeBytes(arr.getBuffer());
            arr=null;
            out.add(buf);
        }

    }
}
