package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class PuffAnimation extends Animation {

    AnimatorSet animClip = new AnimatorSet();

    public PuffAnimation() {
        animClip = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, v.getHeight());
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, v.getWidth());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        animClip.setDuration(getDuration());
        animClip.playTogether(scaleY, scaleX, alpha);
        if (getListener() != null) {
            animClip.addListener(getListener());
        }
        animClip.start();
    }

    @Override
    public void cancel(View v) {
        animClip.cancel();
    }

    @Override
    public void reset(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X,1);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        animClip.setDuration(getDuration());
        animClip.playTogether(scaleY, scaleX, alpha);
        animClip.start();        
    }

}
