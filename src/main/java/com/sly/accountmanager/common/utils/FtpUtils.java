package com.sly.accountmanager.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * ftp工具类
 * 
 * @author sly
 * @time 2018年11月15日
 */
public class FtpUtils {
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(FtpUtils.class);

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 端口号
	 */
	private int port;

	private FTPClient ftpClient = null;

	/**
	 * 构造方法
	 * 
	 * @param userName
	 * @param password
	 * @param ip
	 * @param port
	 */
	public FtpUtils(String userName, String password, String ip, int port) {
		this.userName = userName;
		this.password = password;
		this.ip = ip;
		this.port = port;
	}

	/**
	 * 连接服务器
	 * 
	 * @return
	 * @throws Exception
	 * @author sly
	 * @time 2018年11月15日
	 */
	public boolean connectServer() throws Exception {
		boolean flag = true;
		if (ftpClient == null) {
			ftpClient = new FTPClient();
			//ftp通过ser-u构建，出现的问题
			//ftpClient.configure(new FTPClientConfig("com.xpp.util.filezillapatch.UnixFTPEntryParser"));
			ftpClient.connect(ip, port);

			System.out.println("Connected to " + ip);
			System.out.println(ftpClient.getReplyString());

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
				return false;
			}
			boolean bok = ftpClient.login(userName, password);
			System.out.println("login:" + bok);
			if (!bok) {
				try {
					ftpClient.disconnect();
					ftpClient = null;
				} catch (Exception e) {
				}
				throw new Exception("can not login ftp server");
			}

			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setDataTimeout(120000);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setUseEPSVwithIPv4(false);
		}
		return flag;
	}

	/**
	 * 关闭连接
	 * 
	 * @author sly
	 * @time 2018年11月15日
	 */
	public void closeConnect() {
		try {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {

		}

	}

	/**
	 * 下载文件(直接下载到指定位置)
	 * 
	 * @param remotePath
	 * @param fileName
	 * @param localPath
	 * @return
	 * @throws Exception
	 * @author sly
	 * @time 2018年11月15日
	 */
	public boolean downloadFile(String remotePath, String fileName, String localPath) throws Exception {

		FileOutputStream fos = null;
		try {
			File localFile = new File(localPath, fileName);
			fos = new FileOutputStream(localFile);

			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(remotePath);
			boolean bok = ftpClient.retrieveFile(fileName, fos);

			fos.close();
			fos = null;
			return bok;
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e2) {
				}
			}
		}

	}

	/**
	 * 下载文件(文件名+文件目录 返回流)
	 * 
	 * @param filename
	 * @param ftpPath
	 * @return
	 * @author sly
	 * @time 2018年11月15日
	 */
	public InputStream downloadFile(String filename, String ftpPath) {
		InputStream is = null;
		try {
			is = ftpClient.retrieveFileStream(ftpPath + filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 下载文件(文件路径 返回流)
	 * 
	 * @param filePath
	 * @return
	 * @author sly
	 * @time 2018年11月15日
	 */
	public InputStream downloadFile(String filePath) {
		InputStream is = null;
		try {
			is = ftpClient.retrieveFileStream(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 上传文件
	 * 
	 * @param remotePath
	 * @param filename
	 * @param localFilePath
	 * @return
	 * @throws Exception
	 * @author sly
	 * @time 2018年11月15日
	 */
	public boolean uploadFile(String remotePath, String filename, String localFilePath) throws Exception {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(localFilePath));
			FTPClient.HostnameResolver resolver = new FTPClient.NatServerResolverImpl(ftpClient);
			ftpClient.setPassiveNatWorkaroundStrategy(resolver);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(remotePath);
			System.out.println("changeWorkingDirectory:" + changeWorkingDirectory);
			ftpClient.enterLocalPassiveMode();
			boolean bok = ftpClient.storeFile(filename, fis);
			System.out.println("storeFile:" + bok);
			fis.close();
			fis = null;

			return bok;
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				try {
					fis.close();
					fis = null;
				} catch (Exception e2) {
				}
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param remotePath
	 * @param filename
	 * @return
	 * @throws Exception
	 * @author sly
	 * @time 2018年11月15日
	 */
	public boolean removeFile(String remotePath, String filename) throws Exception {
		ftpClient.changeWorkingDirectory(remotePath);
		boolean bok = ftpClient.deleteFile(filename);
		return bok;
	}
}
