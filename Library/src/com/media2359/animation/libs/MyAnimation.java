package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.view.View;

public class MyAnimation {

    public static void Blind(Context context, View v) {
        new BlindAnimation(context).animate(v);
    }

    public static void bounce(View v) {
        new BounceAnimation().animate(v);
    }

    public static void bounce(View v, int duration, int oritention, int amp, AnimatorListener listener) {
        BounceAnimation animation = new BounceAnimation();
        animation.setDuration(duration);
        animation.setOritention(oritention);
        animation.setAmp(amp);
        animation.setListener(listener);
        animation.animate(v);
    }

    public static void clip(View v) {
        new ClipAnimation().animate(v);
    }

    public static void clip(View v, int duration, int oritention, AnimatorListener listener) {
        ClipAnimation clipAnimation = new ClipAnimation();
        clipAnimation.setDuration(duration);
        clipAnimation.setOritention(oritention);
        clipAnimation.setListener(listener);
        clipAnimation.animate(v);
    }

    public static void dropIn(View v) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.IN);
        animation.animate(v);
    }

    public static void dropIn(View v, int duration, int direction, AnimatorListener listener) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.IN);
        animation.setDuration(duration);
        animation.setDirection(direction);
        animation.animate(v);
    }

    public static void dropOut(View v) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.OUT);
        animation.animate(v);
    }

    public static void dropOut(View v, int duration, int direction, AnimatorListener listener) {
        DropAnimation animation = new DropAnimation();
        animation.setType(Constant.OUT);
        animation.setDuration(duration);
        animation.setDirection(direction);
        animation.animate(v);
    }

    public static void explode(Context context, View v) {
        new ExplodeAnimation(context).animate(v);
    }

    public static void puffOut(View v) {
        new PuffAnimation().animate(v);
    }

    public static void puffOut(View v, long duration, AnimatorListener listener) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setDuration(duration);
        puffAnimation.setListener(listener);
        puffAnimation.animate(v);
    }

    public static void puffIn(View v, long duration, AnimatorListener listener) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setDuration(duration);
        puffAnimation.setListener(listener);
        puffAnimation.setType(Constant.IN);
        puffAnimation.animate(v);
    }

    public static void puffIn(View v) {
        PuffAnimation puffAnimation = new PuffAnimation();
        puffAnimation.setType(Constant.IN);
        puffAnimation.animate(v);
    }

    public static void Pulstate(View v) {
        new PulstateAnimation().animate(v);
    }

    public static void scaleIn(View v, long duration, AnimatorListener listener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(duration);
        scaleAnimation.setListener(listener);
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(v);
    }

    public static void scaleIn(View v) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(v);
    }

    public static void scaleOut(View v) {
        new ScaleAnimation().animate(v);
    }

    public static void scaleOut(View v, long duration, AnimatorListener listener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(duration);
        scaleAnimation.setListener(listener);
        scaleAnimation.setType(Constant.OUT);
        scaleAnimation.animate(v);
    }

    public static void size(View v) {
        new SizeAnimation().animate(v);
    }

    public static void transferIn(View v) {
        TransferAnimation transferAnimation = new TransferAnimation();
        transferAnimation.setType(Constant.IN);
        transferAnimation.animate(v);
    }

    public static void transferIn(View v, long duration, int direction, AnimatorListener listener) {
        TransferAnimation transferAnimation = new TransferAnimation();
        transferAnimation.setType(Constant.IN);
        transferAnimation.setDuration(duration);
        transferAnimation.setListener(listener);
        transferAnimation.setDirection(direction);
        transferAnimation.animate(v);
    }

    public static void transferOut(View v, long duration, int direction, AnimatorListener listener) {
        TransferAnimation transferAnimation = new TransferAnimation();
        transferAnimation.setType(Constant.OUT);
        transferAnimation.setDuration(duration);
        transferAnimation.setListener(listener);
        transferAnimation.setDirection(direction);
        transferAnimation.animate(v);
    }

    public static void transferOut(View v) {
        new TransferAnimation().animate(v);
    }

    public static void fold(Context context, View v) {
        new FoldAnimation(context).animate(v);
    }

    public static void faceIn(View v) {
        new FadeAnimation(null, Constant.DEFAULT_DURATION, Constant.IN).animate(v);
    }

    public static void faceOut(View v) {
        new FadeAnimation().animate(v);
    }
}
