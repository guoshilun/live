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


public class PkSmallAnimation extends RelativeLayout {
    private int inX;
    private RelativeLayout relateLeftBg;
    private SimpleDraweeView leftHead;
    private TextView tvLeftName, tvLeftId;


    private RelativeLayout relateRightBg;
    private SimpleDraweeView rightHead;
    private TextView tvRightName, tvRightId;

    private ImageView centerShards, centerVs;
    private ObjectAnimator objectAnimatorRight, objectAnimatorLeft;

    private ScaleAnimation scaleAnimation;//vs动画
    private ScaleAnimation scaleAnimationPk;
    private boolean isPlaying = false;

    public PkSmallAnimation(Context context) {
        super(context);
        init(context);
    }

    public PkSmallAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PkSmallAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_pk_small_animation, this, true);
        relateLeftBg = findViewById(R.id.relate_left_bg);
        leftHead = findViewById(R.id.im_own_head);
        tvLeftName = findViewById(R.id.tv_own_name);
        tvLeftId = findViewById(R.id.tv_own_id);
        relateRightBg = findViewById(R.id.relate_right_bg);
        rightHead = findViewById(R.id.im_other_head);
        tvRightName = findViewById(R.id.tv_other_name);
        tvRightId = findViewById(R.id.tv_other_id);
        centerShards = findViewById(R.id.im_pk_center_shards);
        centerVs = findViewById(R.id.im_pk_center);
        inX = getResources().getDimensionPixelSize(R.dimen.dp_160);

    }

    /**
     * 右边的动画
     */
    private void setRelateRightAnimat() {
        if (relateRightBg != null) {
            isPlaying = true;
            relateRightBg.setVisibility(View.VISIBLE);
            objectAnimatorRight = ObjectAnimator.ofFloat(relateRightBg, "translationX", inX, 0);
            objectAnimatorRight.setDuration(500);
            objectAnimatorRight.start();
        }
    }

    /**
     * 左边的动画
     */
    private void setRelateLeftAnimat() {
        if (relateLeftBg != null) {
            relateLeftBg.setVisibility(View.VISIBLE);
            objectAnimatorLeft = ObjectAnimator.ofFloat(relateLeftBg, "translationX", -inX, 0);
            objectAnimatorLeft.setDuration(500);
            objectAnimatorLeft.start();
            objectAnimatorLeft.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    relateRightBg.setVisibility(View.INVISIBLE);
                    centerShards.setVisibility(View.INVISIBLE);
                    centerVs.setVisibility(View.INVISIBLE);

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
        }
    }

    /**
     * 中间Vs动画
     */
    private void setPKCenterVSShards() {

//        scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        scaleAnimation.setDuration(300);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
//        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                setCententShards();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        centerVs.startAnimation(scaleAnimation);
//

        if (centerVs != null) {
            centerVs.setVisibility(View.VISIBLE);
            centerVs.clearAnimation();
        }

        scaleAnimation = new ScaleAnimation(0.0f, 1.2f, 0.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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
        if (centerVs != null) {
            centerVs.startAnimation(scaleAnimation);
        }
    }

    /**
     * 中间黄色碎片
     */
    private void setCententShards() {
        scaleAnimationPk = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimationPk.setDuration(250);
        scaleAnimationPk.setRepeatMode(Animation.REVERSE);
        if (centerShards != null) {
            centerShards.setVisibility(View.VISIBLE);
            centerShards.setAnimation(scaleAnimationPk);
        }
        scaleAnimationPk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isPlaying = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void relase() {
        if (leftHead != null) {
            leftHead = null;
        }

        if (tvLeftName != null) {
            tvLeftName = null;
        }

        if (tvLeftId != null) {
            tvLeftId = null;
        }
        if (relateLeftBg != null) {
            relateLeftBg = null;
        }
        if (rightHead != null) {
            rightHead = null;
        }

        if (relateRightBg != null) {
            relateRightBg = null;
        }
        if (tvRightId != null) {
            tvRightId = null;
        }
        if (tvRightName != null) {
            tvRightName = null;
        }
        if (centerShards != null) {
            centerShards = null;
        }
        if (centerVs != null) {
            centerVs = null;
        }

        if (objectAnimatorLeft != null) {
//            objectAnimatorLeft.cancel();
            objectAnimatorLeft = null;
        }
        if (objectAnimatorRight != null) {
//            objectAnimatorRight.cancel();
            objectAnimatorRight = null;
        }
        if (scaleAnimationPk != null) {
//            scaleAnimationPk.cancel();
            scaleAnimationPk = null;
        }
        if (scaleAnimation != null) {
//            scaleAnimation.cancel();
            scaleAnimation = null;
        }
    }
}
