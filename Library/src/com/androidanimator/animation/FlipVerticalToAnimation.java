package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class FlipVerticalToAnimation extends Animation {

	public static final int PIVOT_CENTER = 0, PIVOT_TOP = 1, PIVOT_BOTTOM = 2,
			FLIP_UP = 0, FLIP_DOWN = 1;

	View flipToView;
	int pivotPosition, direction;
	long duration;
	AnimationListener listener;

	public FlipVerticalToAnimation(View view) {
		this.view = view;
		flipToView = null;
		pivotPosition = PIVOT_CENTER;
		direction = FLIP_UP;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();

		float pivotX, pivotY, flipAngle = 270f, viewWidth = view.getWidth(), viewHeight = view
				.getHeight();
		switch (pivotPosition) {
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
		if (direction == FLIP_UP) {
			flipToView.setRotationX(270f);
			flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
					View.ROTATION_X, 0f, flipAngle), ObjectAnimator.ofFloat(
					flipToView, View.ROTATION_X, 270f, 360f));
		} else {
			flipToView.setRotationX(-270f);
			flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
					View.ROTATION_X, 0f, -flipAngle), ObjectAnimator.ofFloat(
					flipToView, View.ROTATION_X, -270f, -360f));
		}
		flipToAnim.setInterpolator(new LinearInterpolator());
		flipToAnim.setDuration(duration);
		flipToAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(FlipVerticalToAnimation.this);
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
	public FlipVerticalToAnimation setFlipToView(View flipToView) {
		this.flipToView = flipToView;
		return this;
	}

	/**
	 * @return the pivotPosition
	 */
	public int getPivotPosition() {
		return pivotPosition;
	}

	/**
	 * @param pivotPosition the pivotPosition to set
	 */
	public FlipVerticalToAnimation setPivotPosition(int pivotPosition) {
		this.pivotPosition = pivotPosition;
		return this;
	}

	/**
	 * @return the flipDirection
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param flipDirection the flipDirection to set
	 */
	public FlipVerticalToAnimation setDirection(int direction) {
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
	public FlipVerticalToAnimation setDuration(long duration) {
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
	public FlipVerticalToAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
