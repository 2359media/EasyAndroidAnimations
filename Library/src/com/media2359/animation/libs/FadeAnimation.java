package com.media2359.animation.libs;

import android.animation.ObjectAnimator;
import android.view.View;

public class FadeAnimation extends Animation {
    ObjectAnimator anim;

    public FadeAnimation() {
        anim = new ObjectAnimator();
    }

    @Override
    public void performAnimation(View v) {
        if (anim != null) {
            anim = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
            anim.setDuration(getDuration());
            if (getListener() != null) {
                anim.addListener(getListener());
            }
            anim.start();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void cancel(View v) {
        if (anim != null) {
            anim.cancel();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void reset(View v) {
        anim = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        anim.setDuration(getDuration());
        anim.start();
    }

}
