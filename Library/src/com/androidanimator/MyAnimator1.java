package com.androidanimator;

import android.view.View;

import com.androidanimator.animation.Animation.AnimationListener;
import com.androidanimator.animation.BlindAnimation;
import com.androidanimator.animation.BounceAnimation;
import com.androidanimator.animation.ClipAnimation1;
import com.androidanimator.animation.Constant;
import com.androidanimator.animation.DropAnimation;
import com.androidanimator.animation.ExplodeAnimation1;
import com.androidanimator.animation.FadeAnimation;
import com.androidanimator.animation.FoldAnimation;
import com.androidanimator.animation.HighlightAnimation1;
import com.androidanimator.animation.MoveAnimation;
import com.androidanimator.animation.PuffAnimation;
import com.androidanimator.animation.PulsateAnimation1;
import com.androidanimator.animation.ScaleAnimation;
import com.androidanimator.animation.SizeAnimation;
import com.androidanimator.animation.SlideInAnimation1;
import com.androidanimator.animation.SlideOutAnimation1;
import com.androidanimator.animation.TransferAnimation1;

public class MyAnimator1 {

    /**
     * The BlindAnimation1 makes use of a box that is of the same size as the
     * view to translate upwards to mimic the blind animation.
     * 
     * @param view
     *            the view to be animated
     */
    public static void blind(View view) {
        new BlindAnimation().animate(view);
    }

    /**
     * The BlindAnimation1 makes use of a box that is of the same size as the
     * view to translate upwards to mimic the blind animation.
     * 
     * @param listener
     *            listener of animator @see {@link AnimationListener}
     * @param duration
     *            the duration of the entire animation
     * @param view
     *            the view to be animated
     */
    public static void blind(AnimationListener listener, long duration, View view) {
        new BlindAnimation(listener, duration).animate(view);
    }

    /**
     * The BounceAnimation1 causes the view to bounce by translating up and down
     * for a number of times before returning to its original position.
     * 
     * @param view
     *            the view to be animated
     */
    public static void bounce(View view) {
        new BounceAnimation().animate(view);
    }

    /**
     * The BounceAnimation1 causes the view to bounce by translating up and down
     * for a number of times before returning to its original position.
     * 
     * @param listener
     *            listener of animator @see {@link AnimationListener}
     * @param bounceDistance
     *            the maximum distance of the bounce
     * @param repetitions
     *            the number of times the animation is repeated
     * @param duration
     *            the duration of the entire animation
     */

    /**
     * The BounceAnimation1 causes the view to bounce by translating up and down
     * for a number of times before returning to its original position.
     * 
     * @param view
     * @param duration
     * @param oritention
     * @param repetitions
     *            the number of times the animation is repeated
     * @param listener
     *            listener of animator @see {@link AnimationListener}
     */
    public static void bounce(View view, int duration, int oritention, int repetitions, AnimationListener listener) {
        new BounceAnimation(listener, duration, oritention, repetitions).animate(view);
    }

    /**
     * The ClipAnimation1 makes use of a box that is of the same size as the
     * view to translate upwards while translating the view downwards to mimic
     * the clip animation.
     * 
     * @param view
     *            the view to be animated
     */
    public static void clip(View view) {
        new ClipAnimation1().animate(view);
    }

    /**
     * The ClipAnimation1 makes use of a box that is of the same size as the
     * view to translate upwards while translating the view downwards to mimic
     * the clip animation.
     * 
     * @param view
     *            the view to be animated
     * @param color
     *            the color of the box used for clipping
     * @param duration
     *            the duration of the entire animation
     */
    public static void clip(View view, int color, long duration) {
        new ClipAnimation1(color, duration).animate(view);
    }

    /**
     * The ExplodeAnimation1 creates a bitmap of the view, divides them into X x
     * Y parts and translates the parts away from the centre of the view to
     * mimic an explosion.
     * 
     * @param view
     *            the view to be animated
     */
    public static void explode(View view) {
        new ExplodeAnimation1().animate(view);
    }

    /**
     * The ExplodeAnimation1 creates a bitmap of the view, divides them into X x
     * Y parts and translates the parts away from the centre of the view to
     * mimic an explosion.
     * 
     * @param view
     *            the view to be animated
     * @param xParts
     *            the number of x parts to be exploded
     * @param yParts
     *            the number of y parts to be exploded
     * @param duration
     *            the duration of the entire animation
     */
    public static void explode(View view, int xParts, int yParts, long duration) {
        new ExplodeAnimation1(xParts, yParts, duration).animate(view);
    }

    /**
     * The HighlightAnimation1 makes use of a translucent box to overlay the
     * view to mimic the highlighting of the view.
     * 
     * @param view
     *            the view to be animated
     */
    public static void highlight(View view) {
        new HighlightAnimation1().animate(view);
    }

    /**
     * The HighlightAnimation1 makes use of a translucent box to overlay the
     * view to mimic the highlighting of the view.
     * 
     * @param view
     *            the view to be animated
     * @param color
     *            the color of the highlight
     * @param duration
     *            the duration of the entire animation
     */
    public static void highlight(View view, int color, long duration) {
        new HighlightAnimation1(color, duration).animate(view);
    }

    /**
     * The PulsateAnimation1 causes the view to blink a number of times to mimic
     * a pulsating animation.
     * 
     * @param view
     *            the view to be animated
     */
    public static void pulsate(View view) {
        new PulsateAnimation1().animate(view);
    }

    /**
     * The PulsateAnimation1 causes the view to blink a number of times to mimic
     * a pulsating animation.
     * 
     * @param view
     *            the view to be animated
     * @param repetitions
     *            the number of times the animation is repeated
     * @param duration
     *            the duration of the entire animation
     */
    public static void pulsate(View view, int repetitions, long duration) {
        new PulsateAnimation1(repetitions, duration).animate(view);
    }

    public static void scaleIn(View view, long duration, AnimationListener listener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(duration);
        scaleAnimation.setListener(listener);
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(view);
    }

    /**
     * The ScaleAnimationIn scales in the view either upwards or downwards depending
     * on the parameters provided by the user before fading out of view.
     * 
     * @param view
     *            the view to be animated
     */
    public static void scaleIn(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.setType(Constant.IN);
        scaleAnimation.animate(view);
    }

    /**
     * The ScaleAnimationOut scales out the view either upwards or downwards depending
     * on the parameters provided by the user before fading out of view.
     * 
     * @param view
     *            the view to be animated
     */
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


    /**
     * The SlideInAnimation1 causes the view to slide in from the left, right,
     * top or bottom depending on the parameters provided by the user.
     * 
     * @param view
     *            the view to be animated
     */
    public static void slideIn(View view) {
        new SlideInAnimation1().animate(view);
    }

    /**
     * The SlideInAnimation1 causes the view to slide in from the left, right,
     * top or bottom depending on the parameters provided by the user.
     * 
     * @param view
     *            the view to be animated
     * @param direction
     *            the direction to slide in from
     * @param duration
     *            the duration of the entire animation
     */
    public static void slideIn(View view, int direction, long duration) {
        new SlideInAnimation1(direction, duration).animate(view);
    }

    /**
     * The SlideOutAnimation1 causes the view to slide out to the left, right,
     * top or bottom depending on the parameters provided by the user.
     * 
     * @param view
     *            the view to be animated
     */
    public static void slideOut(View view) {
        new SlideOutAnimation1().animate(view);
    }

    /**
     * The SlideOutAnimation1 causes the view to slide out to the left, right,
     * top or bottom depending on the parameters provided by the user.
     * 
     * @param view
     *            the view to be animated
     * @param direction
     *            the direction to slide out to
     * @param duration
     *            the duration of the entire animation
     */
    public static void slideOut(View view, int direction, long duration) {
        new SlideOutAnimation1(direction, duration).animate(view);
    }

    /**
     * The TransferAnimation1 transfers the view to another view provided by the
     * user through scaling and translation.
     * 
     * @param view
     *            the view to be animated
     * @param destinationView
     *            the view to be transferred to
     * @param duration
     *            the duration of the entire animation
     */
    public static void transfer(View view, View destinationView, long duration) {
        new TransferAnimation1(destinationView, duration).animate(view);
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

    public static void fadeIn(View view) {
        new FadeAnimation(null, Constant.DEFAULT_DURATION, Constant.IN).animate(view);
    }

    public static void fadeOut(View view) {
        new FadeAnimation().animate(view);
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

}
