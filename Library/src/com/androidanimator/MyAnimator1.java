package com.androidanimator;

import com.androidanimator.animation.Animation;
import com.androidanimator.animation.BlindAnimation1;
import com.androidanimator.animation.BounceAnimation1;
import com.androidanimator.animation.ClipAnimation1;
import com.androidanimator.animation.ExplodeAnimation1;
import com.androidanimator.animation.HighlightAnimation1;
import com.androidanimator.animation.PulsateAnimation1;
import com.androidanimator.animation.ScaleAnimation1;
import com.androidanimator.animation.ShakeAnimation1;
import com.androidanimator.animation.SlideInAnimation1;
import com.androidanimator.animation.SlideOutAnimation1;
import com.androidanimator.animation.TransferAnimation1;

import android.view.View;

public class MyAnimator1 {

	static Animation animation;

	/**
	 * The BlindAnimation1 makes use of a box that is of the same size as the
	 * view to translate upwards to mimic the blind animation.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public static void blind(View view) {
		new BlindAnimation1().animate(view);
	}

	/**
	 * The BlindAnimation1 makes use of a box that is of the same size as the
	 * view to translate upwards to mimic the blind animation.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param color
	 *            the color of the box used for blinding
	 * @param duration
	 *            the duration of the entire animation
	 */
	public static void blind(View view, int color, long duration) {
		animation = new BlindAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
	}

	/**
	 * The BounceAnimation1 causes the view to bounce by translating up and down
	 * for a number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public static void bounce(View view) {
		new BounceAnimation1().animate(view);
	}

	/**
	 * The BounceAnimation1 causes the view to bounce by translating up and down
	 * for a number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param bounceDistance
	 *            the maximum distance of the bounce
	 * @param repetitions
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 */
	public static void bounce(View view, float bounceDistance, int repetitions,
			long duration) {
		animation = new BounceAnimation1(bounceDistance, repetitions);
		animation.setDuration(duration);
		animation.animate(view);
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
		animation = new ClipAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
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
		animation = new ExplodeAnimation1(xParts, yParts);
		animation.setDuration(duration);
		animation.animate(view);
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
		animation = new HighlightAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
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
		animation = new PulsateAnimation1(repetitions);
		animation.setDuration(duration);
		animation.animate(view);
	}

	/**
	 * The ScaleAnimation1 scales the view either upwards or downwards depending
	 * on the parameters provided by the user before fading out of view.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public static void scale(View view) {
		new ScaleAnimation1().animate(view);
	}

	/**
	 * The ScaleAnimation1 scales the view either upwards or downwards depending
	 * on the parameters provided by the user before fading out of view.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param x
	 *            the magnitude of scale in the x-axis
	 * @param y
	 *            the magnitude of scale in the y-axis
	 * @param duration
	 *            the duration of the entire animation
	 */
	public static void scale(View view, int x, int y, long duration) {
		animation = new ScaleAnimation1(x, y);
		animation.setDuration(duration);
		animation.animate(view);
	}

	/**
	 * The ShakeAnimation1 causes the view to shake from left to right for a
	 * number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public static void shake(View view) {
		new ShakeAnimation1().animate(view);
	}

	/**
	 * The ShakeAnimation1 causes the view to shake from left to right for a
	 * number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param shakeDistance
	 *            the maximum distance of the shake
	 * @param repetitions
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 */
	public static void shake(View view, float shakeDistance, int repetitions,
			long duration) {
		animation = new ShakeAnimation1(shakeDistance, repetitions);
		animation.setDuration(duration);
		animation.animate(view);
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
	public static void slideIn(View view, String direction, long duration) {
		animation = new SlideInAnimation1(direction);
		animation.setDuration(duration);
		animation.animate(view);
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
	public static void slideOut(View view, String direction, long duration) {
		animation = new SlideOutAnimation1(direction);
		animation.setDuration(duration);
		animation.animate(view);
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
		animation = new TransferAnimation1(destinationView);
		animation.setDuration(duration);
		animation.animate(view);
	}

}
