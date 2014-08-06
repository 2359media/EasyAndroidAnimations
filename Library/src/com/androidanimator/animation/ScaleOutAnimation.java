package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This animation scales out the view from 1 to 0.
 * 
 * @author SiYao
 * 
 */
public class ScaleOutAnimation extends Animation implements Combinable {

	TimeInterpolator interpolator;
	long duration;
	AnimationListener listener;

	/**
	 * This animation scales out the view from 1 to 0.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public ScaleOutAnimation(View view) {
		this.view = view;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.setScaleX(1f);
		view.setScaleY(1f);
		view.animate().scaleX(0f).scaleY(0f).setInterpolator(interpolator)
				.setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener()
									.onAnimationEnd(ScaleOutAnimation.this);
						}
					}
				});
	}

	/**
	 * @return The interpolator of the entire animation.
	 */
	public TimeInterpolator getInterpolator() {
		return interpolator;
	}

	/**
	 * @param interpolator
	 *            The interpolator of the entire animation to set.
	 */
	public ScaleOutAnimation setInterpolator(TimeInterpolator interpolator) {
		this.interpolator = interpolator;
		return this;
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
	public ScaleOutAnimation setDuration(long duration) {
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
	public ScaleOutAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
