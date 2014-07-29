package com.androidanimator.animation;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;

/**
 * @author phutang
 * 
 */
public class BounceAnimationPhu extends Animation {
	
	public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int AMPLITUDE = 10;
    AnimatorSet bounceAnim;
    int oritention;
    float amp;

    public BounceAnimationPhu() {
        bounceAnim = new AnimatorSet();
        oritention = BounceAnimationPhu.HORIZONTAL;
        amp = BounceAnimationPhu.AMPLITUDE;
    }

    public BounceAnimationPhu(AnimationListener listener, long duration, int oritention, float amp) {
        super(listener, duration);
        bounceAnim = new AnimatorSet();
        this.oritention = oritention;
        this.amp = amp;
    }

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        bounceAnim.start();
    }

    public AnimatorSet getBounceAnim() {
        return bounceAnim;
    }

    public void setBounceAnim(AnimatorSet bounceAnim) {
        this.bounceAnim = bounceAnim;
    }

    public int getOritention() {
        return oritention;
    }

    public void setOritention(int oritention) {
        this.oritention = oritention;
    }

    public float getAmp() {
        return amp;
    }

    public void setAmp(float amp) {
        this.amp = amp;
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        Property<View, Float> translate_type;
        if (oritention == BounceAnimationPhu.HORIZONTAL) {
            translate_type = View.TRANSLATION_X;
        } else {
            translate_type = View.TRANSLATION_Y;
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
        if (getListener() != null) {
            bounceAnim.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(BounceAnimationPhu.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
        }
        return bounceAnim;
    }

}
