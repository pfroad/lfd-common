package com.lfd.frontend.common.file;

import java.io.InputStream;

/**
 * Created by Administrator on 1/3/2017.
 */
public interface FileStorage {
    boolean storeFile(InputStream in, String fileName, Integer mode);
}
