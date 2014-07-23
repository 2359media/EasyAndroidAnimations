package com.androidanimator.animation;

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
public class MoveAnimation extends Animation {
    AnimatorSet transferAnimatorSet;
    private float distance;
    private int type, direction;

    public MoveAnimation() {
        transferAnimatorSet = new AnimatorSet();
        distance = -1;
        direction = Constant.DIRECTION_DOWN;
        type = Constant.OUT;
    }

    public MoveAnimation(AnimationListener listener, long duration, float distance, int type, int direction) {
        super(listener, duration);
        this.distance = distance;
        this.type = type;
        this.direction = direction;
        transferAnimatorSet = new AnimatorSet();
    }

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        transferAnimatorSet.start();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        if (distance == -1) {
            distance = v.getHeight() * 2;

        }
        Property<View, Float> sTranslation = null;
        switch (direction) {
        case Constant.DIRECTION_LEFT:
            sTranslation = Constant.TRANSLATION_X;
            distance = distance * (-1);
            break;
        case Constant.DIRECTION_RIGHT:
            sTranslation = Constant.TRANSLATION_X;
            break;
        case Constant.DIRECTION_UP:
            sTranslation = Constant.TRANSLATION_Y;
            distance = distance * (-1);
            break;
        case Constant.DIRECTION_DOWN:
            sTranslation = Constant.TRANSLATION_Y;
            break;
        }
        ObjectAnimator scaleX, scaleY, alphaA, translation;
        if (type == Constant.OUT) {
            scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 1f, 0f);
            scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 1f, 0f);
            alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 0f);
            translation = ObjectAnimator.ofFloat(v, sTranslation, 0, distance);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, Constant.SCALE_X, 0f, 1f);
            scaleY = ObjectAnimator.ofFloat(v, Constant.SCALE_Y, 0f, 1f);
            alphaA = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 1f);
            translation = ObjectAnimator.ofFloat(v, sTranslation, distance, 0);
        }
        transferAnimatorSet.playTogether(scaleX, scaleY, alphaA, translation);
        transferAnimatorSet.setDuration(getDuration());
        if (getListener() != null) {
            transferAnimatorSet.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(MoveAnimation.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
        }
        return transferAnimatorSet;
    }
}
