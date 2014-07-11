package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class TransferAnimation extends Animation {
    AnimatorSet transferAnimatorSet;
    private float distance;

    public TransferAnimation() {
        transferAnimatorSet = new AnimatorSet();
        properties.put(Constant.PROPERTY_DISTANCE, -1f);
    }

    @Override
    public void performAnimation(View v) {
        distance = properties.get(Constant.PROPERTY_DISTANCE);
        if (distance == -1) {
            distance = v.getHeight() * 2;

        }
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 0f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f, 0f);
        ObjectAnimator translation = ObjectAnimator.ofFloat(v, Constant.TRANSLATION_Y, 0, distance);
        transferAnimatorSet.playTogether(scaleX, scaleY, alphaA, translation);
        transferAnimatorSet.setDuration(getDuration());
        if (getListener() != null) {
            transferAnimatorSet.addListener(getListener());
        }
        transferAnimatorSet.start();
    }

    @Override
    public void cancel(View v) {
        transferAnimatorSet.cancel();
    }

    @Override
    public void reset(View v) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f);
        ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        ObjectAnimator translation = ObjectAnimator.ofFloat(v, Constant.TRANSLATION_Y, distance, 0);

        AnimatorSet scale = new AnimatorSet();
        scale.playTogether(scaleX, scaleY, alphaA, translation);
        scale.start();
    }

}
