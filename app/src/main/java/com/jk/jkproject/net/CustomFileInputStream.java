package com.jk.jkproject.net;


import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.okhttp.progress.listener.UploadListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author: CJ_Xiongj
 * Data: 2016/1/18 12:24
 * Description:
 */
public class CustomFileInputStream extends FileInputStream {
    private UploadListener listener;
    private int total, done;
    private double process;

    public CustomFileInputStream(String path) throws FileNotFoundException {
        super(path);
        available();
    }

    @Override
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        done += byteCount;
        process = 1.0 * done / total;
        if (listener != null) {
            listener.onProgress(done ,total , "", process == 1 ? true : false);
//            listener.onUpload(process);
            LogUtils.d("done:-------"+done + ",process:----------"+process);
        }
        return super.read(buffer, byteOffset, byteCount);
    }

    public void setOnUploadListener(UploadListener listener) {
        this.listener = listener;
    }

    @Override
    public int available() {
        try {
            // 获取文件的总大小
            total = super.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public long getTotal(){
        return total;
    }
}
