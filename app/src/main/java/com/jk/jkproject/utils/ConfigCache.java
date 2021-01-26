package com.jk.jkproject.utils;

import android.os.Environment;

import com.anbetter.log.MLog;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.NetUtils;

import java.io.File;
import java.io.IOException;

public class ConfigCache {
    public static final int CONFIG_CACHE_MOBILE_TIMEOUT = 3600000; // 1 hour
    public static final int CONFIG_CACHE_WIFI_TIMEOUT = 300000; // 5 minute
    public static final int CONFIG_CACHE_SIGN_TIMEOUT = 6000000; // 10 minute
    private static final String TAG = ConfigCache.class.getName();
    public static int mNetWorkState = NetUtils.NETWORK_TYPE_DISCONNECT;

    public static String getUrlCache(String url) {
        if (url == null) {
            return null;
        }

        String result = null;
        File file = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants.CACHE_FOLDER + StringUtils.replaceUrlWithPlus(url));
        if (file.exists() && file.isFile()) {
            long expiredTime = System.currentTimeMillis() - file.lastModified();
            MLog.d(TAG, file.getAbsolutePath() + " expiredTime:" + expiredTime / 60000 + "min");
            // 1. in case the system time is incorrect (the time is turn back
            // long ago)
            // 2. when the network is invalid, you can only read the cache
            mNetWorkState = NetUtils.getNetworkType(AppApplication.getInstance());
            if (mNetWorkState != NetUtils.NETWORK_TYPE_DISCONNECT && expiredTime < 0) {
                return null;
            }
            if (mNetWorkState == NetUtils.NETWORK_TYPE_WIFI && expiredTime > CONFIG_CACHE_WIFI_TIMEOUT) {
                return null;
            } else if (mNetWorkState == NetUtils.NETWORK_TYPE_MOBILE && expiredTime > CONFIG_CACHE_MOBILE_TIMEOUT) {
                return null;
            }
            try {
                result = FileUtils.readTextFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取上传凭证缓存 有效期十分钟
     *
     * @param url
     * @return
     */
    public static String getSignCache(String url) {
        if (url == null) {
            return null;
        }
        String result = null;
        File file = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants.CACHE_FOLDER + StringUtils.replaceUrlWithPlus(url));
        if (file.exists() && file.isFile()) {
            long expiredTime = System.currentTimeMillis() - file.lastModified();
            if (expiredTime <= CONFIG_CACHE_SIGN_TIMEOUT) {
                try {
                    result = FileUtils.readTextFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 缓存一直有效的
     *
     * @param url
     * @return
     */
    public static String getUrlCacheDefault(String url) {
        if (url == null) {
            return null;
        }

        String result = null;
        File file = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants
                .CACHE_FOLDER + StringUtils.replaceUrlWithPlus(url));
        if (file.exists() && file.isFile()) {
            try {
                result = FileUtils.readTextFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void setUrlCache(String data, String url) {
        if (FileUtils.getSDCardBasePath(AppApplication.getInstance()) == null) {
            return;
        }
        File dir = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants.CACHE_FOLDER);
        if (!dir.exists() && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir.mkdirs();
        }
        File file = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants
                .CACHE_FOLDER + StringUtils.replaceUrlWithPlus(url));
        if (file.exists()) {
            file.delete();
        }

        if (data == null) {
            return;
        }

        try {
            // 创建缓存数据到磁盘，就是创建文件
            FileUtils.writeTextFile(file, data);
        } catch (IOException e) {
            MLog.i(TAG, "write " + file.getAbsolutePath() + " data failed!");
            e.printStackTrace();
        }
    }

    public static void delCache(String url) {
        if (FileUtils.getSDCardBasePath(AppApplication.getInstance()) == null) {
            return;
        }
        File file = new File(FileUtils.getSDCardBasePath(AppApplication.getInstance()) + File.separator + Constants.CACHE_FOLDER + StringUtils.replaceUrlWithPlus(url));
        if (file.exists()) {
            file.delete();
        }
    }
}
