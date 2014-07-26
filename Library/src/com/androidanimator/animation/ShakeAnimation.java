package com.androidanimator.animation;

import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author SiYao
 * 
 */
public class ShakeAnimation extends Animation {

	float shakeDistance;
	int repetitions, shakeCount = 0;

	/**
	 * The ShakeAnimation causes the view to shake from left to right for a
	 * number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 */
	public ShakeAnimation() {
		shakeDistance = 50;
		repetitions = 1;
		duration = Constant.DEFAULT_DURATION;
	}

	/**
	 * The ShakeAnimation causes the view to shake from left to right for a
	 * number of times before returning to its original position.
	 * 
	 * @param view
	 *            the view to be animated
	 * @param shakeDistance
	 *            the maximum distance of the shake
	 * @param repetitions
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public ShakeAnimation(float shakeDistance, int repetitions, long duration, AnimationListener listener) {
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
									getListener().onAnimationEnd(ShakeAnimation.this);
								}
							}
						});
					}
				}
			}
		});
	}

}
