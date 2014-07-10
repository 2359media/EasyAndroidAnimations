package com.media2359.animation.libs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class PulstateAnimation extends AnimationLibrary {
    AnimatorSet animPulse;
    int count, total;
    public int TIME_INTERVAL = 50;

    public PulstateAnimation() {
        animPulse = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        total = getDuration() / TIME_INTERVAL;
        animPulse.setDuration(TIME_INTERVAL);
        animPulse.playSequentially(alpha, alpha1);
        animPulse.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (count < total) {
                    animPulse.start();
                    count++;
                } else {
                    if (getListener() != null) {
                        animPulse.removeAllListeners();
                        animPulse.addListener(getListener());
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        animPulse.start();
    }

    @Override
    public void cancel(View v) {
        animPulse.cancel();
    }
}
