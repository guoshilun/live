package com.jk.jkproject.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import com.anbetter.log.MLog;
import com.jk.jkproject.base.AppApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 获取手机信息工具类<br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 */
public class DeviceUtils {


    /**
     * 获取应用程序的IMEI号
     */
    public static String getDeviceId() {
        UUID uuid;
        String device_id = "";
        try {
//            TelephonyManager telecomManager = (TelephonyManager) AppApplication.getInstance()
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            String imei = telecomManager.getDeviceId() + "";//可能为15个0
//            String androidid = android.provider.Settings.Secure.getString(
//                    AppApplication.getInstance().getContentResolver(),
//                    android.provider.Settings.Secure.ANDROID_ID) + "";
//            String simNum = telecomManager.getSimSerialNumber() + "";
//            uuid = new UUID(androidid.hashCode(), ((long) imei.hashCode() << 32 | simNum.hashCode()));
//            device_id = uuid.toString();
            device_id = getUniquePsuedoID2();
//            Log.e("uuid:", "IMEI标识(DeviceId)：" + device_id + " , uuid:" + device_id);
            if (TextUtils.isEmpty(device_id)) {

//                Log.e("DeviceId", device_id);
            }
            return device_id;
        } catch (Exception e) {
            device_id = getUniquePsuedoID2();
//            Log.e("Catch DeviceId", device_id);
            return device_id;
        }
    }

    /**
     * 获取设备的系统版本号
     */
    public static int getDeviceSDK() {
        int sdk = Build.VERSION.SDK_INT;
        MLog.i("设备版本：" + sdk);
        return sdk;
    }

    /**
     * 获取设备的型号
     */
    public static String getDeviceName() {
        String model = Build.MODEL;
        MLog.i("设备型号：" + model);
        return model;
    }

    /**
     * 获取设备号，(友盟测试时 ，后台需要配置的测试设备)
     *
     * @param context
     * @return JSON string
     */
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) AppApplication.getInstance()
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

//			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager)  AppApplication.getInstance().getSystemService(Context.WIFI_SERVICE);
            WifiManager wifi = (WifiManager) AppApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取设备当前联网 IP地址
     *
     * @return
     */
    public static String getDeviceCurrentIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return "";
    }


    public static String getUniquePsuedoID2() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = ""; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}