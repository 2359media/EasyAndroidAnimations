package com.androidanimator.animation;

import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 
 * @author SiYao
 * 
 */
public class BlinkAnimation extends Animation {

	int repetitions, blinkCount = 0;
	long duration;
	AnimationListener listener;

	/**
	 * The BlinkAnimation causes the view to blink a number of times to mimic a
	 * blinking animation.
	 * 
	 * @param bounces
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public BlinkAnimation(View view) {
		this.view = view;
		repetitions = 2;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		long singleBlinkDuration = duration / repetitions / 2;
		if (singleBlinkDuration == 0)
			singleBlinkDuration = 1;
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, View.ALPHA, 0), fadeIn = ObjectAnimator
				.ofFloat(view, View.ALPHA, 1);
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playSequentially(fadeOut, fadeIn);
		animatorSet.setDuration(singleBlinkDuration);
		animatorSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				blinkCount++;
				if (blinkCount != repetitions) {
					animation.start();
					if (blinkCount == repetitions - 1) {
						animation.addListener(new AnimatorListenerAdapter() {

							@Override
							public void onAnimationEnd(Animator animation) {
								if (getListener() != null) {
									getListener().onAnimationEnd(
											BlinkAnimation.this);
								}
							}
						});
					}
				}
			}
		});
		animatorSet.start();
	}

	/**
	 * @return the repetitions
	 */
	public int getRepetitions() {
		return repetitions;
	}

	/**
	 * @param repetitions
	 *            the repetitions to set
	 */
	public BlinkAnimation setRepetitions(int repetitions) {
		this.repetitions = repetitions;
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
	public BlinkAnimation setDuration(long duration) {
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
	public BlinkAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
