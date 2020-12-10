package com.easyandroidanimations.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This animation scales up and fades out the view. On animation end, the view
 * is restored to its original state and is set to <code>View.INVISIBLE</code>.
 * 
 * @author Phu
 * 
 */
public class PuffOutAnimation extends Animation {

	TimeInterpolator interpolator;
	long duration;
	AnimationListener listener;
	float enlargeFactor;

	/**
	 * This animation scales up and fades out the view. On animation end, the
	 * view is restored to its original state and is set to
	 * <code>View.INVISIBLE</code>.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public PuffOutAnimation(View view) {
		this.view = view;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = DURATION_LONG;
		listener = null;
		enlargeFactor = 4f;
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

		final float originalScaleX = view.getScaleX();
		final float originalScaleY = view.getScaleY();
		final float originalAlpha = view.getAlpha();
		view.animate().scaleX(enlargeFactor).scaleY(enlargeFactor).alpha(0f)
				.setInterpolator(interpolator).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						view.setVisibility(View.INVISIBLE);
						view.setScaleX(originalScaleX);
						view.setScaleY(originalScaleY);
						view.setAlpha(originalAlpha);
						if (getListener() != null) {
							getListener().onAnimationEnd(PuffOutAnimation.this);
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
	public PuffOutAnimation setInterpolator(TimeInterpolator interpolator) {
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
	public PuffOutAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 *
	 * @param factor
	 * The factor of the view to be enlarged at the beginning of the animation.
	 * @return This object, allowing calls to methods in this class to be chained.
	 */
	public PuffOutAnimation setEnlargeFactor(double factor) {
		this.enlargeFactor = (float) factor;
		return this;
	}

	/**
	 * @return The enlarging factor at the beginning of the animation.
	 */
	public float getEnlargeFactor() {
		return enlargeFactor;
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
	public PuffOutAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
