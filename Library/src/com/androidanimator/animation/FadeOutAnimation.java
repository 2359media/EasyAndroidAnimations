package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class FadeOutAnimation extends Animation {

	long duration;
	AnimationListener listener;

	public FadeOutAnimation() {
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(View view) {
		view.animate().alpha(0f).setDuration(duration)
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
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public FadeOutAnimation setDuration(long duration) {
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
	 * @param listener
	 *            the listener to set
	 */
	public FadeOutAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}