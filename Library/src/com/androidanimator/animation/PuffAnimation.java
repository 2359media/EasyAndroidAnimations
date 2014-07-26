package com.androidanimator.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author phutang
 * 
 */
public class PuffAnimation extends Animation {

    AnimatorSet animClip = new AnimatorSet();
    int type;

    public PuffAnimation() {
        animClip = new AnimatorSet();
        type = Constant.OUT;
    }

    public PuffAnimation(AnimationListener listener, long duration, int type) {
        super(listener, duration);
        this.type = type;
        animClip = new AnimatorSet();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        animClip.start();
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        ObjectAnimator scaleY, scaleX, alpha;
        if (type == Constant.OUT) {
            scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 4f);
            scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f, 4f);
            alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 0f);
        } else {
        	v.setVisibility(View.VISIBLE);
            scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 4, 1);
            scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 4, 1);
            alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 1f);
        }
        animClip.setDuration(getDuration());
        animClip.playTogether(scaleY, scaleX, alpha);
        if (getListener() != null) {
            animClip.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(PuffAnimation.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
        }
        return animClip;
    }

}
