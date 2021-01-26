package com.jk.jkproject.utils;

import android.content.Context;
import android.text.TextUtils;

import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.db.EmoticonDao;
import com.jk.jkproject.ui.model.EmotionModle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @filename : EmoticonUtils.java
 * @author : zhaotun
 * @description : 表情工具类
 * @version : 1.0
 */
public class EmoticonUtils {
	private static String EMOTICON_BASE_PATH = null;
	
	public static final String EMOTICON_EMOJI = "emoticon_emoji";
	public static final String EMOTICON_HISTORY = "emoticon_history";
	public static final String EMOTICON_SETTING = "emoticon_setting";
	
	public static final String EMOTICON_BAYMAX = "baymax";
	public static final String EMOTICON_BAYMAX_ID = "1003";
	public static final String EMOTICON_BAYMAX_DOWN_URL = Urls.EMOTICON_GIF_BASE_URL + "baymax/baymax.zip";
	public static final String EMOTICON_BAYMAX_DOWNLOAD_ICON_URL = Urls.EMOTICON_GIF_BASE_URL + "baymax/baymax_download.png";
	public static final String EMOTICON_BAYMAX_TITLE = "大白";

	private static ArrayList<EmotionModle> mDefaultEmojis;
	private static HashMap<String, Integer> mDefaultEmojiIds = new HashMap<String, Integer>();

	private static HashMap<String, String> mExistEmoticonIds = new HashMap<String, String>();
	private static LinkedHashMap<String, ArrayList<EmotionModle>> mExistEmoticons = new LinkedHashMap<String, ArrayList<EmotionModle>>();
	
	private static EmoticonUtils instance;
	
	private EmoticonUtils(Context context) {
		initEmoticons(context);
	}

	public static EmoticonUtils get() {
		if (instance == null) {
			init(AppApplication.getInstance());
		}

		return instance;
	}

	private static void init(Context context) {
		if (instance == null) {
			synchronized (EmoticonUtils.class) {
				if (instance == null) {
					instance = new EmoticonUtils(context);
				}
			}
		}
	}
	
	private void initEmoticons(Context context) {
		EmojiParseUtil parse = EmojiParseUtil.getInstace();
		initDefaultEmojis(context, parse);
		initExistEmoticon(context, parse);
	}

	private void initDefaultEmojis(Context context, EmojiParseUtil parse) {
		if (mDefaultEmojis != null) {
			mDefaultEmojis.clear();
		}
		
		mDefaultEmojis = parse.parseEmoji(context, "emoji.xml");
		
		if (mDefaultEmojis != null && mDefaultEmojis.size() > 0) {
			mDefaultEmojiIds.clear();
			for (int i = 0, size = mDefaultEmojis.size(); i < size; i++) {
				EmotionModle em = mDefaultEmojis.get(i);
				mDefaultEmojiIds.put(em.getEmoticonName(), em.getId());
			}
		}
	}
	
	private void initExistEmoticon(Context context, EmojiParseUtil parse) {
		if (TextUtils.isEmpty(getEmoticonBasePath())) {
			return;
		}
		
		File dir = new File(EMOTICON_BASE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
			return;
		}
		
		String uid = SPUtils.Impl.getUid();
		EmoticonDao dao = new EmoticonDao(context);
		List<String> names = dao.queryAllDownloadedEpkgNames(uid);
		
		if (mExistEmoticons != null) {
			mExistEmoticons.clear();
		}
		
		if (mExistEmoticonIds != null) {
			mExistEmoticonIds.clear();
		}
		
		if (names != null && names.size() > 0) {
			for (int i = 0, size = names.size(); i < size; i++) {
				String name = names.get(i);
				String path = EMOTICON_BASE_PATH + File.separator + name + File.separator + name + ".xml";
				File file = new File(path);
				if (!file.exists()) {
					continue;
				}
				
				ArrayList<EmotionModle> list = parse.parseDownEmoticon(context, file);
				if (list != null && list.size() > 0) {
					for (int j = 0, len = list.size(); j < len; j++) {
						EmotionModle e = list.get(j);
						mExistEmoticonIds.put(e.getEmoticonFile(), name);
					}
					
					mExistEmoticons.put(name, list);
				}
			}
			
			names.clear();
		}
		
		if (!mExistEmoticons.containsKey(EMOTICON_BAYMAX)) {
			mExistEmoticons.put(EMOTICON_BAYMAX, new ArrayList<EmotionModle>());
		}
	}
	
	public List<EmotionModle> getDefaultEmojis() {
		return mDefaultEmojis;
	}
	
	public HashMap<String, Integer> getDefaultEmojiIds() {
		return mDefaultEmojiIds;
	}
	
	public HashMap<String, String> getExistEmoticonIds() {
		return mExistEmoticonIds;
	}
	
	public LinkedHashMap<String, ArrayList<EmotionModle>> getExistEmoticons() {
		return mExistEmoticons;
	}
	
	public String getEmoticonBasePath() {
		if (TextUtils.isEmpty(EMOTICON_BASE_PATH)) {
			if (FileUtils.hasSDCard()) {
				// sd卡存在
				String uid = SPUtils.Impl.getUid();
				if (!TextUtils.isEmpty(uid)) {
					//EMOTICON_BASE_PATH = MemoryUtils.getSDRoot() + File.separator + Constants.ChuJian + uid;
					EMOTICON_BASE_PATH = AppApplication.getInstance().getExternalFilesDir("emoticon")+ File.separator  + "." + uid;
				}
			}
		}
		return EMOTICON_BASE_PATH;
	}
	
	public void reset() {
		initEmoticons(AppApplication.getInstance());
	}
	
	public void release() {
		if (mDefaultEmojis != null) {
			mDefaultEmojis.clear();
		}
		
		if (mDefaultEmojiIds != null) {
			mDefaultEmojiIds.clear();
		}
		
		if (mExistEmoticonIds != null) {
			mExistEmoticonIds.clear();
		}
		
		if (mExistEmoticons != null) {
			Collection<ArrayList<EmotionModle>> values = mExistEmoticons.values();
			for (int i = 0, size = values.size(); i < size; i++) {
				values.clear();
			}
			
			mExistEmoticons.clear();
		}
	}
	
}
