package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * This animation scales in the view from 0 to 1.
 * 
 * @author SiYao
 * 
 */
public class ScaleInAnimation extends Animation implements Combinable {

	long duration;
	AnimationListener listener;

	/**
	 * This animation scales in the view from 0 to 1.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public ScaleInAnimation(View view) {
		this.view = view;
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.setScaleX(0f);
		view.setScaleY(0f);
		view.setVisibility(View.VISIBLE);
		view.animate().scaleX(1f).scaleY(1f).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener().onAnimationEnd(ScaleInAnimation.this);
						}
					}
				});
	}

	/**
	 * @return The duration of the entire animation.
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            The duration of the entire animation to set.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 */
	public ScaleInAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return The listener for the end of the animation.
	 */
	public AnimationListener getListener() {
		return listener;
	}

	/**
	 * @param listener
	 *            The listener to set for the end of the animation.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 */
	public ScaleInAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
