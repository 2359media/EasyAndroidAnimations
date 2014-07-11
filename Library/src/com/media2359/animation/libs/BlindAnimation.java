package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class BlindAnimation extends Animation {

    AnimatorSet blindAnimationSet;

    public BlindAnimation() {
        blindAnimationSet = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 0.5f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        blindAnimationSet.playTogether(scaleY, alphaA);
        blindAnimationSet.setDuration(getDuration());
        if(getListener()!=null){
            blindAnimationSet.addListener(getListener());
        }
        v.setPivotX(1f);
        v.setPivotY(1f);
        blindAnimationSet.start();
    }

    @Override
    public void cancel(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v,  Constant.SCALE_Y, 1f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        blindAnimationSet.playTogether(scaleY, alphaA);
        blindAnimationSet.setDuration(0);
        blindAnimationSet.start();
    }
    

}
