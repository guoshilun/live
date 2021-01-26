# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhaotun/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.google.protobuf.**{*;}
-keep public class* extends com.google.protobuf.** { *; }
-keep class com.google.gson.** { *; } # gson
-keep enum com.facebook.**
-keep public interface com.facebook.**
-keep class com.facebook.**
-dontwarn com.nostra13.universalimageloader.**   # ImageLoader
-keep class com.nostra13.universalimageloader.** { *; }
-keep class okhttp3.**{*;}   # okhttp3
-keep class okio.**{*;}



#指定class模糊字典
#-classobfuscationdictionary filename
#指定package模糊字典
#-packageobfuscationdictionary filename


-optimizationpasses 5          # 指定代码的压缩级别
#-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
-dontoptimize  #优化  不优化输入的类文件
-dontshrink
-dontwarn
-dontwarn android.webkit.WebView
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.jk.jkproject.ui.entity.**{ * ;}
-keep public class com.jk.jkproject.net.**{ * ;}

-keepattributes Exceptions,InnerClasses,Signature,EnclosingMethod
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public class javax.**
-keep public class android.webkit.**

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keep class com.jk.jkproject.ui.chat.**{*;}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
    public static final ** CREATOR;
}
#七牛
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}

#bugly bug日志收集
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

-ignorewarnings

#即构
-keep class **.zego.**{*;}

#ShareSDK
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class com.mob.**{*;}
-keep class com.bytedance.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**

#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

-keep public class **.R$*{
     * ;
}
-keep class org.litepal.** {
    *;
}

-keep class * extends org.litepal.crud.DataSupport {
    *;
}
-keep class * extends org.litepal.crud.LitePalSupport {
    *;
}
-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {
    public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory, com.facebook.imagepipeline.core.ExecutorSupplier);
}
-keep class com.facebook.animated.gif.** {*;}
-keepnames class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {
    *;
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers class * extends android.app.Activity {   # 保护xml写的onclick有效
    public void *(android.view.View);
}

-keep class com.tencent.mm.opensdk.** {
    *;
}

-keep class com.tencent.wxop.** {
    *;
}

-keep class com.tencent.mm.sdk.** {
    *;
}

-keep class * implements android.os.Parcelable { *; }

