package com.lfd.frontend.common.file.ftp;

import com.lfd.frontend.common.file.FileStorage;
import com.lfd.frontend.common.file.Pool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FileFtpStorage implements FileStorage {
	private final static Logger logger = LoggerFactory.getLogger(FileFtpStorage.class);

	private Pool<FTPClient> pool;
	
	public boolean uploadFile(String path, String fileName, InputStream in) {
		FTPClient ftpClient = pool.getResource();
		try {
			if (!ftpClient.changeWorkingDirectory(path)) {
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);
			}
			return ftpClient.storeFile(fileName, in);
		} catch (IOException e) {
			logger.error("Failed to upload file!", e);
			return false;
		} finally {
			if (ftpClient != null)
				pool.returnResource(ftpClient);
		}
	}

	public boolean makeDirectory(String rootPath, String directory) {
		FTPClient ftpClient = pool.getResource();
		 try {
			 String directoryPath = rootPath + "/" + directory;
			 if (ftpClient.changeWorkingDirectory(directoryPath)) {
				return false;
			 }
			 return  ftpClient.makeDirectory(directoryPath);
		 } catch (IOException e) {
			 logger.error("Make directory is failed!", e);
			 return false;
		 } finally {
			 if (ftpClient != null)
				 pool.returnResource(ftpClient);
		 }
	}

	public Pool<FTPClient> getPool() {
		return pool;
	}

	public void setPool(Pool<FTPClient> pool) {
		this.pool = pool;
	}

	@Override
	public boolean storeFile(InputStream in, String fileName, Integer mode) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}

		return uploadFile(fileName.substring(fileName.lastIndexOf("/")),
				fileName.substring(fileName.lastIndexOf("/") + 1),
				in);
	}
}
