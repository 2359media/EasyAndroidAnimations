package com.androidanimator.animation;

import com.androidanimator.AndroidAnimator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;

/**
 * @author phutang
 * 
 */
public class DropAnimation extends Animation {
    AnimatorSet animDrop = new AnimatorSet();
    private float distance;
    private int direction;
    private int type;

    public DropAnimation() {
        animDrop = new AnimatorSet();
        distance = -1;
        type = AndroidAnimator.BEHAVIOR_OUT;
    }

    public DropAnimation(AnimationListener listener, long duration, float distance, int direction, int type) {
        super(listener, duration);
        this.distance = distance;
        this.direction = direction;
        this.type = type;
        animDrop = new AnimatorSet();
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

    @Override
    public void animate(View v) {
        getAnimatorSet(v);
        animDrop.start();
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        if (distance == -1) {
            distance = v.getWidth() * 2;
        }
        ObjectAnimator animX;
        Property<View, Float> translateType = null;
        switch (direction) {
        case AndroidAnimator.DIRECTION_LEFT:
            translateType = View.TRANSLATION_X;
            distance = (-1) * distance;
            break;
        case AndroidAnimator.DIRECTION_RIGHT:
            translateType = View.TRANSLATION_X;

            break;
        case AndroidAnimator.DIRECTION_UP:
            translateType = View.TRANSLATION_Y;
            distance = (-1) * distance;
            break;
        case AndroidAnimator.DIRECTION_DOWN:
            translateType = View.TRANSLATION_Y;

            break;
        default:
            break;
        }
        // TODO
        ObjectAnimator alpha;
        if (type == AndroidAnimator.BEHAVIOR_OUT) {
            animX = ObjectAnimator.ofFloat(v, translateType, 0, distance);
            alpha = ObjectAnimator.ofFloat(v, View.ALPHA, v.getAlpha(), 0f);
        } else {
        	v.setVisibility(View.VISIBLE);
            animX = ObjectAnimator.ofFloat(v, translateType, distance, 0);
            alpha = ObjectAnimator.ofFloat(v, View.ALPHA, v.getAlpha(), 1f);
        }
        animDrop.setDuration(getDuration());
        animDrop.playTogether(animX, alpha);
        if (getListener() != null) {
            animDrop.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getListener().onAnimationEnd(DropAnimation.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
        }
        return animDrop;
    }

}
