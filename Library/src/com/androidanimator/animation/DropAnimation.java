package com.androidanimator.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
        type = Constant.OUT;
    }

    public DropAnimation(AnimatorListener listener, long duration, float distance, int direction, int type) {
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

    public void dropIn(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.TRANSLATION_X, -distance, 0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        animDrop.playTogether(animX, alpha);
        if (getListener() != null) {
            animDrop.addListener(getListener());
        }
        animDrop.start();
    }

    /**
 * 
 */
    @Override
    public AnimatorSet getAnimatorSet(View v) {
        if (distance == -1) {
            distance = v.getWidth() * 2;
        }
        ObjectAnimator animX;
        String translateType = null;
        switch (direction) {
        case Constant.DIRECTION_LEFT:
            translateType = Constant.TRANSLATION_X;
            distance = (-1) * distance;
            break;
        case Constant.DIRECTION_RIGHT:
            translateType = Constant.TRANSLATION_X;

            break;
        case Constant.DIRECTION_UP:
            translateType = Constant.TRANSLATION_Y;
            distance = (-1) * distance;
            break;
        case Constant.DIRECTION_DOWN:
            translateType = Constant.TRANSLATION_Y;

            break;
        default:
            break;
        }
        // TODO
        ObjectAnimator alpha;
        if (type == Constant.OUT) {
            animX = ObjectAnimator.ofFloat(v, translateType, 0, distance);
            alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 0f);
        } else {
            animX = ObjectAnimator.ofFloat(v, translateType, distance, 0);
            alpha = ObjectAnimator.ofFloat(v, Constant.ALPHA, v.getAlpha(), 1f);
        }
        animDrop.setDuration(getDuration());
        animDrop.playTogether(animX, alpha);
        if (getListener() != null) {
            animDrop.addListener(getListener());
        }
        return animDrop;
    }

}
