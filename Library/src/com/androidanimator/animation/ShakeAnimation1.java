package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * The ShakeAnimation1 causes the view to shake from left to right for a number
 * of times before returning to its original position.
 * 
 * @author SiYao
 * 
 */
public class ShakeAnimation1 extends Animation {

	float shakeDistance;
	int repetitions, shakeCount = 0;

	public ShakeAnimation1() {
		shakeDistance = 50;
		repetitions = 1;
		duration = Constant.DEFAULT_DURATION;
	}

	public ShakeAnimation1(float shakeDistance, int repetitions, long duration, AnimationListener listener) {
		this.shakeDistance = shakeDistance;
		this.repetitions = repetitions;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(View view) {
		duration /= repetitions;
		AnimatorSet shakeAnim = new AnimatorSet(), shakeAnim1 = new AnimatorSet(), shakeAnim2 = new AnimatorSet();
		shakeAnim1
				.playSequentially(ObjectAnimator.ofFloat(view,
						View.TRANSLATION_X, shakeDistance), ObjectAnimator
						.ofFloat(view, View.TRANSLATION_X, -shakeDistance));
		shakeAnim2
				.playSequentially(ObjectAnimator.ofFloat(view,
						View.TRANSLATION_X, shakeDistance), ObjectAnimator
						.ofFloat(view, View.TRANSLATION_X, 0));
		shakeAnim.playSequentially(shakeAnim1, shakeAnim2);
		shakeAnim.setDuration(duration);
		shakeAnim.start();
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
				if (shakeCount != repetitions) {
					animation.start();
					if (shakeCount == repetitions - 1) {
						animation.addListener(new AnimatorListenerAdapter() {

							@Override
							public void onAnimationEnd(Animator animation) {
								if (getListener() != null) {
									getListener().onAnimationEnd(ShakeAnimation1.this);
								}
							}
						});
					}
				}
			}
		});
	}

}
