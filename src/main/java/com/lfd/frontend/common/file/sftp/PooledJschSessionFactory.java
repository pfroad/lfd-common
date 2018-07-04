package com.lfd.frontend.common.file.sftp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PooledJschSessionFactory implements PooledObjectFactory<Session> {
	private static final Logger logger = LoggerFactory.getLogger(PooledJschSessionFactory.class);

	private int port = 22;
	private String host = "10.35.22.60";
	private String username = "apdev";
	private String password = "Yt123456";
	private int timeout = 60000;
	
	public PooledJschSessionFactory() {
	}

	public PooledJschSessionFactory(int port, String host, String username,
                                    String password) {
		super();
		this.port = port;
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public void activateObject(PooledObject<Session> pooledJschSession) throws Exception {
		
	}

	public void destroyObject(PooledObject<Session> pooledJschSession) throws Exception {
		Session session = pooledJschSession.getObject();

		if (session != null && session.isConnected()) {
			session.disconnect();
		}
	}

	public PooledObject<Session> makeObject() throws Exception {
		JSch jSch = new JSch();

		Session session = jSch.getSession(username, host, port);
		if (StringUtils.isNotEmpty(password)) {
			session.setPassword(password);
		}

		Properties config = new Properties();
		config.setProperty("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setTimeout(timeout);
		session.connect();

		logger.debug("Session has connected!");
		return new DefaultPooledObject<>(session);
	}

	public void passivateObject(PooledObject<Session> jschSession) throws Exception {
		
	}

	public boolean validateObject(PooledObject<Session> pooledJschSession) {
		try {
			pooledJschSession.getObject().sendIgnore();
			return true;
		} catch (Exception e) {
			logger.debug("SFTP session was closed. Ignoring this exception.", e);
			return false;
		}
	}

}
