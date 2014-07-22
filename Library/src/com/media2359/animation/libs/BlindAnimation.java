package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
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
    

    public BlindAnimation(AnimatorListener listener, long duration, Context mContext) {
        super(listener, duration);
        this.mContext = mContext;
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
        animationLayout = new FrameLayout(mContext);
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
            blindAnimationSet.addListener(getListener());
        }
        animationLayout.setPivotX(1f);
        animationLayout.setPivotY(1f);
        return blindAnimationSet;
    }

}
