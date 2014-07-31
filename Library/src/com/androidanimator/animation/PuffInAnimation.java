package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class PuffInAnimation extends Animation {

	long duration;
	AnimationListener listener;

	public PuffInAnimation(View view) {
		this.view = view;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.setScaleX(4f);
		view.setScaleY(4f);
		view.setAlpha(0f);
		view.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						view.setVisibility(View.VISIBLE);
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener().onAnimationEnd(PuffInAnimation.this);
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
	public PuffInAnimation setDuration(long duration) {
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
	public PuffInAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
