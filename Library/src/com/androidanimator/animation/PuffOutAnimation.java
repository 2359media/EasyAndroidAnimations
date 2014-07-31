package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class PuffOutAnimation extends Animation {

	long duration;
	AnimationListener listener;

	public PuffOutAnimation(View view) {
		this.view = view;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener().onAnimationEnd(PuffOutAnimation.this);
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
	public PuffOutAnimation setDuration(long duration) {
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
	public PuffOutAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
