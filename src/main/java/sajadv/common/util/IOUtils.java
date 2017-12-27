package sajadv.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import javax.activation.DataHandler;

import org.apache.commons.logging.LogFactory;

public abstract class IOUtils {

	public static String getFileNamePattern(String fileName, String extension) {
		String date = DateUtils.format(new Date(), "yyyy.MM.dd_HH.mm.ss.SSSS");
		StringBuilder path = new StringBuilder();
		path.append(fileName);
		path.append("_");
		path.append(date);
		path.append(".");
		path.append(extension);
		return path.toString();
	}

	/**
	 * 
	 * @param filePath
	 * @param closeStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {
		byte[] bytes = null;
		File file = new File(filePath);
		if (file.exists()) {
			bytes = toByteArray(new FileInputStream(filePath), true);
		}
		return bytes;
	}

	/**
	 * 
	 * @param stream
	 * @param closeStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream stream, boolean closeStream) throws IOException {
		byte[] bytes = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[4096];
		int read = 0;
		while (read != -1) {
			read = stream.read(buffer);
			if (read > 0) {
				baos.write(buffer, 0, read);
			}
		}
		bytes = baos.toByteArray();

		if (closeStream) {
			closeStream(stream);
		}

		return bytes;
	}

	public static byte[] toByteArray(DataHandler data) throws Exception {
		return toByteArray(data.getInputStream(), true);
	}

	public static byte[] toByteArray(URL url) throws Exception {
		return toByteArray(url.openStream(), true);
	}
	
	public static InputStream toInputStream(String stringUrl) throws IOException {
		URL url = new URL(stringUrl);
		return url.openStream();
	}

	public static void write(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[4096];
		int read = 0;
		while (read != -1) {
			read = in.read(buffer);
			if (read > 0) {
				out.write(buffer, 0, read);
			}
		}
	}

	public static void writeFile(InputStream stream, String outputPathFile) throws IOException {
		File file = new File(outputPathFile);
		FileOutputStream out = new FileOutputStream(file);
		try {
			write(stream, out);
		} finally {
			closeStream(out);
		}
	}

	/**
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		if (StringUtils.isNotBlank(filePath)) {
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 
	 * @param stream
	 */
	public static void closeStream(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				LogFactory.getLog(IOUtils.class).error("Erro ao fechar stream.", e);
			}
		}
	}

	/**
	 * 
	 * @param stream
	 */
	public static void closeStream(OutputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				LogFactory.getLog(IOUtils.class).error("Erro ao fechar stream.", e);
			}
		}
	}

	/**
	 * 
	 * @param stream
	 */
	public static void closeStream(Reader stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				LogFactory.getLog(IOUtils.class).error("Erro ao fechar stream.", e);
			}
		}
	}

	/**
	 * 
	 * @param stream
	 */
	public static void closeStream(Writer stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				LogFactory.getLog(IOUtils.class).error("Erro ao fechar stream.", e);
			}
		}
	}

}
