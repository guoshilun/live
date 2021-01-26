package com.jk.jkproject.ui.widget.room;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PkStartBean;


public class PkStartAnimation extends RelativeLayout {
    private RelativeLayout relateLeft, relateRight;
    private ImageView imCenterShards, imCenterShardsRight;
    private ImageView imLeftShards, imRightShards;
    private ImageView imPKCenter, imPkcenterShards;
    private ObjectAnimator objectAnimatorRight, objectAnimatorLeft;
    private ScaleAnimation scaleAnimation;
    private ScaleAnimation scaleAnimationPk;
    private ScaleAnimation alphaAnimationRightLight;
    private ScaleAnimation alphaAnimationLeftLight;
    private ScaleAnimation scaleAnimationLeftShards;
    private ScaleAnimation scaleAnimationRightShards;
    private SimpleDraweeView imOwnhead, imOtherhead;
    private TextView tvOwnName, tvOtherName, tvOwnId, tvOtherId;
    private int inX;

    public PkStartAnimation(Context context) {
        super(context);
        init(context);
    }

    public PkStartAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PkStartAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_pk_animation, this, true);
        relateLeft = findViewById(R.id.relate_left_bg);
        relateRight = findViewById(R.id.relate_right_bg);
        imCenterShards = findViewById(R.id.im_left_center_shards);
        imLeftShards = findViewById(R.id.im_left_shards);
        imPKCenter = findViewById(R.id.im_pk_center);
        imPkcenterShards = findViewById(R.id.im_pk_center_shards);
        imCenterShardsRight = findViewById(R.id.im_right_center_shards);
        imRightShards = findViewById(R.id.im_right_shards);

        imOtherhead = findViewById(R.id.im_other_head);
        imOwnhead = findViewById(R.id.im_own_head);

        tvOwnName = findViewById(R.id.tv_own_name);
        tvOtherName = findViewById(R.id.tv_other_name);

        tvOtherId = findViewById(R.id.tv_other_id);
        tvOwnId = findViewById(R.id.tv_own_id);
        inX = getResources().getDimensionPixelSize(R.dimen.dp_160);
    }


    public void setDataOrStartAnima(PkStartBean bean) {

        tvOtherId.setText("ID:" + bean.getOther().getData().getIp());
        tvOtherName.setText(bean.getOther().getData().getNickName());
        imOtherhead.setImageURI(bean.getOther().getData().getPicture());

        tvOwnName.setText(bean.getOwnNickName());
        tvOwnId.setText("ID:" + bean.getOwnUid());
        imOwnhead.setImageURI(bean.getOwnLiveCover());


        setRelateLeftAnimat();
        setRelateRightAnimat();
    }

    /**
     * 左边大背景动画
     */
    private void setRelateLeftAnimat() {
        relateLeft.setVisibility(View.VISIBLE);
        objectAnimatorLeft = ObjectAnimator.ofFloat(relateLeft, "translationX", -inX, 0);
        objectAnimatorLeft.setDuration(300);
        objectAnimatorLeft.start();
        objectAnimatorLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imCenterShards.setVisibility(View.INVISIBLE);
                imLeftShards.setVisibility(View.INVISIBLE);
                imCenterShardsRight.setVisibility(View.INVISIBLE);
                imRightShards.setVisibility(View.INVISIBLE);
                imPKCenter.setVisibility(View.INVISIBLE);
                imPkcenterShards.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setPKCenterVSShards();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimatorLeft.start();
    }

    /**
     * 左边发光
     */
    private void setLeftLightAnimat() {
        if (imCenterShards != null) {
            imCenterShards.setVisibility(View.VISIBLE);
            alphaAnimationLeftLight = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            alphaAnimationLeftLight.setDuration(250);
            alphaAnimationLeftLight.setRepeatMode(Animation.REVERSE);
            imCenterShards.setAnimation(alphaAnimationLeftLight);
            alphaAnimationLeftLight.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    setLeftShardsAnimat();
                    setRightShardsAnimat();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            alphaAnimationLeftLight.start();
        }

    }

    /**
     * 左边碎片
     */
    private void setLeftShardsAnimat() {
        if (imLeftShards != null) {
            imLeftShards.setVisibility(View.VISIBLE);
            scaleAnimationLeftShards = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimationLeftShards.setDuration(250);
            scaleAnimationLeftShards.setRepeatMode(Animation.REVERSE);
            imLeftShards.setAnimation(scaleAnimationLeftShards);
            scaleAnimationLeftShards.start();
        }
    }

    /**
     * 中间Vs动画
     */
    private void setPKCenterVSShards() {
        if (imPKCenter != null) {
            imPKCenter.setVisibility(View.VISIBLE);
            imPKCenter.clearAnimation();
            scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setRepeatMode(Animation.REVERSE);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    setCententShards();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            imPKCenter.startAnimation(scaleAnimation);
        }
    }

    /**
     * 中间黄色碎片
     */
    private void setCententShards() {
        if (imPkcenterShards != null) {
            scaleAnimationPk = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimationPk.setDuration(250);
            scaleAnimationPk.setRepeatMode(Animation.REVERSE);
            imPkcenterShards.setVisibility(View.VISIBLE);
            imPkcenterShards.setAnimation(scaleAnimationPk);
            scaleAnimationPk.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    setRightLightAnimat();
                    setLeftLightAnimat();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            scaleAnimationPk.start();
        }
    }

    /**
     * 右边大背景动画
     */
    private void setRelateRightAnimat() {
        if (relateRight != null) {
            relateRight.setVisibility(View.VISIBLE);
            objectAnimatorRight = ObjectAnimator.ofFloat(relateRight, "translationX", inX, 0);
            objectAnimatorRight.setDuration(300);
            objectAnimatorRight.start();
        }
    }

    /**
     * 右边发光
     */
    private void setRightLightAnimat() {
        if (imCenterShardsRight != null) {
            alphaAnimationRightLight = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            alphaAnimationRightLight.setDuration(250);
            alphaAnimationRightLight.setRepeatMode(Animation.REVERSE);
            imCenterShardsRight.setVisibility(View.VISIBLE);
            imCenterShardsRight.setAnimation(alphaAnimationRightLight);
            alphaAnimationRightLight.start();
        }
    }

    /**
     * 右边碎片
     */
    private void setRightShardsAnimat() {
        if (imRightShards != null) {
            imRightShards.setVisibility(View.VISIBLE);
            scaleAnimationRightShards = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimationRightShards.setDuration(150);
            scaleAnimationRightShards.setRepeatMode(Animation.REVERSE);
            imRightShards.setAnimation(scaleAnimationRightShards);
            scaleAnimationRightShards.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            scaleAnimationRightShards.start();
        }
    }

    public void relase() {


        if (scaleAnimationRightShards != null) {
            scaleAnimationRightShards.cancel();
            scaleAnimationRightShards = null;
        }
        if (scaleAnimationLeftShards != null) {
            scaleAnimationLeftShards.cancel();
            scaleAnimationLeftShards = null;
        }
        if (alphaAnimationLeftLight != null) {
            alphaAnimationLeftLight.cancel();
            alphaAnimationLeftLight = null;
        }
        if (alphaAnimationRightLight != null) {
            alphaAnimationRightLight.cancel();
            alphaAnimationRightLight = null;
        }
        if (scaleAnimationPk != null) {
            scaleAnimationPk.cancel();
            scaleAnimationPk = null;
        }
        if (scaleAnimation != null) {
            scaleAnimation.cancel();
            scaleAnimation = null;
        }
        if (objectAnimatorRight != null) {
            objectAnimatorRight.cancel();
            objectAnimatorRight = null;
        }

        if (objectAnimatorLeft != null) {
            objectAnimatorLeft.cancel();
            objectAnimatorLeft = null;
        }


        if (imPkcenterShards != null) {
            imPkcenterShards = null;
        }
        if (imOtherhead != null) {
            imOtherhead = null;
        }
        if (imOwnhead != null) {
            imOwnhead = null;
        }
        if (imRightShards != null) {
            imRightShards = null;
        }
        if (imLeftShards != null) {
            imLeftShards = null;
        }
        if (imCenterShardsRight != null) {
            imCenterShardsRight = null;
        }
        if (imCenterShards != null) {
            imCenterShards = null;
        }
        if (relateRight != null) {
            relateRight = null;
        }
        if (relateLeft != null) {
            relateLeft = null;
        }

    }
}
