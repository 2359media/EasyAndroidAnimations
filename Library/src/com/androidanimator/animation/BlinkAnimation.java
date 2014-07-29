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

	/**
	 * The BlindAnimation makes use of a box that is of the same size as the
	 * view to translate upwards to mimic the blind animation.
	 * 
	 */
	public BlinkAnimation() {
		repetitions = 2;
		duration = Animation.DEFAULT_DURATION;
	}

	/**
	 * The BlinkAnimation causes the view to blink a number of times to mimic a
	 * blinking animation.
	 * 
	 * @param repetitions
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public BlinkAnimation(int repetitions, long duration, AnimationListener listener) {
		this.repetitions = repetitions;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		long singleBlinkDuration = duration / repetitions;
		if (singleBlinkDuration == 0)
			singleBlinkDuration = 1;
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, View.ALPHA, 0), fadeIn = ObjectAnimator
				.ofFloat(view, View.ALPHA, 1);
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playSequentially(fadeOut, fadeIn);
		animatorSet.setDuration(singleBlinkDuration);
		animatorSet.start();
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
									getListener().onAnimationEnd(BlinkAnimation.this);
								}
							}
						});
					}
				}
			}
		});
	}

}
