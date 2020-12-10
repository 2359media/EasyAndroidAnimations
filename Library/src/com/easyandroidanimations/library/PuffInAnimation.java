package com.easyandroidanimations.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This animation scales down and fades in the view.
 * 
 * @author Phu
 * 
 */
public class PuffInAnimation extends Animation {

	TimeInterpolator interpolator;
	long duration;
	AnimationListener listener;
	float enlargeFactor;

	/**
	 * This animation scales down and fades in the view.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public PuffInAnimation(View view) {
		this.view = view;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = DURATION_LONG;
		listener = null;
		this.enlargeFactor = 4f;
	}

	@Override
	public void animate() {
		ViewGroup parentView = (ViewGroup) view.getParent();
		ViewGroup rootView = (ViewGroup) view.getRootView();
		while (parentView != rootView) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		view.setScaleX(enlargeFactor);
		view.setScaleY(enlargeFactor);
		view.setAlpha(0f);
		view.animate().scaleX(1f).scaleY(1f).alpha(1f)
				.setInterpolator(interpolator).setDuration(duration)
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
	 * @return The interpolator of the entire animation.
	 */
	public TimeInterpolator getInterpolator() {
		return interpolator;
	}

	/**
	 * @param interpolator
	 *            The interpolator of the entire animation to set.
	 */
	public PuffInAnimation setInterpolator(TimeInterpolator interpolator) {
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
	public PuffInAnimation setDuration(long duration) {
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
	public PuffInAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

	/**
	 *
	 * @param factor
	 * The factor of the view to be enlarged at the beginning of the animation.
	 * @return This object, allowing calls to methods in this class to be chained.
	 */
	public PuffInAnimation setEnlargeFactor(double factor) {
		this.enlargeFactor = (float) factor;
		return this;
	}

	/**
	 * @return The enlarging factor at the beginning of the animation.
	 */
	public float getEnlargeFactor() {
		return enlargeFactor;
	}

}
