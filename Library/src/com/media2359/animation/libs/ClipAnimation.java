package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ClipAnimation extends AnimationLibrary {

    AnimatorSet animClip = new AnimatorSet();

    public ClipAnimation() {
        animClip = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "scaleY", 0.5f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
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

}
