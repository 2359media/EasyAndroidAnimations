package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.view.View;

public abstract class Animation {

    private AnimatorListener listener;
    long duration;

    public Animation(AnimatorListener listener, long duration) {
        super();
        this.listener = listener;
        this.duration = duration;
    }

    public Animation() {
        duration = Constant.DEFAULT_DURATION;
    };

    public AnimatorListener getListener() {
        return listener;
    }

    public void setListener(AnimatorListener listener) {
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

}
