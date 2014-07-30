package com.androidanimator.animation;

import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author SiYao
 *
 */
public class RotationAnimation extends Animation {

	public static final int PIVOT_CENTER = 0, PIVOT_TOP_LEFT = 1,
			PIVOT_TOP_RIGHT = 2, PIVOT_BOTTOM_LEFT = 3, PIVOT_BOTTOM_RIGHT = 4;

	float degrees;
	int pivotPosition;
	long duration;
	AnimationListener listener;

	/**
	 * The RotationAnimation rotates the view depending on the parameters
	 * provided by the user.
	 * 
	 * @param degrees
	 *            the number of degrees to rotate the view by
	 * @param pivotPosition
	 *            the pivot position around which the view is rotated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public RotationAnimation() {
		degrees = 360;
		pivotPosition = PIVOT_CENTER;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}
	
	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		while (parentView != rootView) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		float pivotX, pivotY, viewWidth = view.getWidth(), viewHeight = view.getHeight();
		switch (pivotPosition) {
		case PIVOT_TOP_LEFT:
			pivotX = 1f;
			pivotY = 1f;
			break;
		case PIVOT_TOP_RIGHT:
			pivotX = viewWidth;
			pivotY = 1f;
			break;
		case PIVOT_BOTTOM_LEFT:
			pivotX = 1f;
			pivotY = viewHeight;
			break;
		case PIVOT_BOTTOM_RIGHT:
			pivotX = viewWidth;
			pivotY = viewHeight;
			break;
		default:
			pivotX = 0f;
			pivotY = 0f;
			break;
		}
		view.setPivotX(pivotX);
		view.setPivotY(pivotY);
		view.animate().rotationBy(degrees).setDuration(duration).setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(RotationAnimation.this);
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
	public RotationAnimation setDegrees(float degrees) {
		this.degrees = degrees;
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
	public RotationAnimation setPivotPosition(int pivotPosition) {
		this.pivotPosition = pivotPosition;
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
	public RotationAnimation setDuration(long duration) {
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
	public RotationAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
