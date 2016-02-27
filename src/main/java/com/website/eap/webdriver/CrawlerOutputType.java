package com.website.eap.webdriver;

import com.website.eap.common.util.DateUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.Base64Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhizunbao
 * 
 */
public interface CrawlerOutputType<T> extends OutputType<T> {


	final static String PREFIX_FILE_PATH = "/data/program/code/";

	/**
	 * Obtain the screenshot as base64 data.
	 */
	OutputType<String> BASE64 = new OutputType<String>() {
		public String convertFromBase64Png(String base64Png) {
			return base64Png;
		}

		public String convertFromPngBytes(byte[] png) {
			return new Base64Encoder().encode(png);
		}
	};

	/**
	 * Obtain the screenshot as raw bytes.
	 */
	OutputType<byte[]> BYTES = new OutputType<byte[]>() {
		public byte[] convertFromBase64Png(String base64Png) {
			return new Base64Encoder().decode(base64Png);
		}

		public byte[] convertFromPngBytes(byte[] png) {
			return png;
		}
	};

	/**
	 * Obtain the screenshot into a temporary file that will be deleted once the
	 * JVM exits. It is up to users to make a copy of this file.
	 */
	OutputType<File> FILE = new OutputType<File>() {
		public File convertFromBase64Png(String base64Png) {
			return save(BYTES.convertFromBase64Png(base64Png));
		}

		public File convertFromPngBytes(byte[] data) {
			return save(data);
		}

		private File save(byte[] data) {
			OutputStream stream = null;

			try {
				String FILE_PATH=PREFIX_FILE_PATH+ DateUtil.getTodayString()+"/";
				File tmpPath = new File(FILE_PATH);
				if (!tmpPath.exists()){
					tmpPath.mkdirs();
				}
				File tmpFile = File.createTempFile("screenshot", ".png",
						tmpPath);
				tmpFile.deleteOnExit();
				stream = new FileOutputStream(tmpFile);
				stream.write(data);
				return tmpFile;
			} catch (IOException e) {
				throw new WebDriverException(e);
			}catch (Exception e){
				throw new WebDriverException(e);
			}finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						// Nothing sane to do
					}
				}
			}
		}
	};

	/**
	 * Convert the given base64 png to a requested format.
	 * 
	 * @param base64Png
	 *            base64 encoded png.
	 * @return png encoded into requested format.
	 */
	T convertFromBase64Png(String base64Png);

	/**
	 * Convert the given png to a requested format.
	 * 
	 * @param png
	 *            an array of bytes forming a png file.
	 * @return png encoded into requested format.
	 */
	T convertFromPngBytes(byte[] png);
}
