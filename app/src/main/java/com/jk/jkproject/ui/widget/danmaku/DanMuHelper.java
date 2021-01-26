package com.jk.jkproject.ui.widget.danmaku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.fresco.helper.utils.CircleBitmapTransform;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveFireworksInfo;
import com.jk.jkproject.utils.EmojiParseUtil;
import com.jk.jkproject.utils.EmoticonUtils;
import com.jk.jkproject.utils.LiveRoleUtils;
import com.miliyo.danmaku.model.DanMuModel;
import com.miliyo.danmaku.model.utils.DimensionUtil;
import com.miliyo.danmaku.model.utils.PaintUtils;
import com.miliyo.danmaku.view.IDanMuParent;
import com.miliyo.danmaku.view.OnDanMuTouchCallBackListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public final class DanMuHelper {
    private DanmakuClickListener listener;

    private Context mContext;

    private ArrayList<WeakReference<IDanMuParent>> mDanMuViewParents;

    public DanMuHelper(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
        this.mDanMuViewParents = new ArrayList<WeakReference<IDanMuParent>>();
        PaintUtils.speed = DimensionUtil.dpToPx(this.mContext, 1.35F);
    }

    private void RichMessagePase(DanMuModel paramDanMuModel, DanmakuEntity paramDanmakuEntity, boolean paramBoolean) {
        String str;
        if (paramBoolean) {
            // 图像
            int avatarSize = DimensionUtil.dpToPx(mContext, 30);
            paramDanMuModel.avatarWidth = avatarSize;
            paramDanMuModel.avatarHeight = avatarSize;
            paramDanMuModel.avatar = CircleBitmapTransform.transform(drawable2Bitmap(ContextCompat.getDrawable(this.mContext, R.mipmap.live_fireworks)));
            int j = paramDanmakuEntity.getLevel();
            int i = getLevelResId(j);
            if (j > -1) {
                paramDanMuModel.levelBitmap = drawable2Bitmap(ContextCompat.getDrawable(this.mContext, i));
                paramDanMuModel.levelBitmapWidth = DimensionUtil.dpToPx(this.mContext, 33);
                paramDanMuModel.levelBitmapHeight = DimensionUtil.dpToPx(this.mContext, 16);
                paramDanMuModel.levelMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
            }
        }
        paramDanMuModel.textSize = DimensionUtil.spToPx(this.mContext, 14);
        paramDanMuModel.textColor = ContextCompat.getColor(this.mContext, R.color.light_green);
        paramDanMuModel.textMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
        if (paramBoolean) {
            LiveFireworksInfo liveFireworksInfo = paramDanmakuEntity.getLiveFireworksInfo();
            if (liveFireworksInfo.getRich_text() != null) {
                paramDanMuModel.text = (CharSequence) RichTextParse.parse(this.mContext, liveFireworksInfo.getRich_text(), DimensionUtil.spToPx(this.mContext, 13), false);
            } else {
                String str2 = this.mContext.getString(R.string.live_gift_fireworks);
                str = liveFireworksInfo.getFr_name();
                String str1 = liveFireworksInfo.getTo_name();
                str2 = String.format(str2, str, str1);
                SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(this.mContext, EmoticonUtils.get().getDefaultEmojiIds(), str2, 18);
                spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.white)), 0, str.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.white)), str.length() + 2, str.length() + 2 + str1.length(), 33);
                paramDanMuModel.text = (CharSequence) spannableString;
            }
        } else {
            paramDanMuModel.text = (CharSequence) RichTextParse.parse(this.mContext, paramDanmakuEntity.getRichText(), DimensionUtil.spToPx(this.mContext, 13), false);
        }
        paramDanMuModel.textBackground = ContextCompat.getDrawable(this.mContext, R.drawable.corners_danmu);
        paramDanMuModel.textBackgroundMarginLeft = DimensionUtil.dpToPx(this.mContext, 15);
        paramDanMuModel.textBackgroundPaddingTop = DimensionUtil.dpToPx(this.mContext, 3);
        paramDanMuModel.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this.mContext, 3);
        paramDanMuModel.textBackgroundPaddingRight = DimensionUtil.dpToPx(this.mContext, 15);
        paramDanMuModel.enableTouch(true);
    }

    private DanMuModel createDanMuView(final DanmakuEntity entity) {
        final DanMuModel danMuModel = new DanMuModel();
        danMuModel.headBordColor = ContextCompat.getColor(mContext, R.color.white);
        if (entity.getType() == DanmakuEntity.DANMAKU_TYPE_USERCHAT) {
            danMuModel.isChat = true;
        } else {
            danMuModel.isChat = false;
        }
        if (!entity.isChat()) {
            danMuModel.isChat = false;
        }
        danMuModel.textBackgroundMarginHeight = DimensionUtil.dpToPx(this.mContext, 20);
        danMuModel.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuModel.setPriority(DanMuModel.NORMAL);
        danMuModel.marginLeft = DimensionUtil.dpToPx(this.mContext, 30);
        danMuModel.headTopMarin = DimensionUtil.dpToPx(this.mContext, 5);
        danMuModel.topMarin = DimensionUtil.dpToPx(this.mContext, 14);
        danMuModel.paddingRight = DimensionUtil.dpToPx(this.mContext, 15);
        if (entity.getType() == DanmakuEntity.DANMAKU_TYPE_USERCHAT) {
            int avatarSize;
            if (entity.isChat()) {
                avatarSize = DimensionUtil.dpToPx(this.mContext, 36);
            } else {
                avatarSize = DimensionUtil.dpToPx(this.mContext, 25);
            }
            danMuModel.endMargin = DimensionUtil.dpToPxFloat(this.mContext, 1);
            danMuModel.headBgMarginTop = DimensionUtil.dpToPx(this.mContext, 5);
            danMuModel.avatarWidth = avatarSize;
            danMuModel.avatarHeight = avatarSize;
            if (LiveRoleUtils.isAnonymousRole(entity.getRole())) {
                danMuModel.avatar = CircleBitmapTransform.transform(drawable2Bitmap(ContextCompat.getDrawable(this.mContext, R.mipmap.profile_secret_user)));
            } else {
                String avatarImageUrl = entity.getAvatar();
                Phoenix.with(this.mContext)
                        .setUrl(avatarImageUrl)
                        .setWidth(avatarSize)
                        .setHeight(avatarSize)
                        .setResult(new IResult<Bitmap>() {
                    public void onResult(Bitmap bitmap) {
                        danMuModel.avatar = CircleBitmapTransform.transform(bitmap);
                    }
                }).load();
            }
            int level = entity.getLevel();
            if (level > -1) {
                int levelResId = getLevelResId(level);
                Drawable drawable = ContextCompat.getDrawable(mContext, levelResId);
                danMuModel.levelBitmap = drawable2Bitmap(drawable);
                danMuModel.levelBitmapWidth = DimensionUtil.dpToPx(this.mContext, 33);
                danMuModel.levelBitmapHeight = DimensionUtil.dpToPx(this.mContext, 16);
                danMuModel.levelMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
            }
            String str1 = entity.getName();
            if (LiveRoleUtils.isAnonymousRole(entity.getRole())) {
                str1 = "匿名";
            }
            String str2 = entity.getText();
            SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(this.mContext, EmoticonUtils.get().getDefaultEmojiIds(), str2, 16);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("spannableString = ");
            stringBuilder.append(spannableString);
            LogUtils.i(stringBuilder.toString());
            danMuModel.textSize = DimensionUtil.spToPx(this.mContext, 14);
            danMuModel.textColor = ContextCompat.getColor(this.mContext, R.color.light_green);
            danMuModel.textMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
            danMuModel.text = (CharSequence) spannableString;
            danMuModel.intoSpecialWidth = DimensionUtil.dpToPx(this.mContext, 60);
            danMuModel.intoSpectialHeight = DimensionUtil.dpToPx(this.mContext, 35);
            if (entity.getEffect() != null) {
                if (entity.getEffect().getImage_bubble() != null)
                    Phoenix.with(this.mContext).setUrl(entity.getEffect().getImage_bubble()).setWidth(avatarSize).setHeight(danMuModel.textBackgroundMarginHeight).setResult(new IResult<Bitmap>() {
                        public void onResult(Bitmap param1Bitmap) {
                            danMuModel.textBackground = (Drawable) new BitmapDrawable(param1Bitmap);
                        }
                    }).load();
                if (entity.getEffect().getImage_tail() != null)
                    Phoenix.with(this.mContext).setUrl(entity.getEffect().getImage_tail()).setWidth(danMuModel.intoSpecialWidth).setHeight(danMuModel.intoSpectialHeight).setResult(new IResult<Bitmap>() {
                        public void onResult(Bitmap param1Bitmap) {
                            danMuModel.danmuEndBackground = param1Bitmap;
                        }
                    }).load();
                if (entity.getEffect().getColor_border() != null) {
                    danMuModel.headBordColor = Color.parseColor(entity.getEffect().getColor_border());
                } else {
                    danMuModel.headBordColor = -1;
                }
                if (entity.getEffect().getColor_content() != null) {
                    danMuModel.textColor = Color.parseColor(entity.getEffect().getColor_content());
                } else {
                    danMuModel.textColor = -1;
                }
                if (entity.getEffect().getColor_name() != null) {
                    danMuModel.userNameColor = Color.parseColor(entity.getEffect().getColor_border());
                } else {
                    danMuModel.userNameColor = -1;
                }
            } else {
                danMuModel.userNameColor = -1;
                danMuModel.textColor = -1;
                danMuModel.headBordColor = -1;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append(":");
            danMuModel.userName = stringBuilder.toString();
            if (!entity.isChat()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str1);
                stringBuilder.append(":");
                danMuModel.userName = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str1);
                stringBuilder.append("");
                danMuModel.userName = stringBuilder.toString();
            }
//      danMuModel.nameBackground = ContextCompat.getDrawable(this.mContext, 2131230818);
            if (!entity.isChat()) {
                EmojiParseUtil emojiParseUtil = EmojiParseUtil.getInstace();
                Context context = this.mContext;
                HashMap hashMap = EmoticonUtils.get().getDefaultEmojiIds();
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(danMuModel.userName);
                stringBuilder1.append(str2);
                SpannableString spannableString1 = emojiParseUtil.getExpressionString(context, hashMap, stringBuilder1.toString(), 18);
                spannableString1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.white)), 0, danMuModel.userName.length(), 33);
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("spannableString = ");
                stringBuilder1.append(spannableString);
                LogUtils.i(stringBuilder1.toString());
                danMuModel.textSize = DimensionUtil.spToPx(this.mContext, 14);
                danMuModel.textColor = ContextCompat.getColor(this.mContext, R.color.light_green);
                danMuModel.textMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
                danMuModel.text = (CharSequence) spannableString1;
            }
            danMuModel.textBackground = ContextCompat.getDrawable(this.mContext, R.drawable.bg_danmu_des_btn);
            danMuModel.textBackgroundMarginLeft = DimensionUtil.dpToPx(this.mContext, 15);
            danMuModel.textBackgroundPaddingTop = DimensionUtil.dpToPx(this.mContext, 3);
            danMuModel.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this.mContext, 3);
            danMuModel.textBackgroundPaddingRight = DimensionUtil.dpToPx(this.mContext, 15);
            danMuModel.enableTouch(true);
            danMuModel.setOnTouchCallBackListener(param1DanMuModel -> {
                try {
                    String str11 = entity.getUserId();
                    String str21 = entity.getAvatar();
                    int i = entity.getRole();
                    if (!TextUtils.isEmpty(str11) && !TextUtils.isEmpty(str21) && !str11.equals("1000") && DanMuHelper.this.listener != null)
                        DanMuHelper.this.listener.onDanmuIconClick(str11, str21, i);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } else if (entity.getType() == 2) {
            RichMessagePase(danMuModel, entity, true);
            danMuModel.setOnTouchCallBackListener(new OnDanMuTouchCallBackListener() {
                public void callBack(DanMuModel param1DanMuModel) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(DanMuHelper.this.listener);
                        LogUtils.d(stringBuilder.toString());
                        if (DanMuHelper.this.listener != null) {
                            DanMuHelper.this.listener.onDanmakuClick(DanMuHelper.this.mContext, entity);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } else if (entity.getType() == 3) {
            RichMessagePase(danMuModel, entity, false);
            danMuModel.setOnTouchCallBackListener(new OnDanMuTouchCallBackListener() {
                public void callBack(DanMuModel param1DanMuModel) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(DanMuHelper.this.listener);
                        LogUtils.d(stringBuilder.toString());
                        if (DanMuHelper.this.listener != null) {
                            DanMuHelper.this.listener.onDanmakuClick(DanMuHelper.this.mContext, entity);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } else if (entity.getType() == 4) {
            RichMessagePase(danMuModel, entity, false);
            int k = DimensionUtil.dpToPx(this.mContext, 30);
            danMuModel.avatarWidth = k;
            danMuModel.avatarHeight = k;
            String str = entity.getAvatar();
            Phoenix.with(this.mContext).setUrl(str).setWidth(k).setHeight(k).setResult(new IResult<Bitmap>() {
                public void onResult(Bitmap param1Bitmap) {
                    danMuModel.avatar = CircleBitmapTransform.transform(param1Bitmap);
                }
            }).load();
            RichMessagePase(danMuModel, entity, false);
            int j = entity.getLevel();
            int i = getLevelResId(j);
            if (entity.getBg_head() != null) {
                Phoenix.with(this.mContext).setUrl(entity.getBg_head()).setWidth(45).setHeight(45).setResult(new IResult<Bitmap>() {
                    public void onResult(Bitmap param1Bitmap) {
                        danMuModel.avatarCircle = param1Bitmap;
                    }
                }).load();
                danMuModel.headBgMarginBottom = DimensionUtil.dpToPx(this.mContext, 8);
                danMuModel.headBgMarginTop = DimensionUtil.dpToPx(this.mContext, 5);
                danMuModel.headBgMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
                danMuModel.headBgMarginLeftOther = DimensionUtil.dpToPx(this.mContext, 3);
                danMuModel.headBgMargin = DimensionUtil.dpToPx(this.mContext, 10);
                danMuModel.avatarCircleHight = DimensionUtil.dpToPx(this.mContext, 45);
                danMuModel.avatarCircleWidth = DimensionUtil.dpToPx(this.mContext, 45);
            }
            if (j > -1) {
                danMuModel.levelBitmap = drawable2Bitmap(ContextCompat.getDrawable(this.mContext, i));
                danMuModel.levelBitmapWidth = DimensionUtil.dpToPx(this.mContext, 33);
                danMuModel.levelBitmapHeight = DimensionUtil.dpToPx(this.mContext, 16);
                danMuModel.levelMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
                danMuModel.levelMarginTop = DimensionUtil.dpToPx(this.mContext, 3);
            }
            if (entity.getBg_barrage() != null) {
                Phoenix.with(this.mContext).setUrl(entity.getBg_barrage()).setWidth(k).setHeight(k).setResult(new IResult<Bitmap>() {
                    public void onResult(Bitmap param1Bitmap) {
                        danMuModel.textBackground = (Drawable) new BitmapDrawable(param1Bitmap);
                    }
                }).load();
                danMuModel.textBackgroundMarginLeft = DimensionUtil.dpToPx(this.mContext, 15);
                danMuModel.textBackgroundPaddingTop = DimensionUtil.dpToPx(this.mContext, 3);
                danMuModel.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this.mContext, 3);
                danMuModel.textBackgroundPaddingRight = DimensionUtil.dpToPx(this.mContext, 15);
                danMuModel.headRight = drawable2Bitmap(ContextCompat.getDrawable(this.mContext, getIcon(entity.getLevel())));
                danMuModel.headRightWidth = DimensionUtil.dpToPx(this.mContext, 15);
                danMuModel.headRightHeight = DimensionUtil.dpToPx(this.mContext, 15);
            }
            danMuModel.headTopHeight = DimensionUtil.dpToPx(this.mContext, 36);
            danMuModel.headTopWidth = DimensionUtil.dpToPx(this.mContext, 35);
            if (entity.getAchieve_icon() != null)
                Phoenix.with(this.mContext).setUrl(entity.getAchieve_icon()).setWidth(danMuModel.headTopWidth).setHeight(danMuModel.headTopHeight).setResult(new IResult<Bitmap>() {
                    public void onResult(Bitmap param1Bitmap) {
                        danMuModel.headTop = param1Bitmap;
                    }
                }).load();
        } else {
            danMuModel.textSize = DimensionUtil.spToPx(this.mContext, 14);
            danMuModel.textColor = ContextCompat.getColor(this.mContext, R.color.live_yellow);
            danMuModel.textMarginLeft = DimensionUtil.dpToPx(this.mContext, 5);
            if (entity.getRichText() != null) {
                danMuModel.text = (CharSequence) RichTextParse.parse(this.mContext, entity.getRichText(), DimensionUtil.spToPx(this.mContext, 18), false);
            } else {
                danMuModel.text = (CharSequence) EmojiParseUtil.getInstace().getExpressionString(this.mContext, EmoticonUtils.get().getDefaultEmojiIds(), entity.getText(), 18);
            }
            danMuModel.textBackground = ContextCompat.getDrawable(this.mContext, R.drawable.corners_danmu);
            danMuModel.textBackgroundMarginLeft = DimensionUtil.dpToPx(this.mContext, 15);
            danMuModel.textBackgroundPaddingTop = DimensionUtil.dpToPx(this.mContext, 3);
            danMuModel.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this.mContext, 3);
            danMuModel.textBackgroundPaddingRight = DimensionUtil.dpToPx(this.mContext, 15);
            danMuModel.enableTouch(false);
        }
        return danMuModel;
    }

    public void add(IDanMuParent paramIDanMuParent) {
        if (paramIDanMuParent != null)
            paramIDanMuParent.clear();
        ArrayList<WeakReference<IDanMuParent>> arrayList = this.mDanMuViewParents;
        if (arrayList != null)
            arrayList.add(new WeakReference<IDanMuParent>(paramIDanMuParent));
    }

    public void addDanMu(DanmakuEntity paramDanmakuEntity, boolean paramBoolean) {
        ArrayList<WeakReference<IDanMuParent>> arrayList = this.mDanMuViewParents;
        if (arrayList != null) {
            WeakReference<IDanMuParent> weakReference = arrayList.get(0);
            if (!paramBoolean)
                weakReference = this.mDanMuViewParents.get(0);
            DanMuModel danMuModel = createDanMuView(paramDanmakuEntity);
            if (weakReference != null && danMuModel != null && weakReference.get() != null)
                ((IDanMuParent) weakReference.get()).add(danMuModel);
        }
    }

    public Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            // 转换成Bitmap
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            // .9图片转换成Bitmap
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ?
                            Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    public int getIcon(int gloryGrade) {
        switch (gloryGrade) {
            case 0:
//                return R.mipmap.icon_live_badge_new_comer;

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                //1级
//                return R.mipmap.icon_live_badge_bronze;

            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                //2级
//                return R.mipmap.icon_live_badge_silver;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                //3级
//                return R.mipmap.icon_live_badge_gold;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                //4级
//                return R.mipmap.icon_live_badge_platinum;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                //5级
//                return R.mipmap.icon_live_badge_pearl;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                //6级
//                return R.mipmap.icon_live_badge_royal;

            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:


                //7级
//                return R.mipmap.icon_live_badge_deeproyal;


            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                //8级

//                return R.mipmap.icon_live_badge_deepgold;

            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
                //9级
//                return R.mipmap.icon_live_badge_deepred;

            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
                //10级
                return R.mipmap.icon_level_stage_zero;

            default:
                //11级
//                return R.mipmap.icon_live_badge_deepgreen;
        }
        return 0;
    }

    public int getLevelResId(int paramInt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lv_");
        stringBuilder.append(paramInt);
        String str = stringBuilder.toString();
        if (paramInt == 100) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("lv_");
            stringBuilder1.append(paramInt * 10);
            str = stringBuilder1.toString();
            paramInt = this.mContext.getResources().getIdentifier(str, "mipmap", this.mContext.getPackageName());
        } else if (paramInt == 0) {
            paramInt = R.mipmap.icon_level_stage_zero;
        } else {
            paramInt = this.mContext.getResources().getIdentifier(str, "mipmap", this.mContext.getPackageName());
        }
        return paramInt;
    }

    public void release() {
        ArrayList<WeakReference<IDanMuParent>> arrayList = this.mDanMuViewParents;
        if (arrayList != null) {
            for (WeakReference<IDanMuParent> weakReference : arrayList) {
                if (weakReference != null) {
                    IDanMuParent iDanMuParent = weakReference.get();
                    if (iDanMuParent != null)
                        iDanMuParent.release();
                }
            }
            this.mDanMuViewParents.clear();
            this.mDanMuViewParents = null;
        }
        this.listener = null;
        this.mContext = null;
    }

    public void setDanmakuClickListener(DanmakuClickListener paramDanmakuClickListener) {
        this.listener = paramDanmakuClickListener;
    }
}
