package com.androidanimator;

import com.androidanimator.animation.Animation.AnimationListener;
import com.androidanimator.animation.BlindAnimation;
import com.androidanimator.animation.BounceAnimation;
import com.androidanimator.animation.ClipAnimation;
import com.androidanimator.animation.Constant;
import com.androidanimator.animation.DropAnimation;
import com.androidanimator.animation.ExplodeAnimation;
import com.androidanimator.animation.FadeAnimation;
import com.androidanimator.animation.FoldAnimation;
import com.androidanimator.animation.PuffAnimation;
import com.androidanimator.animation.PulstateAnimation;
import com.androidanimator.animation.ScaleAnimation;
import com.androidanimator.animation.SizeAnimation;
import com.androidanimator.animation.MoveAnimation;

import android.content.Context;
import android.view.View;

/**
 * @author phutang
 *
 */
public class MyAnimation {

    /**
     * Blind animate with the given view.
     * @param view
     */
    public static void Blind(View view) {
        new BlindAnimation().animate(view);
    }
    /**
     * Bounce default animate with the given view
     * @param view
     */
    public static void bounce(View view) {
        new BounceAnimation().animate(view);
    }

    /**
     * Bounce animate view.
     * @param view
     * @param duration in milliseconds 
     * @param oritention vertical or horizontal @see {@link Constant}
     * @param amp the number of repeat of bounce should be less than 20
     * @param listener listener of animator @see {@link AnimationListener}
     */
    public static void bounce(View view, int duration, int oritention, int amp, AnimationListener listener) {
        BounceAnimation animation = new BounceAnimation();
        animation.setDuration(duration);
        animation.setOritention(oritention);
        animation.setAmp(amp);
        animation.setListener(listener);
        animation.animate(view);
    }

    public static void clip(View view) {
        new ClipAnimation().animate(view);
    }

    public static void clip(View view, int duration, int oritention, AnimationListener listener) {
        ClipAnimation clipAnimation = new ClipAnimation();
        clipAnimation.setDuration(duration);
        clipAnimation.setOritention(oritention);
        clipAnimation.setListener(listener);
        clipAnimation.animate(view);
    }

    public static void dropIn(View view) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.IN);
        animation.animate(view);
    }

    public static void dropIn(View view, int duration, int direction, AnimationListener listener) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.IN);
        animation.setDuration(duration);
        animation.setDirection(direction);
        animation.animate(view);
    }

    public static void dropOut(View view) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.OUT);
        animation.animate(view);
    }

    public static void dropOut(View view, int duration, int direction, AnimationListener listener) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.OUT);
        animation.setDuration(duration);
        animation.setDirection(direction);
        animation.animate(view);
    }

    public static void explode(Context context, View view) {
        new ExplodeAnimation(context).animate(view);
    }

    public static void puffOut(View view) {
        new PuffAnimation().animate(view);
    }

    public static void puffOut(View view, long duration, AnimationListener listener) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setDuration(duration);
        puffAnimation.setListener(listener);
        puffAnimation.animate(view);
    }

    public static void puffIn(View view, long duration, AnimationListener listener) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setDuration(duration);
        puffAnimation.setListener(listener);
        puffAnimation.setType(Constant.IN);
        puffAnimation.animate(view);
    }

    public static void puffIn(View view) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setType(Constant.IN);
        puffAnimation.animate(view);
    }

    public static void Pulstate(View view) {
        new PulstateAnimation().animate(view);
    }

    public static void scaleIn(View view, long duration, AnimationListener listener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(duration);
        scaleAnimation.setListener(listener);
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(view);
    }

    public static void scaleIn(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(view);
    }

    public static void scaleOut(View view) {
        new ScaleAnimation().animate(view);
    }

    public static void scaleOut(View view, long duration, AnimationListener listener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(duration);
        scaleAnimation.setListener(listener);
        scaleAnimation.setType(Constant.OUT);
        scaleAnimation.animate(view);
    }

    public static void size(View view) {
        new SizeAnimation().animate(view);
    }

    public static void moveIn(View view) {
        MoveAnimation transferAnimation = new MoveAnimation();
        transferAnimation.setType(Constant.IN);
        transferAnimation.animate(view);
    }

    public static void moveIn(View view, long duration, int direction, AnimationListener listener) {
        MoveAnimation transferAnimation = new MoveAnimation();
        transferAnimation.setType(Constant.IN);
        transferAnimation.setDuration(duration);
        transferAnimation.setListener(listener);
        transferAnimation.setDirection(direction);
        transferAnimation.animate(view);
    }

    public static void moveOut(View view, long duration, int direction, AnimationListener listener) {
        MoveAnimation transferAnimation = new MoveAnimation();
        transferAnimation.setType(Constant.OUT);
        transferAnimation.setDuration(duration);
        transferAnimation.setListener(listener);
        transferAnimation.setDirection(direction);
        transferAnimation.animate(view);
    }

    public static void moveOut(View view) {
        new MoveAnimation().animate(view);
    }

    public static void fold( View view) {
        new FoldAnimation().animate(view);
    }

    public static void faceIn(View view) {
        new FadeAnimation(null, Constant.DEFAULT_DURATION, Constant.IN).animate(view);
    }

    public static void faceOut(View view) {
        new FadeAnimation().animate(view);
    }
}
