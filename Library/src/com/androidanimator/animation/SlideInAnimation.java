package com.androidanimator.animation;

import com.androidanimator.AndroidAnimator;
import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author SiYao
 * 
 */
public class SlideInAnimation extends Animation {

	int direction;
	long duration;
	AnimationListener listener;

	/**
	 * The SlideInAnimation causes the view to slide in from the left, right,
	 * top or bottom of the screen depending on the parameters provided by the
	 * user.
	 * 
	 * @param direction
	 *            the direction to slide in from
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public SlideInAnimation() {
		direction = AndroidAnimator.DIRECTION_LEFT;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		int[] locationView = new int[2];
		view.getLocationOnScreen(locationView);

		ObjectAnimator slideAnim = null;
		switch (direction) {
		case AndroidAnimator.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, -locationView[0]
					- view.getWidth(), view.getX());
			break;
		case AndroidAnimator.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X,
					rootView.getRight(), view.getX());
			break;
		case AndroidAnimator.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, -locationView[1]
					- view.getHeight(), view.getY());
			break;
		case AndroidAnimator.DIRECTION_DOWN:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y,
					rootView.getBottom(), view.getY());
			break;
		default:
			break;
		}
		view.setVisibility(View.VISIBLE);
		slideAnim.setDuration(duration);
		slideAnim.start();
		slideAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(SlideInAnimation.this);
				}
			}
		});
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public SlideInAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public SlideInAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return the listener
	 */
	public AnimationListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public SlideInAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
