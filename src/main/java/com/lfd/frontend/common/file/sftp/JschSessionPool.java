package com.lfd.frontend.common.file.sftp;

import com.lfd.frontend.common.file.Pool;
import com.jcraft.jsch.Session;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JschSessionPool extends Pool<Session> {

	public JschSessionPool(GenericObjectPoolConfig config,
                           PooledObjectFactory<Session> factory) {
		super(config, factory);
	}
//	public static void main(String[] args) {
//		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
//		config.setMaxTotal(5);
//		config.setMaxWaitMillis(1000);
//		config.setMaxIdle(2);
//		config.setTestOnBorrow(false);
//		config.setTestOnReturn(false);
//
//		Pool<Session> pool = new JschSessionPool(config, new PooledJschSessionFactory());
//
//		Session session = pool.getResource();
//
//		File file = new File("F:\\airparking\\add_and_init_ap_emg_setting.sql");
//		InputStream in = null;
//
//		ChannelSftp sftp = null;
//
//		try {
//			sftp = (ChannelSftp) session.openChannel("sftp");
//			in = new FileInputStream(file);
////			System.out.println(ftp.changeWorkingDirectory("/home/apdev"));
//			sftp.connect();
//
//			sftp.mkdir("/home/apdev/test/");
//
//			sftp.put(in, "/home/apdev/test/test/add_and_init_ap_emg_setting.sql", ChannelSftp.OVERWRITE);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSchException e) {
//			e.printStackTrace();
//		} catch (SftpException e) {
//			e.printStackTrace();
//		} finally {
//			if (sftp != null) {
//				sftp.quit();
//				sftp.disconnect();
//			}
//			if (session != null) {
//				pool.returnResource(session);
//			}
//			if (pool != null) {
//				pool.destroy();
//			}
//		}
//
//	}
}
