package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class PuffAnimation extends AnimationLibrary {

    AnimatorSet animClip = new AnimatorSet();

    public PuffAnimation() {
        animClip = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", v.getHeight());
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", v.getWidth());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
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

}
