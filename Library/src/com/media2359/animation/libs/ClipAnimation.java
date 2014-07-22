package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ClipAnimation extends Animation {

    AnimatorSet animClip = new AnimatorSet();
    int oritention;

    public ClipAnimation() {
        animClip = new AnimatorSet();
        oritention = Constant.VERTICAL;
    }
    
    public ClipAnimation(AnimatorListener listener, long duration, int oritention) {
        super(listener, duration);
        this.oritention = oritention;
        animClip = new AnimatorSet();
    }



    public int getOritention() {
        return oritention;
    }

    public void setOritention(int oritention) {
        this.oritention = oritention;
    }

    @Override
    public void animate(View v) {
        // get property
        //
        getAnimatorSet(v);
        animClip.start();
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        ObjectAnimator animX;
        if (oritention == Constant.VERTICAL) {
            animX = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 0.5f);
        } else {
            animX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 0.5f);
        }
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 0f);
        animClip.setDuration(getDuration());
        animClip.playTogether(animX, alpha);
        if (getListener() != null) {
            animClip.addListener(getListener());
        }
        return animClip;
    }

}
