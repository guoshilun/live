package com.jk.jkproject.utils;


import android.hardware.Camera;

public class FlashUtils {
    private static FlashUtils utils;
    private static Camera camera;
    public static boolean mIsOpen = true;

    //使用单例模式在这里初始化相机
    public static FlashUtils getInstance() {
        if (utils == null) {
            utils = new FlashUtils();
        }
        try {
            if (camera == null) {
                camera = Camera.open();
            }
        } catch (Exception e) {
            if (camera != null) {
                camera.release();
            }
            camera = null;
        }
        return utils;
    }

    //参考二维码工具的闪光灯
    public void switchFlash() {
        try {
            Camera.Parameters parameters = camera.getParameters();
            if (mIsOpen) {
//                if (parameters.getFlashMode().equals("torch")) {
//                    return;
//                } else {
//                    parameters.setFlashMode("torch");
//                }
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
                camera.setParameters(parameters);
                camera.startPreview();
            } else {
//                if (parameters.getFlashMode().equals("off")) {
//                    return;
//                } else {
//                    parameters.setFlashMode("off");
//                }
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
                camera.setParameters(parameters);
                camera.stopPreview();
            }
//            camera.setParameters(parameters);
        } catch (Exception e) {
            finishFlashUtils();
        }
        mIsOpen = !mIsOpen;
    }

    //页面销毁的时候调用此方法
    public void finishFlashUtils() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
        camera = null;
    }
}
