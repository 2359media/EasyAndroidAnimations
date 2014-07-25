package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/**
 * @author phutang
 * 
 */
public class FoldAnimation extends Animation {

    AnimatorSet animFold = new AnimatorSet();
    View child;
    ViewGroup animationLayout;
    LayoutParams originalParam;

    public FoldAnimation() {
        animFold = new AnimatorSet();
    }

    public FoldAnimation(AnimationListener listener, long duration) {
        super(listener, duration);
        animFold = new AnimatorSet();
    }

    @Override
    public void animate(View v) {

        getAnimatorSet(v);
        animFold.start();
    }

    public void reset(View v) {
        ViewGroup parent = (ViewGroup) animationLayout.getParent();
        parent.removeView(animationLayout);
        animationLayout.removeView(child);
        child.setLayoutParams(originalParam);
        parent.addView(child);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 1f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(child, Constant.SCALE_X, 1f);

        AnimatorSet animFold = new AnimatorSet();
        animFold.playTogether(scaleY, alphaA);
        animFold.setDuration(0);
        animFold.start();
    }

    public void addToAnimatioView(View v) {
        child = v;
        ViewGroup parent = (ViewGroup) v.getParent();
        parent.removeView(v);
        originalParam = v.getLayoutParams();
        LayoutParams newParam = new LayoutParams(v.getWidth(), v.getHeight());
        v.setLayoutParams(newParam);
        animationLayout = new FrameLayout(v.getContext());
        animationLayout.setId(v.getId());
        animationLayout.setLayoutParams(originalParam);
        animationLayout.addView(v);
        parent.addView(animationLayout);
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        addToAnimatioView(v);
        ObjectAnimator animY1 = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_Y, 1f, 0.5f);
        ObjectAnimator animY1_child = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 1f, 2f);

        ObjectAnimator animY2 = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_Y, 0.5f, 0f);
        ObjectAnimator animX = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_X, 1f, 0f);
        ObjectAnimator animY2_child = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 2f, 2.5f);
        ObjectAnimator animX_child = ObjectAnimator.ofFloat(child, Constant.SCALE_X, 1f, 2.5f);

        AnimatorSet fold2 = new AnimatorSet();
        fold2.playTogether(animY2, animX, animY2_child, animX_child);

        AnimatorSet step1 = new AnimatorSet();
        step1.playTogether(animY1, animY1_child);

        animFold.setDuration(getDuration());
        animFold.playSequentially(step1, fold2);
        if (getListener() != null) {
            animFold.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(FoldAnimation.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
        }
        animationLayout.setPivotX(1f);
        animationLayout.setPivotY(1f);
        child.setPivotX(1f);
        child.setPivotY(1f);
        return animFold;
    }
}
