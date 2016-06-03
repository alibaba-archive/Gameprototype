package org.gameprototype.endingCode;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.MessageToByteEncoder;
import org.gameprototype.decode.ByteArray;

/**
 * Created by zhoubo on 15-9-9.
 */
public class LengthFieldEncoder extends MessageToByteEncoder<Object> {


    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx,
                          Object msg, ByteBuf out) throws Exception {
        ByteBuf body = (ByteBuf) msg;

        //	ByteBuf header = ByteBufUtil.threadLocalDirectBuffer();

        int length = body.readableBytes();
        ByteArray arr=new ByteArray();
        arr.WriteInt(length);
        arr.WriteBytes(body.readBytes(length).array());
        out.writeBytes(arr.getBuffer());
        body.resetReaderIndex();
        //out=header;

    }

}
