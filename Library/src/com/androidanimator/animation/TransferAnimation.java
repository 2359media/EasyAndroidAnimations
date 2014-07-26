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
public class TransferAnimation extends Animation {

	View destinationView;
	ViewGroup parentView;
	int transX, transY;
	
	/**
	 * The TransferAnimation transfers the view to another view provided by the
	 * user through scaling and translation. The view is scaled to the same size
	 * and is translated to the same position as the destination view.
	 * 
	 * @param destinationView
	 *            the view to be transferred to
	 * @param duration
	 *            the duration of the entire animation
	 */
	public TransferAnimation(View destinationView, long duration) {
		this.destinationView = destinationView;
		this.duration = duration;
	}

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
	public TransferAnimation(View destinationView, long duration,
			AnimationListener listener) {
		this.destinationView = destinationView;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
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
}
