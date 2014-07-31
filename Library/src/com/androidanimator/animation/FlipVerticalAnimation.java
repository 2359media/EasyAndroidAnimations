package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class FlipVerticalAnimation extends Animation {

	public static final int PIVOT_CENTER = 0, PIVOT_TOP = 1, PIVOT_BOTTOM = 2;

	float degrees;
	int pivot;
	long duration;
	AnimationListener listener;

	public FlipVerticalAnimation(View view) {
		this.view = view;
		degrees = 360;
		pivot = PIVOT_CENTER;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		while (parentView != rootView) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		float pivotX, pivotY, viewWidth = view.getWidth(), viewHeight = view
				.getHeight();
		switch (pivot) {
		case PIVOT_TOP:
			pivotX = viewWidth / 2;
			pivotY = 0f;
			break;
		case PIVOT_BOTTOM:
			pivotX = viewWidth / 2;
			pivotY = viewHeight;
			break;
		default:
			pivotX = viewWidth / 2;
			pivotY = viewHeight / 2;
			break;
		}
		view.setPivotX(pivotX);
		view.setPivotY(pivotY);

		view.animate().rotationXBy(degrees).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener().onAnimationEnd(
									FlipVerticalAnimation.this);
						}
					}
				});
	}

	/**
	 * @return the degrees
	 */
	public float getDegrees() {
		return degrees;
	}

	/**
	 * @param degrees the degrees to set
	 */
	public FlipVerticalAnimation setDegrees(float degrees) {
		this.degrees = degrees;
		return this;
	}

	/**
	 * @return the pivot
	 */
	public int getPivot() {
		return pivot;
	}

	/**
	 * @param pivot the pivot to set
	 */
	public FlipVerticalAnimation setPivot(int pivot) {
		this.pivot = pivot;
		return this;
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
	public FlipVerticalAnimation setDuration(long duration) {
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
	public FlipVerticalAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
