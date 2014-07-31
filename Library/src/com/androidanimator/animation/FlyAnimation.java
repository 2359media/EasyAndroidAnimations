//package com.androidanimator.animation;
//
//import com.androidanimator.AndroidAnimator;
//
//import android.animation.Animator.AnimatorListener;
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.util.Property;
//import android.view.View;
//
///**
// * @author phutang
// * 
// */
//public class FlyAnimation extends Animation {
//    AnimatorSet transferAnimatorSet;
//    private float distance;
//    private int type, direction;
//
//    public FlyAnimation() {
//        transferAnimatorSet = new AnimatorSet();
//        distance = -1;
//        direction = AndroidAnimator.DIRECTION_DOWN;
//        type = AndroidAnimator.BEHAVIOR_OUT;
//    }
//
//    public FlyAnimation(AnimationListener listener, long duration, float distance, int type, int direction) {
//        super(listener, duration);
//        this.distance = distance;
//        this.type = type;
//        this.direction = direction;
//        transferAnimatorSet = new AnimatorSet();
//    }
//
//    @Override
//    public void animate(View v) {
//        getAnimatorSet(v);
//        transferAnimatorSet.start();
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public int getDirection() {
//        return direction;
//    }
//
//    public void setDirection(int direction) {
//        this.direction = direction;
//    }
//
//    public float getDistance() {
//        return distance;
//    }
//
//    public void setDistance(float distance) {
//        this.distance = distance;
//    }
//
//    @Override
//    public AnimatorSet getAnimatorSet(View v) {
//        if (distance == -1) {
//            distance = v.getHeight() * 2;
//
//        }
//        Property<View, Float> sTranslation = null;
//        switch (direction) {
//        case AndroidAnimator.DIRECTION_LEFT:
//            sTranslation = View.TRANSLATION_X;
//            distance = distance * (-1);
//            break;
//        case AndroidAnimator.DIRECTION_RIGHT:
//            sTranslation = View.TRANSLATION_X;
//            break;
//        case AndroidAnimator.DIRECTION_UP:
//            sTranslation = View.TRANSLATION_Y;
//            distance = distance * (-1);
//            break;
//        case AndroidAnimator.DIRECTION_DOWN:
//            sTranslation = View.TRANSLATION_Y;
//            break;
//        }
//        ObjectAnimator scaleX, scaleY, alphaA, translation;
//        if (type == AndroidAnimator.BEHAVIOR_OUT) {
//            scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1f, 0f);
//            scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1f, 0f);
//            alphaA = ObjectAnimator.ofFloat(v, View.ALPHA, v.getAlpha(), 0f);
//            translation = ObjectAnimator.ofFloat(v, sTranslation, 0, distance);
//        } else {
//        	v.setVisibility(View.VISIBLE);
//            scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 0f, 1f);
//            scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 0f, 1f);
//            alphaA = ObjectAnimator.ofFloat(v, View.ALPHA, v.getAlpha(), 1f);
//            translation = ObjectAnimator.ofFloat(v, sTranslation, distance, 0);
//        }
//        transferAnimatorSet.playTogether(scaleX, scaleY, alphaA, translation);
//        transferAnimatorSet.setDuration(getDuration());
//        if (getListener() != null) {
//            transferAnimatorSet.addListener(new AnimatorListener() {
//
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    getListener().onAnimationEnd(FlyAnimation.this);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//            });
//        }
//        return transferAnimatorSet;
//    }
//}
