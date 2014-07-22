package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * The PulsateAnimation1 causes the view to blink a number of times to mimic a
 * pulsating animation.
 * 
 * @author SiYao
 * 
 */
public class PulsateAnimation1 extends Animation {

	int repetitions, blinkCount = 0;

	public PulsateAnimation1() {
		repetitions = 2;
		duration = 300;
	}

	public PulsateAnimation1(int repetitions) {
		this.repetitions = repetitions;
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
				if (blinkCount != repetitions)
					animation.start();
			}
		});
	}

}
