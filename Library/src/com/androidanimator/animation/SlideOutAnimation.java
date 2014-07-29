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
public class SlideOutAnimation extends Animation {

	int direction;
	ObjectAnimator slideAnim;
	
	/**
	 * The SlideOutAnimation causes the view to slide out to the left, right,
	 * top or bottom of the screen depending on the parameters provided by the
	 * user.
	 * 
	 */
	public SlideOutAnimation() {
		direction = AndroidAnimator.DIRECTION_LEFT;
		duration = Animation.DEFAULT_DURATION;
	}

	/**
	 * The SlideOutAnimation causes the view to slide out to the left, right,
	 * top or bottom of the screen depending on the parameters provided by the
	 * user.
	 * 
	 * @param direction
	 *            the direction to slide out to
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public SlideOutAnimation(int direction, long duration, AnimationListener listener) {
		this.direction = direction;
		this.duration = duration;
		this.listener = listener;
	}
	
	@Override
	public void animate(final View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		
		final int[] locationView = new int[2];
		view.getLocationOnScreen(locationView);
		
		switch (direction) {
		case AndroidAnimator.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, -locationView[0] - view.getWidth());
			break;
		case AndroidAnimator.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight());
			break;
		case AndroidAnimator.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, -locationView[1] - view.getHeight());
			break;
		case AndroidAnimator.DIRECTION_DOWN:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom());
			break;
		default:
			break;
		}
		slideAnim.setDuration(duration);
		slideAnim.start();
		slideAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(SlideOutAnimation.this);
				}
			}
		});
	}
	
}
