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
public class BounceAnimation extends Animation {

	float bounceDistance;
	int bounces, bounceCount = 0;
	long duration;
	AnimationListener listener;

	/**
	 * The BounceAnimation causes the view to bounce by translating up and down
	 * for a number of times before returning to its original position.
	 * 
	 * @param bounceDistance
	 *            the maximum distance of the bounce
	 * @param bounces
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public BounceAnimation() {
		bounceDistance = 20;
		bounces = 2;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(View view) {
		long singleBounceDuration = duration / bounces;
		if (singleBounceDuration == 0)
			singleBounceDuration = 1;
		AnimatorSet bounceAnim = new AnimatorSet(), bounceAnim1 = new AnimatorSet(), bounceAnim2 = new AnimatorSet();
		bounceAnim1.playSequentially(ObjectAnimator.ofFloat(view,
				View.TRANSLATION_Y, bounceDistance), ObjectAnimator.ofFloat(
				view, View.TRANSLATION_Y, -bounceDistance));
		bounceAnim2.playSequentially(ObjectAnimator.ofFloat(view,
				View.TRANSLATION_Y, bounceDistance), ObjectAnimator.ofFloat(
				view, View.TRANSLATION_Y, 0));
		bounceAnim.playSequentially(bounceAnim1, bounceAnim2);
		bounceAnim.setInterpolator(new LinearInterpolator());
		bounceAnim.setDuration(singleBounceDuration);
		bounceAnim.start();
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		bounceAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				bounceCount++;
				if (bounceCount == bounces) {
					if (getListener() != null) {
						getListener().onAnimationEnd(BounceAnimation.this);
					}
				}
				else {
					animation.start();
				}
			}
		});
	}

	/**
	 * @return the bounceDistance
	 */
	public float getBounceDistance() {
		return bounceDistance;
	}

	/**
	 * @param bounceDistance
	 *            the bounceDistance to set
	 */
	public BounceAnimation setBounceDistance(float bounceDistance) {
		this.bounceDistance = bounceDistance;
		return this;
	}

	/**
	 * @return the repetitions
	 */
	public int getRepetitions() {
		return bounces;
	}

	/**
	 * @param repetitions
	 *            the repetitions to set
	 */
	public BounceAnimation setRepetitions(int repetitions) {
		this.bounces = repetitions;
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
	public BounceAnimation setDuration(long duration) {
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
	public BounceAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
