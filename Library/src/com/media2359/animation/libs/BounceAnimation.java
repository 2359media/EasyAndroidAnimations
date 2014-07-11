package com.media2359.animation.libs;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class BounceAnimation extends Animation {
    public int AMPLITUDE = 10;
    AnimatorSet bounceAnim;
    int count, total;
    float amp;

    public BounceAnimation() {
        bounceAnim = new AnimatorSet();
        properties.put(Constant.PROPERTY_ORITENTION, Constant.HORIZONTAL);
        properties.put(Constant.PROPERTY_AMPLITUDE, (float) AMPLITUDE);
    }

    @Override
    public void performAnimation(View v) {
        amp = properties.get(Constant.PROPERTY_AMPLITUDE);
        String translate_type;
        if (properties.get(Constant.PROPERTY_ORITENTION) == Constant.HORIZONTAL) {
            translate_type = Constant.TRANSLATION_X;
        } else {
            translate_type = Constant.TRANSLATION_Y;
        }
        List<ObjectAnimator> move = new ArrayList<ObjectAnimator>();
        for (int i = 0; i <= amp; i++) {
            float currentAmp = amp - i;
            ObjectAnimator moveRight = ObjectAnimator.ofFloat(v, translate_type, -currentAmp, currentAmp);
            ObjectAnimator moveLeft = ObjectAnimator.ofFloat(v, translate_type, currentAmp, -currentAmp + 1);
            move.add(moveRight);
            move.add(moveLeft);
        }
        bounceAnim.playSequentially(move.toArray(new ObjectAnimator[move.size()]));
        bounceAnim.setDuration((int) amp * 5);
        bounceAnim.start();
    }

    @Override
    public void cancel(View v) {

    }

    @Override
    public void reset(View v) {

    }

}
