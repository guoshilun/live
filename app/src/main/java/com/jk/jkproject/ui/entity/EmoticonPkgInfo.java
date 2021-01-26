package com.jk.jkproject.ui.entity;

import android.app.Activity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.jk.jkproject.R;
import com.jk.jkproject.net.im.db.DBConstants;
import com.jk.jkproject.ui.widget.IProgressCallback;

import java.lang.ref.WeakReference;
import java.util.List;

public class EmoticonPkgInfo extends BaseInfo{

  private String id;			// 表情包id

  private String title;		// 表情包名称

  private String package_name;//表情包标识

  private String sex;			// 性别

  private String icon;		// 表情包logo的url

  private String download_icon;// 表情包列表图标url

  private String banner;	// 表情包详情中顶部banner图url

  private String space_usage;	// 表情包大小 M

  private String description;	// 表情包描述

  private String price;		// 表情包价格

  private String url;			// 表情包下载地址

  private String model;		// 表情包类别

  private String active_time;	// 表情包有效时间  单位为天

  /** 下载状态 */
  private int mStatus = DBConstants.Impl.EPKG_DOWN_STATUS_NOT;

  /** 下载进度(数字) */
  private int mProgress;

  /** 总大小 */
  private long mTotalSize;

  /** 已下载大小 */
  private long mCurrentSize;

  /** 下载进度（动画） */
  private int mProgressLevel;

  /** 下载完成之后的文件存储路径 */
  private String mFilePath;

  private List<EmoticonInfo> images;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPackage_name() {
    return package_name;
  }

  public void setPackage_name(String package_name) {
    this.package_name = package_name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getDownload_icon() {
    return download_icon;
  }

  public void setDownload_icon(String download_icon) {
    this.download_icon = download_icon;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public String getSpace_usage() {
    return space_usage;
  }

  public void setSpace_usage(String space_usage) {
    this.space_usage = space_usage;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getActive_time() {
    return active_time;
  }

  public void setActive_time(String active_time) {
    this.active_time = active_time;
  }

  public List<EmoticonInfo> getImages() {
    return images;
  }

  public void setImages(List<EmoticonInfo> images) {
    this.images = images;
  }



  public int getmStatus() {
    return mStatus;
  }

  public void setmStatus(int mStatus) {
    this.mStatus = mStatus;
  }

  public int getmProgress() {
    return mProgress;
  }

  public void setmProgress(int mProgress) {
    this.mProgress = mProgress;
  }

  public long getmTotalSize() {
    return mTotalSize;
  }

  public void setmTotalSize(long mTotalSize) {
    this.mTotalSize = mTotalSize;
  }

  public long getmCurrentSize() {
    return mCurrentSize;
  }

  public void setmCurrentSize(long mCurrentSize) {
    this.mCurrentSize = mCurrentSize;
  }

  public int getmProgressLevel() {
    return mProgressLevel;
  }

  public void setmProgressLevel(int mProgressLevel) {
    this.mProgressLevel = mProgressLevel;
  }

  public String getmFilePath() {
    return mFilePath;
  }

  public void setmFilePath(String mFilePath) {
    this.mFilePath = mFilePath;
  }



  private SparseArray<WeakReference<IProgressCallback>> callbacks = new SparseArray<WeakReference<IProgressCallback>>();

  public synchronized void addProgressViews(IProgressCallback... progressViews) {
    for (IProgressCallback callback : progressViews) {
      if (callback instanceof View) {
        View view = (View) callback;
        Object obj = view.getTag(R.id.download_url);
        if (obj != null && !TextUtils.isEmpty((CharSequence) obj)) {
          callbacks.put(callback.hashCode(), new WeakReference<IProgressCallback>(callback));
        } else {
          throw new RuntimeException("the callback must setTag and use R.id.download_url as the key");
        }
      } else {
        throw new RuntimeException("the callback isn't the instance of View!");
      }
    }
  }

  public synchronized void notifyProgress() {
    notifyProgress(null);
  }

  public synchronized void notifyProgress(Activity activity) {
    int size;
    if (callbacks != null && (size = callbacks.size()) > 0) {
      for (int i = 0; i < size; i++) {
        final IProgressCallback callback = callbacks.valueAt(i).get();
        if (callback == null) {
          callbacks.removeAt(i);
          i--;
          size--;
        } else {
          if (callback instanceof View) {
            final View v = (View) callback;
            final Object u = v.getTag(R.id.download_url);
            if (u != null) {
              if (u.equals(url)) {
                Runnable run = new Runnable() {

                  @Override
                  public void run() {
                    if (u.equals(v.getTag(R.id.download_url))) {
                      callback.showStatus(EmoticonPkgInfo.this);
                      callback.showProgress(mProgress);
                    }
                  }
                };

                if (activity == null) {
                  v.post(run);
                } else {
                  activity.runOnUiThread(run);
                }
              } else {
                callbacks.removeAt(i);
                i--;
                size--;
              }
            } else {
              callbacks.removeAt(i);
              i--;
              size--;
            }
          } else {
            callbacks.removeAt(i);
            i--;
            size--;
          }
        }
      }
    }
  }

}
