package org.gameprototype.endingCode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Created by zhoubo on 15-9-6.
 */

public class ZLibUtils {

	private static Deflater DEFLATER = new Deflater(Deflater.DEFAULT_COMPRESSION);
	private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
	private static final DeflaterOutputStream ZOS = new DeflaterOutputStream(
			OUT, DEFLATER);
	/**
		 * 压缩
		 * 
		 * @param data
		 *            待压缩数据
		 * @return byte[] 压缩后的数据
		 */
		public static byte[] compress(byte[] data) {
			try {
				ZOS.write(data);
				ZOS.finish();
				OUT.close();
				DEFLATER.reset();
				byte[] output=OUT.toByteArray();
				OUT.reset();
				return output;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 压缩
		 * 
		 * @param data
		 *            待压缩数据
		 * 
		 * @param os
		 *            输出流
		 */
		public static ByteBuf compressC(byte[] data) {
			try {
				ZOS.write(data);
				ZOS.finish();
				OUT.close();
				DEFLATER.reset();
				byte[] output=OUT.toByteArray();
				ByteBuf out=ByteBufUtil.threadLocalDirectBuffer();
				out.writeBytes(output);
				OUT.reset();
				return out;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 解压缩
		 * 
		 * @param data
		 *            待压缩的数据
		 * @return byte[] 解压缩后的数据
		 */
		public static byte[] decompress(byte[] data) {
			byte[] output = new byte[0];

			Inflater decompresser = new Inflater();
			decompresser.reset();
			decompresser.setInput(data);

			ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
			try {
				byte[] buf = new byte[1024];
				while (!decompresser.finished()) {
					int i = decompresser.inflate(buf);
					o.write(buf, 0, i);
				}
				output = o.toByteArray();
			} catch (Exception e) {
				output = data;
				e.printStackTrace();
			} finally {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			decompresser.end();
			return output;
		}

		/**
		 * 解压缩
		 * 
		 * @param is
		 *            输入流
		 * @return byte[] 解压缩后的数据
		 */
		public static byte[] decompress(InputStream is) {
			InflaterInputStream iis = new InflaterInputStream(is);
			ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
			try {
				int i = 1024;
				byte[] buf = new byte[i];

				while ((i = iis.read(buf, 0, i)) > 0) {
					o.write(buf, 0, i);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return o.toByteArray();
		}


}
