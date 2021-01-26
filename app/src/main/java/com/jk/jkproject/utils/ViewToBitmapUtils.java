package com.jk.jkproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.FileOutputStream;

public class ViewToBitmapUtils {

    private static Bitmap cachebmp;

    public static String viewSaveToImage(Context mContext, View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        cachebmp = loadBitmapFromView(mContext, view);

        FileOutputStream fos;
        String imagePath = "";
        try {
            // 判断手机设备是否有SD卡
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录
                File sdRoot = Environment.getExternalStorageDirectory();
                File file = new File(sdRoot, Constants.IMAGE_SHARE_SAVE_NAME);
                fos = new FileOutputStream(file);
                imagePath = file.getAbsolutePath();
            } else {
                throw new Exception("创建文件失败!");
            }
            cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos);

            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.e("imagePath=" + imagePath);
        view.destroyDrawingCache();
        return imagePath;
    }

    private static Bitmap loadBitmapFromView(Context mContext, View v) {
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.buildDrawingCache();
        Bitmap source = v.getDrawingCache();
        int w = source.getWidth();
        int h = source.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }


    public static void reset() {
        if (cachebmp != null && !cachebmp.isRecycled()) {
            cachebmp.recycle();
        }
    }

    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile() {
        File sdRoot = Environment.getExternalStorageDirectory();
        File file = new File(sdRoot, Constants.IMAGE_SHARE_SAVE_NAME);
//        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }
}
