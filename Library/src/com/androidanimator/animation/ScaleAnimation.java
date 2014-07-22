package com.androidanimator.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author phutang
 *
 */
public class ScaleAnimation extends Animation {
    AnimatorSet scaleAnimatorSet;
    int type;

    public ScaleAnimation() {
        scaleAnimatorSet = new AnimatorSet();
        type = Constant.OUT;
    }

    public ScaleAnimation(AnimatorListener listener, long duration, int type) {
        super(listener, duration);
        this.type = type;
        scaleAnimatorSet = new AnimatorSet();
    }

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        scaleAnimatorSet.start();
    }
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        ObjectAnimator scaleX,scaleY;
        if(type==Constant.OUT){
         scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f, 0f);
         scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 0f);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 0f, 1f);
            scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 0f, 1f);
        }
        AnimatorSet scale = new AnimatorSet();
        scale.playTogether(scaleX, scaleY);
        scaleAnimatorSet.setDuration(getDuration());
        if (getListener() != null) {
            scaleAnimatorSet.addListener(getListener());
        }
        return scaleAnimatorSet;
    }

}
