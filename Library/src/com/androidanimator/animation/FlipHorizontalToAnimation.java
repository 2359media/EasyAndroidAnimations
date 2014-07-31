package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class FlipHorizontalToAnimation extends Animation {

	public static final int PIVOT_CENTER = 0, PIVOT_LEFT = 1, PIVOT_RIGHT = 2,
			FLIP_LEFT = 0, FLIP_RIGHT = 1;

	View flipToView;
	int pivot, direction;
	long duration;
	AnimationListener listener;

	public FlipHorizontalToAnimation(View view) {
		this.view = view;
		flipToView = null;
		pivot = PIVOT_CENTER;
		direction = FLIP_RIGHT;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();

		float pivotX, pivotY, flipAngle = 270f, viewWidth = view.getWidth(), viewHeight = view
				.getHeight();
		switch (pivot) {
		case PIVOT_LEFT:
			pivotX = 0f;
			pivotY = viewHeight / 2;
			break;
		case PIVOT_RIGHT:
			pivotX = viewWidth;
			pivotY = viewHeight / 2;
			break;
		default:
			pivotX = viewWidth / 2;
			pivotY = viewHeight / 2;
			flipAngle = 90f;
			break;
		}
		view.setPivotX(pivotX);
		view.setPivotY(pivotY);
		flipToView.setLayoutParams(view.getLayoutParams());
		flipToView.setLeft(view.getLeft());
		flipToView.setTop(view.getTop());
		flipToView.setPivotX(pivotX);
		flipToView.setPivotY(pivotY);
		flipToView.setVisibility(View.VISIBLE);

		while (parentView != rootView) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		AnimatorSet flipToAnim = new AnimatorSet();
		if (direction == FLIP_RIGHT) {
			flipToView.setRotationY(270f);
			flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
					View.ROTATION_Y, 0f, flipAngle), ObjectAnimator.ofFloat(
					flipToView, View.ROTATION_Y, 270f, 360f));
		} else {
			flipToView.setRotationY(-270f);
			flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
					View.ROTATION_Y, 0f, -flipAngle), ObjectAnimator.ofFloat(
					flipToView, View.ROTATION_Y, -270f, -360f));
		}
		flipToAnim.setInterpolator(new LinearInterpolator());
		flipToAnim.setDuration(duration);
		flipToAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener()
							.onAnimationEnd(FlipHorizontalToAnimation.this);
				}
			}
		});
		flipToAnim.start();
	}

	/**
	 * @return the flipToView
	 */
	public View getFlipToView() {
		return flipToView;
	}

	/**
	 * @param flipToView the flipToView to set
	 */
	public FlipHorizontalToAnimation setFlipToView(View flipToView) {
		this.flipToView = flipToView;
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
	public FlipHorizontalToAnimation setPivot(int pivot) {
		this.pivot = pivot;
		return this;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public FlipHorizontalToAnimation setDirection(int direction) {
		this.direction = direction;
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
	public FlipHorizontalToAnimation setDuration(long duration) {
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
	public FlipHorizontalToAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
