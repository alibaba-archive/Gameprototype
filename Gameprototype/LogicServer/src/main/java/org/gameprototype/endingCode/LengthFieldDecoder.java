/*
 * 2011-8-22 o≧﹏≦o Powered by EXvision
 */

package org.gameprototype.endingCode;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.gameprototype.decode.ByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhoubo on 15-9-6.
 */


/**
 * 抗粘包解码器[4-length][bytes]<br>
 * 注意 这个必须是多例
 * 
 * <pre>
 * MESSAGE FORMAT
 * ==============
 * 
 * Offset:  0        4                   (Length + 4)
 *          +--------+------------------------+
 * Fields:  | Length | Actual message content |
 *          +--------+------------------------+
 * 
 * DECODER IMPLEMENTATION
 * ======================
 * </pre>
 * 
 * @author zhoubo
 * @since 2015-09-06
 */
public class LengthFieldDecoder extends ByteToMessageDecoder
{
	 public static final Logger logger = LoggerFactory
     .getLogger(AMF3NettyDecoder.class);
	private static final int PACKAGE_MAX = 128 * 1024;

	private int packageMax;

	public LengthFieldDecoder()
	{
		this(PACKAGE_MAX);
	}

	public LengthFieldDecoder(int packageMax)
	{
		super();
		this.packageMax = packageMax;
	}

	@Override
	protected void decode(io.netty.channel.ChannelHandlerContext ctx,
			ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 8)
		{
			return;
		}
		in.markReaderIndex();

		// Read the length field.
		byte[] bs=in.readBytes(8).array();
		
		int length = new ByteArray(bs).ReadInt();

		// 效验长度最小值与最大值
		if (length >= packageMax || length <= 0)
		{
			String dump = in.toString(CharsetUtil.UTF_8);
			logger.info("Illegal package size :["
					+ length + "]! Package limit to [0-" + packageMax
					+ "]. DUMP: [" + dump + "] " + ctx.channel());
//
//			throw new IllegalPackageException("Illegal package size :["
//					+ length + "]! Package limit to [0-" + packageMax
//					+ "]. DUMP: [" + dump + "] " + channel);
		}
		// 效验长度最小值与最大值 END

		if (in.readableBytes() < length)
		{
			in.resetReaderIndex();

			return;
		}

		// There's enough bytes in the buffer. Read it.
		ByteBuf frame = in.readBytes(length);

		// Successfully decoded a frame. Return the decoded frame.
		out.add(frame);
		
	}
}
