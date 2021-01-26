package com.jk.jkproject.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Xml;

import com.jk.jkproject.ui.model.EmotionModle;
import com.jk.jkproject.utils.emoji.AlineCenterImageSpan;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiParseUtil {

    private static EmojiParseUtil instance;

    public static EmojiParseUtil getInstace() {
        if (instance == null) {
            synchronized (EmojiParseUtil.class) {
                if (instance == null) {
                    instance = new EmojiParseUtil();
                }
            }
        }

        return instance;
    }

    /**
     * 解析emoji
     *
     * @param ctx
     * @return
     */
    public ArrayList<EmotionModle> parseEmoji(Context ctx, String name) {
        ArrayList<EmotionModle> list = new ArrayList<EmotionModle>();
        InputStream is = null;
        try {
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            XmlPullParser xrp = xppf.newPullParser();
            is = ctx.getAssets().open(name);
            xrp.setInput(is, "UTF-8");
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("emoticon")) {
                        String id = xrp.getAttributeValue(0);// 通过属性索引来获取属性值
                        String text = xrp.getAttributeValue(1);// 通过属性索引来获取属性值
                        String file = xrp.getAttributeValue(2);// 通过属性索引来获取属性值
                        String tmp[] = file.split("\\.");
                        String picName = "0";
                        if (tmp != null && tmp.length > 0) {
                            picName = tmp[0];
                        }
                        int resourceId = ctx.getResources().getIdentifier(
                                picName, "drawable", ctx.getPackageName());
                        EmotionModle emo = new EmotionModle();
                        emo.setEmoticonName(text);
                        emo.setEmoticonFile(file);
                        emo.setId(resourceId);
                        list.add(emo);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (Throwable e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable e) {
                } finally {
                    is = null;
                }
            }
        }

        return list;
    }

    public ArrayList<EmotionModle> parseDownEmoticon(Context ctx, File file) {
        ArrayList<EmotionModle> list = new ArrayList<EmotionModle>();

        InputStream is = null;
        try {
            XmlPullParser parser = Xml.newPullParser();
            is = new FileInputStream(file);
            parser.setInput(is, "UTF-8");

//			String pkg = ctx.getPackageName();
            int type = parser.getEventType();
            // 直到文档的结尾处
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:    // 开始标签
                        // 获取标签的名字
                        if ("emoticon".equals(parser.getName())) {
                            String emotid = parser.getAttributeValue(0);// 通过属性索引来获取属性值
                            String name = parser.getAttributeValue(1);// 通过属性索引来获取属性值
                            String f = parser.getAttributeValue(2);// 通过属性索引来获取属性值
                            String url = "";//

                            try {
                                url = parser.getAttributeValue(3);// 通过属性索引来获取属性值
                            } catch (Throwable e) {
                                url = "";
                            }

                            EmotionModle emo = new EmotionModle();
                            emo.setEmoticonName(name);
                            emo.setEmoticonFile(f);
                            emo.setEmoticonId(emotid);
                            emo.setEmoticonUrl(url);

                            list.add(emo);
                        }
                        break;
                    default:
                        break;
                }
                type = parser.next();// 获取解析下一个事件
            }
        } catch (Throwable e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable e) {
                } finally {
                    is = null;
                }
            }
        }

        return list;
    }

    /**
     * 解析emot
     *
     * @param ctx
     * @return
     */
    public ArrayList<EmotionModle> parseEmotGif(Context ctx, String name) {

        ArrayList<EmotionModle> list = new ArrayList<EmotionModle>();
        try {
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            XmlPullParser xrp = xppf.newPullParser();
            InputStream stream = ctx.getAssets().open(name);
            xrp.setInput(stream, "UTF-8");
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("emoticon")) {
                        String emotid = xrp.getAttributeValue(0);// 通过属性索引来获取属性值
                        String text = xrp.getAttributeValue(1);// 通过属性索引来获取属性值
                        String file = xrp.getAttributeValue(2);// 通过属性索引来获取属性值

                        String tmp[] = file.split("\\.");
                        String picName = "0";
                        if (tmp != null && tmp.length > 0) {
                            picName = tmp[0];
                        }
                        int resourceId = ctx.getResources().getIdentifier(
                                picName, "drawable", ctx.getPackageName());

                        EmotionModle emo = new EmotionModle();
                        emo.setId(resourceId);
                        emo.setEmoticonName(text);
                        emo.setEmoticonFile(file);
                        emo.setEmoticonId(emotid);
                        list.add(emo);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 添加表情
     *
     * @param context
     * @param imgId
     * @param spannableString
     * @return
     */
    public static SpannableString addFace(Context context, int imgId,
                                          String spannableString) {
        if (TextUtils.isEmpty(spannableString)) {
            return null;
        }
//		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
//				imgId);
//		bitmap = Bitmap.createScaledBitmap(bitmap,
//				DensityUtils.dip2px(context, 28), DensityUtils.dip2px(context, 28),
//				true);
//		ImageSpan imageSpan = new ImageSpan(context, bitmap);

        AlineCenterImageSpan imageSpan1 = new AlineCenterImageSpan(context.getApplicationContext(), imgId);

        SpannableString spannable = new SpannableString(spannableString);
        spannable.setSpan(imageSpan1, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }


    /**
     * 添加表情
     *
     * @param context
     * @param imgId
     * @param spannableString
     * @return
     */
    public static SpannableString addFace(Context context, int imgId,
                                          String spannableString, int emojiSize) {
        if (TextUtils.isEmpty(spannableString)) {
            return null;
        }
//		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
//				imgId);
//		bitmap = Bitmap.createScaledBitmap(bitmap,
//				DensityUtils.dip2px(context, 28), DensityUtils.dip2px(context, 28),
//				true);
//		ImageSpan imageSpan = new ImageSpan(context, bitmap);

        AlineCenterImageSpan imageSpan1 = new AlineCenterImageSpan(context.getApplicationContext(), imgId, emojiSize);

        SpannableString spannable = new SpannableString(spannableString);
        spannable.setSpan(imageSpan1, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }


    /**
     * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
     *
     * @param context
     * @param str
     * @return
     */
    public SpannableString getExpressionString(Context context,
                                               HashMap<String, Integer> emojiMap, String str, int emojiSize) {
        SpannableString spannableString = new SpannableString(str);
        // 正则表达式比配字符串里是否含有表情，如： 我好[开心]啊
        String pattern = "\\[[^\\]]+\\]";
        // 通过传入的正则表达式来生成一个pattern
        Pattern sinaPatten = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        try {
            dealExpression(context, emojiMap, spannableString, sinaPatten, 0, emojiSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannableString;
    }

    /**
     * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
     *
     * @param context
     * @param spannableString
     * @param patten
     * @param start
     * @throws Exception
     */
    private void dealExpression(Context context,
                                HashMap<String, Integer> emojiMap, SpannableString spannableString,
                                Pattern patten, int start, int emojiSize) throws Exception {
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            // 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
            if (matcher.start() < start) {
                continue;
            }
            int resId = emojiMap.get(key);
            if (resId == 0) {
                continue;
            }
            // 通过上面匹配得到的字符串来生成图片资源id
            // Field field=R.drawable.class.getDeclaredField(value);
            // int resId=Integer.parseInt(field.get(null).toString());
            if (resId != 0) {
//				Bitmap bitmap = BitmapFactory.decodeResource(
//						context.getResources(), resId);
//				bitmap = Bitmap.createScaledBitmap(bitmap,
//						DensityUtils.dip2px(context, 28),
//						DensityUtils.dip2px(context, 28), true);
//				// 通过图片资源id来得到bitmap，用一个ImageSpan来包装
//				ImageSpan imageSpan = new ImageSpan(context, bitmap);

                AlineCenterImageSpan imageSpan1 = new AlineCenterImageSpan(context.getApplicationContext(), resId, ScreenUtils.dp2px(context, emojiSize));

                // 计算该图片名字的长度，也就是要替换的字符串的长度
                int end = matcher.start() + key.length();
                // 将该图片替换字符串中规定的位置中
                spannableString.setSpan(imageSpan1, matcher.start(), end,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                if (end < spannableString.length()) {
                    // 如果整个字符串还未验证完，则继续。。
                    dealExpression(context, emojiMap, spannableString, patten,
                            end, emojiSize);
                }
                break;
            }
        }
    }

}
