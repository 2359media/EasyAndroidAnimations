package com.androidanimator.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/**
 * @author phutang
 *
 */
public class BlindAnimation extends Animation {

    AnimatorSet blindAnimationSet;
    View child;
    ViewGroup animationLayout;
    LayoutParams originalParam;
    Context mContext;

    public BlindAnimation() {
        blindAnimationSet = new AnimatorSet();
    }
    

    public BlindAnimation(AnimationListener listener, long duration) {
        super(listener, duration);
        blindAnimationSet = new AnimatorSet();
    }

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        blindAnimationSet.start();
    }


    public View addToAnimatioView(View v) {
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
        return animationLayout;
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        addToAnimatioView(v);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_Y, 1f, 0f);
        child.setPivotX(1f);
        child.setPivotY(1f);
        ObjectAnimator scaleY_child = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 1f, 2.5f);
        blindAnimationSet.playTogether(scaleY, scaleY_child);
        blindAnimationSet.setDuration(getDuration());
        if (getListener() != null) {
            blindAnimationSet.addListener(new AnimatorListener() {
                
                @Override
                public void onAnimationStart(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onAnimationRepeat(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(BlindAnimation.this);
                }
                
                @Override
                public void onAnimationCancel(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
            });
        }
        animationLayout.setPivotX(1f);
        animationLayout.setPivotY(1f);
        return blindAnimationSet;
    }

}
