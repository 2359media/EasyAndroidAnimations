package com.androidanimator.animation;

import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/**
 * 
 * @author SiYao
 * 
 */
public class ShakeAnimation extends Animation {

	float shakeDistance;
	int numOfShakes, shakeCount = 0;
	long duration;
	AnimationListener listener;

	/**
	 * The ShakeAnimation causes the view to shake from left to right for a
	 * number of times before returning to its original position.
	 * 
	 * @param shakeDistance
	 *            the maximum distance of the shake
	 * @param bounces
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public ShakeAnimation(View view) {
		this.view = view;
		shakeDistance = 20;
		numOfShakes = 2;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		long singleShakeDuration = duration / numOfShakes / 2;
		if (singleShakeDuration == 0)
			singleShakeDuration = 1;
		final AnimatorSet shakeAnim = new AnimatorSet();
		shakeAnim
				.playSequentially(ObjectAnimator.ofFloat(view,
						View.TRANSLATION_X, shakeDistance), ObjectAnimator
						.ofFloat(view, View.TRANSLATION_X, -shakeDistance),
						ObjectAnimator.ofFloat(view, View.TRANSLATION_X,
								shakeDistance), ObjectAnimator.ofFloat(view,
								View.TRANSLATION_X, 0));
		shakeAnim.setInterpolator(new LinearInterpolator());
		shakeAnim.setDuration(singleShakeDuration);

		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		shakeAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				shakeCount++;
				if (shakeCount == numOfShakes) {
					if (getListener() != null) {
						getListener().onAnimationEnd(ShakeAnimation.this);
					}
				} else {
					shakeAnim.start();
				}
			}
		});
		shakeAnim.start();
	}

	/**
	 * @return the shakeDistance
	 */
	public float getShakeDistance() {
		return shakeDistance;
	}

	/**
	 * @param shakeDistance
	 *            the shakeDistance to set
	 */
	public ShakeAnimation setShakeDistance(float shakeDistance) {
		this.shakeDistance = shakeDistance;
		return this;
	}

	/**
	 * @return the numOfShakes
	 */
	public int getNumOfShakes() {
		return numOfShakes;
	}

	/**
	 * @param numOfShakes
	 *            the numOfShakes to set
	 */
	public ShakeAnimation setNumOfShakes(int numOfShakes) {
		this.numOfShakes = numOfShakes;
		return this;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public ShakeAnimation setDuration(long duration) {
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
	 * @param listener
	 *            the listener to set
	 */
	public ShakeAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
