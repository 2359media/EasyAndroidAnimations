package com.androidanimator.animation;

import android.animation.AnimatorSet;
import android.view.View;

/**
 * @author phutang
 * 
 */
public abstract class Animation {

    protected AnimationListener listener;
    long duration;

    public Animation(AnimationListener listener, long duration) {
        super();
        this.listener = listener;
        this.duration = duration;
    }

    public Animation() {
        duration = Constant.DEFAULT_DURATION;
    };

    public AnimationListener getListener() {
        return listener;
    }

    public void setListener(AnimationListener listener) {
        this.listener = listener;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public abstract void animate(View v);

    public AnimatorSet getAnimatorSet(View v) {
        return null;
    }

    public interface AnimationListener {
        //TODO add onAnimationStart
        public void onAnimationEnd(Animation animation);
    }

}
