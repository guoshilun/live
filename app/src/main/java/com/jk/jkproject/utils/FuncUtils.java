package com.jk.jkproject.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.ui.entity.MediaInfo;

import org.json.JSONArray;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncUtils {

    /**
     * 生成验证码签名
     *
     * @return
     */
    public static String encryptCheckCode(String phone, String time) {
        String code = "sadfkvj34";
        String sigin = phone + time + code;
        return MD5Tools.toMD5(sigin);
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isAppisBackground(final Context context) {
        ActivityManager am = (ActivityManager) AppApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTopActivy(String cmdName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(Integer.MAX_VALUE);
        String cmpNameTemp = null;
        if (null != runningTaskInfos) {
            cmpNameTemp = (runningTaskInfos.get(0).topActivity.getClassName()).toString();
        }

        if (null == cmpNameTemp) {
            return false;
        }

        return cmpNameTemp.equals(cmdName);

    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) AppApplication.getInstance().getSystemService(Context
                .ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 重启应用
     *
     * @param ct
     */
    public static void restartApplication(Context ct) {
        final Intent intent = ct.getPackageManager().getLaunchIntentForPackage(ct.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ct.startActivity(intent);
    }

    /**
     * 判断 ：默认为手机号匹配
     *
     * @param format  正则式
     * @param content 内容
     * @return true 符合条件
     */
    public static boolean match(String format, String content) {
        if (TextUtils.isEmpty(format)) {
            format = RegularConstants.FORMAT_PHONE;
        }
        try {
            Pattern pattern = Pattern.compile(format);
            Matcher match = pattern.matcher(content);
            // LogUtils.e("匹配结果：======="+match.matches());
            return match.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否为空
     *
     * @param v
     * @return
     */
    public static boolean isEmpty(TextView v) {
        if (TextUtils.isEmpty(v.getText())) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param v
     * @return
     */
    public static boolean isEmpty(EditText v) {
        if (TextUtils.isEmpty(v.getText())) {
            v.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(CharSequence string) {
        if (TextUtils.isEmpty(string) || "null".equalsIgnoreCase(string.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(Object[] list) {
        if (null == list || list.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || TextUtils.isEmpty(obj.toString()) || "null".equalsIgnoreCase(obj.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 获取内容
     *
     * @param v
     * @return
     */
    public static String getText(TextView v) {
        return TextUtils.isEmpty(v.getText()) ? "" : v.getText().toString().trim();
    }

    private static long last;

    /**
     * 判断按钮两次点击时间间隔是否大于0.5s return true是快速点击
     */
    public static boolean isFastDoubleClick(View v) {
        if (null == v) {
            return false;
        }
        long time = System.currentTimeMillis();
        if (FuncUtils.isEmpty(v.getTag())) {
            v.setTag(time);
            return false;
        }
        long lastClickTime = (Long) (v.getTag());
        v.setTag(time);
        if (time - lastClickTime < 500) {
            return true;
        }
        return false;
    }

    /**
     * 是否已安装某个应用
     *
     * @param ct
     * @param targetPakege
     * @return
     */
    public static boolean isInstallByread(Context ct, String targetPakege) {
        if (targetPakege == null || "".equals(targetPakege)) return false;
        PackageInfo packageInfo;
        try {
            packageInfo = ct.getPackageManager().getPackageInfo(targetPakege, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }

        return packageInfo == null ? false : true;

    }

    public static String unicodeToUTF(String unicodeStr) {
        char aChar;
        int len = unicodeStr.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = unicodeStr.charAt(x++);
            if (aChar == '\\') {
                aChar = unicodeStr.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = unicodeStr.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') aChar = '\t';
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 生成随机数组
     *
     * @param arrLength 数组长度
     * @param max       限制
     * @return
     */
    public synchronized static Set<Integer> random(int arrLength, int max) {
        Set<Integer> arr = new HashSet<Integer>();
        while (arr.size() < arrLength) {
            arr.add((int) (Math.random() * max));
        }

        return arr;
    }

    /**
     * 生成随机数
     *
     * @param max 限制
     * @return
     */
    public synchronized static int random(int max) {
        return ((int) (Math.random() * max));
    }

    public static String takePhotos(Activity activity, int requestCode) {
        File file = getOutputMediaFile();
        if (file == null) {
            return null;
        }

        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(openCameraIntent, requestCode);
        return file.getAbsolutePath();
    }

    public static String takePhotos(Activity activity, Class<?> cls, int requestCode) {
        File file = getOutputMediaFile();
        if (file == null) {
            return null;
        }

        Intent openCameraIntent = new Intent(activity, cls);
        openCameraIntent.putExtra(Constants.PATH, Uri.fromFile(file));
        activity.startActivityForResult(openCameraIntent, requestCode);
        return file.getAbsolutePath();
    }

    public static File getOutputMediaFile() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                ToastUtils.showShortToast("未找到存储卡，无法存储照片！");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(dir.getAbsolutePath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }


    /**
     * 第一个字符是1.5倍，中间是2.5倍
     *
     * @param str
     * @return
     */
    public static SpannableString getDialogGiftCharisma(CharSequence str) {
        if (isEmpty(str)) return null;
        SpannableString curStr = new SpannableString(str);
        curStr.setSpan(new RelativeSizeSpan(1.5f), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        curStr.setSpan(new RelativeSizeSpan(2.5f), 1, str.length() - 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return curStr;
    }

    public static SpannableString getBalanceSpannableString(CharSequence str, int start, int end, float scale) {
        if (isEmpty(str)) return null;
        SpannableString curStr = new SpannableString(str);
        curStr.setSpan(new RelativeSizeSpan(scale), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return curStr;
    }


    public static SpannableString getPhoneColorSpannable(String phone) {
        if (isEmpty(phone)) return null;
        SpannableString curStr = new SpannableString(String.format(AppApplication.getInstance().getString(R.string.register_2_tips), phone));
        curStr.setSpan(new ForegroundColorSpan(AppApplication.getInstance().getResources().getColor(R.color.app_red_color))
                , curStr.length() - phone.length() - 3, curStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return curStr;
    }

    public static String getJsonArray(ArrayList<String> photos) {
        StringBuilder json = new StringBuilder();
        for (int i = 0, size = photos.size(); i < size; i++) {
            if (i == 0) json.append("[\"");
            json.append(photos.get(i));
            if (i == size - 1) {
                json.append("\"]");
            } else {
                json.append("\",\"");
            }

        }
        return json.toString();
    }

    public static ArrayList<MediaInfo> arrToList(String arr) {
        if (isEmpty(arr)) return null;
        ArrayList<MediaInfo> list = new ArrayList<>();
        try {
            JSONArray jarr = new JSONArray(arr);
            for (int i = 0, size = jarr.length(); i < size; i++) {
                list.add(GsonUtils.get().fromJson(jarr.getString(i), MediaInfo.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> arrToStringList(String arr) {
        if (isEmpty(arr)) return null;
        ArrayList<String> list = null;
        arr = arr.replaceAll("\\[", "");
        arr = arr.replaceAll("\\]", "");
        arr = arr.replaceAll(" ", "");
        String[] ss = arr.split(",");
        list = new ArrayList<>(Arrays.asList(ss));

        return list;
    }

    /**
     * 获取当前屏幕截图
     *
     * @param activity
     * @return
     */
//    public static Bitmap getScreenBitmap(Activity activity) {
//        //View是你需要截图的View
//        View view = activity.getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap b1 = view.getDrawingCache();
//        // 获取状态栏高度 /
//        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
//        // 获取屏幕长和高
//        int width = DensityUtils.SCREEN_WIDTH_PIXELS;
//        int screenheight = DensityUtils.SCREEN_HEIGHT_PIXELS;
//        int height = SPUtils.getInt("screen_height", screenheight);
//        // 去掉标题栏
//        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, (height - statusBarHeight));
//        view.destroyDrawingCache();
//        return b;
//    }

//    public static void startLoacation() {
//        LocationClient mLocationClient = AppApplication.getInstance().mLocationClient;
//        if (!mLocationClient.isStarted()) mLocationClient.start();
//        else mLocationClient.requestLocation();
//    }
//
//    public static void stopLoacation() {
//        LocationClient mLocationClient = AppApplication.getInstance().mLocationClient;
//        if (mLocationClient.isStarted()) mLocationClient.stop();
//    }
    public static boolean isEmptyStr(String str) {
        if (null == str || str.length() <= 0)
            return true;
        else
            return false;
    }


    public static int HexStrToRGB(String str, int nDefColor) {
        try {
            if (isEmptyStr(str))
                return nDefColor;

            if (str.length() >= 2 &&
                    str.substring(0, 2).equalsIgnoreCase("0x")) {
                str = str.substring(2);
                if (isEmptyStr(str))
                    return nDefColor;
            }

            return (int) Long.parseLong(str, 16);    // Java大端字节序
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nDefColor;
    }

    public static int HexStrToRGB(String str) {
        return HexStrToRGB(str, 0);
    }

    public static String RGBToHexStr(int color) {
        return Integer.toHexString(color);
    }

    public static int HexStrToARGB(String str, int nDefColor) {
        try {
            if (isEmptyStr(str))
                return nDefColor;

            if (str.length() >= 2 &&
                    str.substring(0, 2).equalsIgnoreCase("0x")) {
                str = str.substring(2);
                if (isEmptyStr(str))
                    return nDefColor;
            }

            return (int) Long.parseLong(str, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nDefColor;
    }

    /**
     * 获取本地res资源Uri
     *
     * @param ct
     * @param resId
     * @return
     */
    public static Uri getResourceUri(Context ct, int resId) {
        Resources r = ct.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                r.getResourcePackageName(resId) + "/" + r.getResourceTypeName(resId) + "/" +
                r.getResourceEntryName(resId));
    }

    /**
     * 获取本地res资源Uri
     *
     * @param resId
     * @return
     */
    public static Uri getResourceUri(int resId) {
        return Uri.parse("res:///" + resId);
    }


    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastClickTime > 0 && timeD < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleClickThree() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastClickTime > 0 && timeD < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
