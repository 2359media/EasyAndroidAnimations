package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BlindAnimation extends Animation {

    AnimatorSet blindAnimationSet;
    View child;
    ViewGroup animationLayout;
    LayoutParams originalParam;
    Context mContext;

    public BlindAnimation(Context context) {
        blindAnimationSet = new AnimatorSet();
        mContext = context;
    }

    @Override
    public void performAnimation(View v) {
        addToAnimatioView(v);
        
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_Y, 1f, 0f);
        child.setPivotX(1f);
        child.setPivotY(1f);
        ObjectAnimator scaleY_child = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 1f, 2.5f);
        blindAnimationSet.playTogether(scaleY, scaleY_child);
        blindAnimationSet.setDuration(getDuration());
        if (getListener() != null) {
            blindAnimationSet.addListener(getListener());
        }
        animationLayout.setPivotX(1f);
        animationLayout.setPivotY(1f);
        blindAnimationSet.start();
    }

    @Override
    public void cancel(View v) {

    }

    @Override
    public void reset(View v) {
        
        ViewGroup parent = (ViewGroup) animationLayout.getParent();
        parent.removeView(animationLayout);
        animationLayout.removeView(child);
        child.setLayoutParams(originalParam);
        parent.addView(child);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(child, Constant.SCALE_Y, 1f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(child, Constant.ALPHA, 1f);
        blindAnimationSet.playTogether(scaleY, alphaA);
        blindAnimationSet.setDuration(0);
        blindAnimationSet.start();
        
    }

    public void addToAnimatioView(View v) {
        child = v;
        ViewGroup parent = (ViewGroup) v.getParent();
        parent.removeView(v);
        originalParam = v.getLayoutParams();
        LayoutParams newParam = new LayoutParams(v.getWidth(), v.getHeight());
        v.setLayoutParams(newParam);
        animationLayout = new FrameLayout(mContext);
        animationLayout.setId(v.getId());
        animationLayout.setLayoutParams(originalParam);
        animationLayout.addView(v);
        parent.addView(animationLayout);
    }

}
