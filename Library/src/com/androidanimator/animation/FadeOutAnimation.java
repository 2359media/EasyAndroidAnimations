package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This animation fades the view out by animating its alpha property to 0.
 * 
 * @author SiYao
 * 
 */
public class FadeOutAnimation extends Animation implements Combinable {

	TimeInterpolator interpolator;
	long duration;
	AnimationListener listener;

	/**
	 * This animation fades the view out by animating its alpha property to 0.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public FadeOutAnimation(View view) {
		this.view = view;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.animate().alpha(0f).setInterpolator(interpolator)
				.setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener().onAnimationEnd(FadeOutAnimation.this);
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
	public FadeOutAnimation setInterpolator(TimeInterpolator interpolator) {
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
	 */
	public FadeOutAnimation setDuration(long duration) {
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
	 */
	public FadeOutAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
