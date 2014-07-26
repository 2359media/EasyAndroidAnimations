package com.androidanimator.animation;

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
	ObjectAnimator slideAnim;

	/**
	 * The SlideInAnimation causes the view to slide in from the left, right,
	 * top or bottom of the screen depending on the parameters provided by the
	 * user.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public SlideInAnimation() {
		direction = Constant.DIRECTION_LEFT;
		duration = Constant.DEFAULT_DURATION;
	}

	/**
	 * The SlideInAnimation causes the view to slide in from the left, right,
	 * top or bottom of the screen depending on the parameters provided by the
	 * user.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param direction
	 *            the direction to slide in from
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public SlideInAnimation(int direction, long duration,
			AnimationListener listener) {
		this.direction = direction;
		this.duration = duration;
		this.listener = listener;
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

		switch (direction) {
		case Constant.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, -locationView[0]
					- view.getWidth(), view.getX());
			break;
		case Constant.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X,
					rootView.getRight(), view.getX());
			break;
		case Constant.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, -locationView[1]
					- view.getHeight(), view.getY());
			break;
		case Constant.DIRECTION_DOWN:
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

}
