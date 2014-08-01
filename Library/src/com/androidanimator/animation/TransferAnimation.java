package com.androidanimator.animation;

import com.androidanimator.animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author SiYao
 *
 */
public class TransferAnimation extends Animation {

	View destinationView;
	int transX, transY;
	long duration;
	AnimationListener listener;
	ViewGroup parentView;
	
	/**
	 * The TransferAnimation transfers the view to another view provided by the
	 * user through scaling and translation. The view is scaled to the same size
	 * and is translated to the same position as the destination view.
	 * 
	 * @param destinationView
	 *            the view to be transferred to
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public TransferAnimation(View view) {
		this.view = view;
		destinationView = null;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		parentView = (ViewGroup) view.getParent();
		final ViewGroup rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		final float scaleX = (float) destinationView.getWidth()
				/ ((float) view.getWidth()), scaleY = (float) destinationView
				.getHeight() / ((float) view.getHeight());
		int[] locationDest = new int[2], locationView = new int[2];
		view.getLocationOnScreen(locationView);
		destinationView.getLocationOnScreen(locationDest);
		transX = locationDest[0] - locationView[0];
		transY = locationDest[1] - locationView[1];
		transX = transX - view.getWidth() / 2 + destinationView.getWidth() / 2;
		transY = transY - view.getHeight() / 2 + destinationView.getHeight()
				/ 2;

		view.animate().scaleX(scaleX).scaleY(scaleY).translationX(transX)
				.translationY(transY).setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator animation) {
						if (getListener() != null) {
							getListener()
									.onAnimationEnd(TransferAnimation.this);
						}
					}
				});
	}

	/**
	 * @return the destinationView
	 */
	public View getDestinationView() {
		return destinationView;
	}

	/**
	 * @param destinationView the destinationView to set
	 */
	public TransferAnimation setDestinationView(View destinationView) {
		this.destinationView = destinationView;
		return this;
	}

	/**
	 * @return the transX
	 */
	public int getTransX() {
		return transX;
	}

	/**
	 * @param transX the transX to set
	 */
	public TransferAnimation setTransX(int transX) {
		this.transX = transX;
		return this;
	}

	/**
	 * @return the transY
	 */
	public int getTransY() {
		return transY;
	}

	/**
	 * @param transY the transY to set
	 */
	public TransferAnimation setTransY(int transY) {
		this.transY = transY;
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
	public TransferAnimation setDuration(long duration) {
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
	public TransferAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}
}
