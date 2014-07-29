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

	/**
	 * The RotationAnimation rotates the view depending on the parameters
	 * provided by the user.
	 * 
	 */
	public RotationAnimation() {
		degrees = 360;
		pivotPosition = PIVOT_CENTER;
		duration = Animation.DEFAULT_DURATION;
	}
	
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
	public RotationAnimation(float degrees, int pivotPosition, long duration, AnimationListener listener) {
		this.degrees = degrees;
		this.pivotPosition = pivotPosition;
		this.duration = duration;
		this.listener = listener;
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

}
