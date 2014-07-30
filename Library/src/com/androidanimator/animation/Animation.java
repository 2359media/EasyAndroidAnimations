package com.androidanimator.animation;

import android.animation.AnimatorSet;
import android.view.View;

/**
 * @author phutang
 * 
 */
public abstract class Animation {

//    protected AnimationListener listener;
//    protected long duration;
    
    public static final int DEFAULT_DURATION = 500; // 500 ms

//    public Animation(AnimationListener listener, long duration) {
//        super();
//        this.listener = listener;
//        this.duration = duration;
//    }
//
    public Animation() {
//        duration = Animation.DEFAULT_DURATION;
    };
//
//    public AnimationListener getListener() {
//        return listener;
//    }
//
//    public void setListener(AnimationListener listener) {
//        this.listener = listener;
//    }
//
//    public long getDuration() {
//        return duration;
//    }
//
//    public Animation setDuration(long duration) {
//        this.duration = duration;
//        return this;
//    }

    public abstract void animate(View view);

    public AnimatorSet getAnimatorSet(View view) {
        return null;
    }

    public interface AnimationListener {
        //TODO add onAnimationStart
        public void onAnimationEnd(Animation animation);
    }

}
