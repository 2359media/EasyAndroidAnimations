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

public class MyAnimator {
	
	static Animation animation;
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void blind(View view) {
		new BlindAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param color		the color of the box used for blinding
	 * @param duration	the duration of the entire animation 
	 */
	public static void blind(View view, int color, long duration) {
		animation = new BlindAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void bounce(View view) {
		new BounceAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view				the view to be animated
	 * @param bounceDistance	the maximum distance of the bounce
	 * @param repetitions		the number of times the animation is repeated
	 * @param duration			the duration of the entire animation 
	 */
	public static void bounce(View view, float bounceDistance, int repetitions, long duration) {
		animation = new BounceAnimation1(bounceDistance, repetitions);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void clip(View view) {
		new ClipAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param color		the color of the box used for clipping
	 * @param duration	the duration of the entire animation 
	 */
	public static void clip(View view, int color, long duration) {
		animation = new ClipAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	
	/**
	 * @param view	the view to be animated
	 */
	public static void explode(View view) {
		 new ExplodeAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param xParts	the number of x parts to be exploded
	 * @param yParts	the number of y parts to be exploded
	 * @param duration	the duration of the entire animation 
	 */
	public static void explode(View view, int xParts, int yParts, long duration) {
		animation = new ExplodeAnimation1(xParts, yParts);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void highlight(View view) {
		new HighlightAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param color		the color of the highlight
	 * @param duration	the duration of the entire animation 
	 */
	public static void highlight(View view, int color, long duration) {
		animation = new HighlightAnimation1(color);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void pulsate(View view) {
		new PulsateAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view			the view to be animated
	 * @param repetitions	the number of times the animation is repeated
	 * @param duration		the duration of the entire animation 
	 */
	public static void pulsate(View view, int repetitions, long duration) {
		animation = new PulsateAnimation1(repetitions);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void scale(View view) {
		new ScaleAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param x			the magnitude of scale in the x-axis
	 * @param y			the magnitude of scale in the y-axis
	 * @param duration	the duration of the entire animation 
	 */
	public static void scale(View view, int x, int y, long duration) {
		animation = new ScaleAnimation1(x, y);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void shake(View view) {
		new ShakeAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view			the view to be animated
	 * @param shakeDistance	the maximum distance of the shake
	 * @param repetitions	the number of times the animation is repeated
	 * @param duration		the duration of the entire animation 
	 */
	public static void shake(View view, float shakeDistance, int repetitions, long duration) {
		animation = new ShakeAnimation1(shakeDistance, repetitions);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void slideIn(View view) {
		new SlideInAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param direction	the direction to slide in from
	 * @param duration	the duration of the entire animation 
	 */
	public static void slideIn(View view, String direction, long duration) {
		animation = new SlideInAnimation1(direction);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view	the view to be animated
	 */
	public static void slideOut(View view) {
		new SlideOutAnimation1().animate(view);
	}
	
	/**
	 * 
	 * @param view		the view to be animated
	 * @param direction	the direction to slide out to
	 * @param duration	the duration of the entire animation 
	 */
	public static void slideOut(View view, String direction, long duration) {
		animation = new SlideOutAnimation1(direction);
		animation.setDuration(duration);
		animation.animate(view);
	}
	
	/**
	 * 
	 * @param view				the view to be animated
	 * @param destinationView	the view to be transferred to
	 * @param duration			the duration of the entire animation 
	 */
	public static void transfer(View view, View destinationView, long duration) {
		animation = new TransferAnimation1(destinationView);
		animation.setDuration(duration);
		animation.animate(view);
	}

}

 
