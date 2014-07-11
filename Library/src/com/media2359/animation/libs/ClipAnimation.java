package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ClipAnimation extends Animation {

    AnimatorSet animClip = new AnimatorSet();

    public ClipAnimation() {
        animClip = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 0.5f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        animClip.setDuration(getDuration());
        animClip.playTogether(animX, alpha);
        if (getListener() != null) {
            animClip.addListener(getListener());
        }
        animClip.start();
    }

    @Override
    public void cancel(View v) {
        if (animClip != null) {
            animClip.cancel();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void reset(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v,Constant.SCALE_Y, 1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        animClip.setDuration(0);
        animClip.playTogether(animX, alpha);
        animClip.start();        
    }

}
