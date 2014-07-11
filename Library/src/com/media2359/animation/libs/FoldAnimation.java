package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class FoldAnimation extends Animation {

    AnimatorSet animFold = new AnimatorSet();

    public FoldAnimation() {
        animFold = new AnimatorSet();
    }

    @Override
    public void performAnimation(View v) {
        ObjectAnimator animY1 = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 0.5f);

        ObjectAnimator animY2 = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 0.5f, 0f);
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f, 0f);
        AnimatorSet fold2 = new AnimatorSet();
        fold2.playTogether(animY2, animX);

        animFold.setDuration(getDuration());
        animFold.playSequentially(animY1, fold2);
        if (getListener() != null) {
            animFold.addListener(getListener());
        }
        v.setPivotX(1f);
        v.setPivotY(1f);
        animFold.start();
    }

    @Override
    public void cancel(View v) {
        if (animFold != null) {
            animFold.cancel();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void reset(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1);
        animFold = new AnimatorSet();
        animFold.setDuration(getDuration());
        animFold.playTogether(scaleY, scaleX);
        animFold.start();
    }

}
