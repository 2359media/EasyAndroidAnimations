package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class DropAnimation extends Animation {
    AnimatorSet animDrop = new AnimatorSet();
    private float distance;

    public DropAnimation() {
        animDrop = new AnimatorSet();
        properties.put(Constant.PROPERTY_DISTANCE, -1f);
    }

    @Override
    public void performAnimation(View v) {
        distance = properties.get(Constant.PROPERTY_DISTANCE);
        if (distance == -1) {
            distance = v.getWidth() * 2;

        }
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.TRANSLATION_X, 0, -distance);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        animDrop.setDuration(getDuration());
        animDrop.playTogether(animX, alpha);
        if (getListener() != null) {
            animDrop.addListener(getListener());
        }
        animDrop.start();
    }

    @Override
    public void cancel(View v) {
        animDrop.cancel();
    }

    @Override
    public void reset(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.TRANSLATION_X, -distance, 0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        animDrop.playTogether(animX, alpha);
        animDrop.start();
    }

}
