package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleAnimation extends AnimationLibrary {
    AnimatorSet scaleAnimatorSet;

    public ScaleAnimation() {
        scaleAnimatorSet = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        AnimatorSet scale = new AnimatorSet();
        scale.playTogether(scaleX, scaleY);
        scaleAnimatorSet.playSequentially(scale, alphaA);
        scaleAnimatorSet.setDuration(getDuration());
        if(getListener()!=null){
            scaleAnimatorSet.addListener(getListener());
        }
        scaleAnimatorSet.start();
    }

    @Override
    public void cancel(View v) {
        scaleAnimatorSet.cancel();
    }

}
