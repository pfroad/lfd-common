package com.lfd.frontend.common.file.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class PooledFtpClientFactory implements PooledObjectFactory<FTPClient> {
	private int port = 21;
	private String host = "10.35.22.60";
	private String username = "apdev";
	private String password = "Yt123456";
	private boolean passiveMode = true;

	public PooledFtpClientFactory() {
	}

	public PooledFtpClientFactory(int port, String host, String username,
								  String password) {
		super();
		this.port = port;
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public void activateObject(PooledObject<FTPClient> pooledFtpClient) throws Exception {

	}

	@Override
	public void destroyObject(PooledObject<FTPClient> pooledFtpClient) throws Exception {
		FTPClient ftpClient = pooledFtpClient.getObject();
		try {
			if (ftpClient != null && ftpClient.isConnected()) {
				ftpClient.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftpClient.disconnect();
		}
	}

	@Override
	public PooledObject<FTPClient> makeObject() throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(host, port);
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
//			logger.warn("FTPServer refused connection");
			return null;
		}
		ftpClient.login(username, password);
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("UTF-8");
		if (passiveMode) {
			ftpClient.enterLocalPassiveMode();
		}
		return new DefaultPooledObject<>(ftpClient);
	}

	@Override
	public void passivateObject(PooledObject<FTPClient> ftpClient) throws Exception {

	}

	@Override
	public boolean validateObject(PooledObject<FTPClient> pooledFtpClient) {
		try {
			return pooledFtpClient.getObject().sendNoOp();
		} catch (Exception e) {
//			logger.error("Failed to validate ftpClient.", e);
			return false;
		}
	}

}
