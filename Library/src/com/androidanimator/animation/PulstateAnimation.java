package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author phutang
 *
 */
public class PulstateAnimation extends Animation {
    AnimatorSet animPulse;
    int count, total;
    public long TIME_INTERVAL = 50;

    public PulstateAnimation() {
        animPulse = new AnimatorSet();
    }
    
    

    public PulstateAnimation(AnimatorListener listener, long duration) {
        super(listener, duration);
        animPulse = new AnimatorSet();
    }



    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        animPulse.start();
    }


    @Override
    public AnimatorSet getAnimatorSet(View v) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(v, Constant.ALPHA, 0f, 1f);
        total = (int) (getDuration() / TIME_INTERVAL);
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
        return animPulse;
    }
}
