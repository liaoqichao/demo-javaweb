package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class IOUtils {

	/**
	 * 根据输入流,输出字符串。
	 * 
	 * @param is
	 * @return String text
	 */
	public static String toString(InputStream is) {
		String text = "";
		byte[] buf = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buf)) != -1) {
				String str = new String(buf, 0, len);
				text = text + str;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 输入源文件路径和目的文件路径,把源文件写到目的文件
	 * 
	 * @throws IOException
	 */
	public static void outputStreamWrite(String srcPath, String desPath) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(srcPath));
			bos = new BufferedOutputStream(new FileOutputStream(desPath));
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 给一个文件，返回这个文件的字符数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] toByteArray(File file) {
		BufferedInputStream bis = null;
		byte[] buf = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			buf = new byte[bis.available()];
			bis.read(buf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buf;
	}

	/**
	 * 文件复制
	 * 
	 * @param in
	 * @param out
	 */
	public static void copy(InputStream in, OutputStream out) {
		byte[] buf = new byte[1024];
		int len = 0;
		try {
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把文件类型变成Blob类型,以便后面存到数据库
	 * 
	 * @param file
	 * @return
	 */
	public static Blob fileToBlob(File file) {
		byte[] bin_file = toByteArray(file);
		Blob blob = null;
		try {
			blob = new SerialBlob(bin_file);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}

	/**
	 * 把数据库的Blob类型数据保存到本地硬盘
	 * 
	 * @param blob
	 * @param file_path
	 */
	public static void blobToLocalDisk(Blob blob, String file_path) {

		File file = new File(file_path);
		FileOutputStream fos = null;
		InputStream in = null;
		try {
			fos = new FileOutputStream(file);
			in = blob.getBinaryStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
				fos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
