package com.jk.jkproject.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.jk.jkproject.base.AppApplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileUtils {
    private static final int BUFFER = 8192;
    public static String SDPATH = Environment.getExternalStorageDirectory()
            + File.separator + Constants.CRASH_LOG;
    private static String sd_root_path;

    /**
     * 删除缓存文件
     *
     * @param filePath
     */
    public static void deleteTempFile(String filePath) {
        if (FuncUtils.isEmpty(filePath)) {
            return;
        }
        deleteTempFile(new File(filePath));
    }

    /**
     * 删除缓存文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        try {
            updateMediaDB(AppApplication.getInstance(), Uri.fromFile(file));
        } catch (Exception ex) {
        }
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String readTextFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    /**
     * 从流中读取文件
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strbuffer.toString();
    }

    /**
     * 将文本内容写入文件
     *
     * @param file
     * @param str
     * @throws IOException
     */
    public static void writeTextFile(File file, String str) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] buffer = new byte[BUFFER];
            int length;
            while ((length = inBuff.read(buffer)) != -1) {
                outBuff.write(buffer, 0, length);
            }
            outBuff.flush();
        } finally {
            if (inBuff != null) {
                inBuff.close();
            }
            if (outBuff != null) {
                outBuff.close();
            }
        }
    }

    /**
     * 创建文件夹
     *
     * @param context
     * @param dir
     */

    public static void createDir(Context context, String dir) {
        File folderDir = new File(dir);
        if (!folderDir.exists()) {
            folderDir.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param dir
     * @param fileName
     * @return
     */
    public static File createFile(String dir, String fileName) {
        File folderDir = new File(getFilePath(null, dir));
        if (!folderDir.exists()) {
            folderDir.mkdirs();
        }
        String filePath = getFilePath(null, dir) + fileName;
        File fileNew = new File(filePath);
        if (!fileNew.exists()) {
            try {
                fileNew.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return fileNew;
    }

    /**
     * 创建文件用于缓存
     *
     * @param context
     * @param cacheDir
     * @param subDir
     * @param fileName
     * @return
     */
    public static File createFile(Context context, String cacheDir,
                                  String subDir, String fileName) {
        if (!isDirExist(context, cacheDir)) {
            File file = new File(getFilePath(context, cacheDir));
            file.mkdirs();
        }

        String cacheTempDir = getFilePath(context, cacheDir, subDir);
        File fileDir = new File(cacheTempDir);
        if (!fileDir.exists()) {
            File subFile = new File(cacheTempDir);
            subFile.mkdirs();
        }

        String fileTargetDir = cacheTempDir + fileName;
        File fileNew = new File(fileTargetDir);
        if (!fileNew.exists()) {
            try {
                fileNew.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }

        return fileNew;
    }

    /**
     * 获取文件的路径
     *
     * @param context
     * @param fileDir
     * @return
     */
    public static String getFilePath(Context context, String fileDir) {
        return getFilePath(context, fileDir, "");
    }

    /**
     * 获取文件路径
     *
     * @param context
     * @param cacheDir
     * @param subDir
     * @return
     */
    public static String getFilePath(Context context, String cacheDir,
                                     String subDir) {
        String baseDir;
        if (!TextUtils.isEmpty(subDir)) {
            baseDir = getSDCardBasePath(context) + cacheDir + File.separator
                    + subDir + File.separator;
        } else {
            baseDir = getSDCardBasePath(context) + cacheDir + File.separator;
        }
        createDirIfNotExist(baseDir);
        return baseDir;
    }

    /**
     * 获取sdcard根目录
     *
     * @param context
     * @return
     */
    public static String getSDCardBasePath(Context context) {
        if (TextUtils.isEmpty(sd_root_path)) {

            if (hasSDCard() || context == null) {
                sd_root_path = Environment.getExternalStorageDirectory().getPath();
            } else {
                sd_root_path = context.getFilesDir().getPath();
            }
        }
        return sd_root_path;
    }

    /**
     * 获取sdcard根目录
     *
     * @param context
     * @return
     */
    public static String getSDCardBasePathWithSeparetor(Context context) {
        return getSDCardBasePath(context) + File.separator;
    }

    /**
     * 递归删除文件夹（包含根目录）
     *
     * @param path
     */
    public static void deleteDir(String path) {
        deleteDir(path, true);
    }

    /**
     * 递归删除文件
     *
     * @param path         文件夹路径
     * @param isDeleteSelf 是否删除根目录
     */
    public static void deleteDir(String path, boolean isDeleteSelf) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory()) return;

        for (File file : dir.listFiles()) {
            if (file.isFile()) file.delete(); // 删除所有文件
            else if (file.isDirectory()) deleteDir(file.getPath(), true); // 递规的方式删除文件夹
        }
        if (isDeleteSelf) {
            dir.delete();// 删除目录本身
        }
    }


    public static void updateMediaDB(Context context, Uri data) {
        // Mediascanner need to scan for the image saved

        if (context != null && data != null) {
            Intent scanner = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            scanner.setData(data);
            context.sendBroadcast(scanner);
        }
    }

    public static boolean isDirExist(Context context, String cacheDir) {
        File file = new File(getFilePath(context, cacheDir));
        return null != file && file.isDirectory();
    }


    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) return false;
        return isExist(new File(filePath));
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }


    /**
     * 根据传入的文件路径判断此文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isExist(File file) {
        return file != null && file.exists();
    }

    /**
     * 根据传入的文件路径创建文件夹，若不存在则新建
     *
     * @param dirPath
     * @return
     */
    public static File createDirIfNotExist(String dirPath) {
        File file = new File(dirPath);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return System.currentTimeMillis() + "";
        }
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);

    }

    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()) ? true : false;
    }


    // "/storage/sdcard/"
    public static String getSDCardDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    }

    // "/data/data/包名/files/"
    public static String getAppFilesDir(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/";
    }

    // "/data/data/包名/cache/"
    public static String getAppCacheDir(Context context) {
        return context.getCacheDir().getAbsoluteFile() + "/";
    }

    public static void deleteFile(String strFileName) {
        File file = new File(strFileName);
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    public static String readFromAssets(Context context, String fileName) {
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufReader = new BufferedReader(reader);
            String strLine = "";
            String Result = "";
            while ((strLine = bufReader.readLine()) != null)
                Result += strLine;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取到视频的第一帧
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        if (null == videoUrl) {
            videoUrl = "https://ksv-video-publish.cdn.bcebos.com/ef34f63e5cebb4bb6e792964a8304c73058dc119.mp4?auth_key=1613366156-0-0-64544c7118280297e767262d63dd8075";
        }
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
}
