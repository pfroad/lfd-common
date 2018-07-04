package com.lfd.frontend.common.file.ftp;

import com.lfd.frontend.common.file.Pool;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class FtpClientPool extends Pool<FTPClient> {

	public FtpClientPool(GenericObjectPoolConfig config,
			PooledObjectFactory<FTPClient> factory) {
		super(config, factory);
	}
}
