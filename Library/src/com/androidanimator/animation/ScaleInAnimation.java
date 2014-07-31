package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ScaleInAnimation extends Animation {
	
	long duration;
	AnimationListener listener;
	
	public ScaleInAnimation() {
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(View view) {
		view.setScaleX(0f);
		view.setScaleY(0f);
		view.setVisibility(View.VISIBLE);
		view.animate().scaleX(1f).scaleY(1f).setDuration(duration).setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(ScaleInAnimation.this);
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
	 * @param duration the duration to set
	 */
	public ScaleInAnimation setDuration(long duration) {
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
	public ScaleInAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
