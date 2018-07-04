package com.lfd.frontend.common.file.sftp;

import com.lfd.frontend.common.file.FileStorage;
import com.lfd.frontend.common.file.Pool;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by Administrator on 1/3/2017.
 */
public class FileSftpStorage implements FileStorage {
    private static final Logger logger = LoggerFactory.getLogger(FileSftpStorage.class);

    private Pool<Session> pool;

    @Override
    public boolean storeFile(InputStream in, String fileName, Integer mode) {
        Session session = pool.getResource();

        ChannelSftp sftp = null;

        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();

            logger.debug("Sftp has connected!");

            sftp.put(in, fileName, ChannelSftp.OVERWRITE);

            logger.debug("Done to upload!");
            return true;
        } catch (JSchException | SftpException e) {
            logger.error("Upload file is failed!", e);
            return false;
        } finally {
            if (sftp != null) {
                sftp.quit();
                sftp.disconnect();
            }
            if (session != null) {
                pool.returnResource(session);
            }
        }
    }

    public Pool<Session> getPool() {
        return pool;
    }

    public void setPool(Pool<Session> pool) {
        this.pool = pool;
    }
}