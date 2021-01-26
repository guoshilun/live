package com.jk.jkproject.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.HttpResponseCache;
import androidx.annotation.WorkerThread;
import androidx.multidex.MultiDex;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fresco.helper.config.ImageLoaderConfig;
import com.fansan.common.BaseApp;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.net.im.service.ChujianIMService;
import com.jk.jkproject.utils.CleanDataUtils;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.DynamicTimeFormat;
import com.jk.jkproject.utils.FileUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.Zego;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.net.shoot.sharetracesdk.ShareTrace;

import static android.os.Process.myPid;


public class AppApplication extends BaseApp {

    private volatile static AppApplication instance;
    private ArrayList<Activity> records;
    private WorkerThread mWorkerThread;
    public String UID;
    public String TOKEN;

    //微信
    public static final String APP_ID_WX = "wx690f26bce4caa419";
//    public static final String APP_ID_WX = "wx87729c91d67298b3";
    public static final String APP_SECRET_WX = "e294c288ceaaf29fe86fbe08800a8ead";
    public static final String API_KEY_WX = "46e9ae887632039d0b39f1b2df4592b9";

    //腾讯云直播
    public static final String licenceURL = "http://license.vod2.myqcloud.com/license/v1/c471c92bda93d06d1c481d981c7e0f53/TXLiveSDK.licence";
    public static final String licenceKey = "cb39432d6705a958c0df552beb2ebb16";

    //即构直播推拉流
    public static final long appID = 2250166144L;
    public static final String appSign = "b3f0bbccbbbb7d74ce310a1ae61078b0d9b5723be7daf2fd9dd3486653862d43";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public synchronized WorkerThread getWorkerThread() {
        return mWorkerThread;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (shouldInit()) {
            initApplication();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppApplication getInstance() {
        return instance;
    }

    private void initApplication() {
        initFresco(instance);
        initImageLoader(this);
        SPUtils.init(instance);
        MultiDex.install(this);
        MobSDK.submitPolicyGrantResult(true, null);
        MobSDK.init(this);
        LitePal.initialize(this);
        Zego.instance();
        initBugly();
        ShareTrace.init(this);
        try {
//            HttpResponseCache.install(new File(getExternalCacheDir(), "http"), 1024 * 1024 * 128);
            HttpResponseCache.install(new File(getCacheDir(), "http"), 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableAutoLoadMore(true);
            layout.setEnableOverScrollDrag(false);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableScrollContentWhenRefreshed(true);
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.tabSelectTextColor, android.R.color.black);
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.tabSelectTextColor, android.R.color.black);
            return new ClassicsFooter(context).setPrimaryColorId(R.color.tabSelectTextColor);
        });

        initUtils();
    }

    //bug日志收集初始化
    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "54e9777f6e", false, strategy);
    }

    private void initUtils() {
        Utils.init(this);
        ToastUtils.init(true);
    }

    public void initFresco(Context context) {
        Fresco.initialize(context, ImageLoaderConfig.getInstance(context).getImagePipelineConfig());
    }

    /**
     * 初始化ImageLoader
     *
     * @param context
     */
    public void initImageLoader(Context context) {
        File cacheDir = FileUtils.createDirIfNotExist(FileUtils.getFilePath(context, Constants.PICTURE_CACHE_PATH));

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.app_default_small)
                .showImageOnFail(R.mipmap.app_default_small)
                .showImageOnLoading(R.mipmap.app_default_small)
                .cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(
                        new UnlimitedDiskCache(cacheDir, null,
                                new Md5FileNameGenerator()))
                .diskCacheSize(200 * 1024 * 1024)
                .memoryCache(new LruMemoryCache(4 * 1024 * 1024))
                .memoryCacheSize(4 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // .writeDebugLogs()
                .threadPriority(Thread.NORM_PRIORITY - 1).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public void addActvity(Activity activity) {
        if (records == null) {
            records = new ArrayList<>();
        }

        records.add(activity);
    }

    public void removeActvity(Activity activity) {
        if (records != null) {
            records.remove(activity);
        }
    }

    public void exit(Context context) {
        try {
            stopService(new Intent(context, ChujianIMService.class));
            release();
            exitAll();
            System.gc();
        } catch (Exception e) {
        }

    }

    public void release() {
        IMLoginManager.instance().reset();
        IMLiveRoomManager.instance().reset();
    }

    /**
     * 删除所有的activity
     */
    public void exitAll() {
        if (records != null) {
            for (Activity activity : records) {
                activity.finish();
            }
            records.clear();
        }
    }

    /**
     * 删除pos以上的所有activity
     */
    public void exitTop(int pos) {
        List<Activity> tempList = new ArrayList<Activity>();
        if (records.size() > pos) {
            for (int i = pos; i < records.size(); i++) {
                Activity activity = records.get(i);
                tempList.add(activity);
                activity.finish();
            }
        }
        records.removeAll(tempList);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public void stopService(Context context) {
        NotificationManager notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.cancelAll();
        CleanDataUtils.clearAllCache(context);
        stopService(new Intent(context, ChujianIMService.class));
        release();
        SPUtils.clear();
    }

    //退出当前账号
    public void logOut(Context context) {
        try {
            NotificationManager notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notifyMgr.cancelAll();
            CleanDataUtils.clearAllCache(context);
            stopService(new Intent(context, ChujianIMService.class));
            release();
            SPUtils.clear();
            exitAll();
        } catch (Exception e) {
        }

    }
}
