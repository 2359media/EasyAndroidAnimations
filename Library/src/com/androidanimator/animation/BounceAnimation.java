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
public class BounceAnimation extends Animation {

	float bounceDistance;
	int repetitions, bounceCount = 0;
	
	/**
	 * The BounceAnimation causes the view to bounce by translating up and down
	 * for a number of times before returning to its original position.
	 * 
	 */
	public BounceAnimation() {
		bounceDistance = 50;
		repetitions = 1;
		duration = Animation.DEFAULT_DURATION;
	}
	
	/**
	 * The BounceAnimation causes the view to bounce by translating up and down
	 * for a number of times before returning to its original position.
	 * 
	 * @param bounceDistance
	 *            the maximum distance of the bounce
	 * @param repetitions
	 *            the number of times the animation is repeated
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public BounceAnimation(float bounceDistance, int repetitions, long duration, AnimationListener listener) {
		this.bounceDistance = bounceDistance;
		this.repetitions = repetitions;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(View view) {
		long singleBounceDuration = duration / repetitions;
		if (singleBounceDuration == 0)
			singleBounceDuration = 1;
		AnimatorSet bounceAnim = new AnimatorSet(), bounceAnim1 = new AnimatorSet(), bounceAnim2 = new AnimatorSet();
		bounceAnim1.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, bounceDistance), ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -bounceDistance));
		bounceAnim2.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, bounceDistance), ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0));
		bounceAnim.playSequentially(bounceAnim1, bounceAnim2);
		bounceAnim.setDuration(singleBounceDuration);
		bounceAnim.start();
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		bounceAnim.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator animation) {
				bounceCount++;
				if (bounceCount != repetitions) {
					animation.start();
					if (bounceCount == repetitions - 1) {
						animation.addListener(new AnimatorListenerAdapter() {

							@Override
							public void onAnimationEnd(Animator animation) {
								if (getListener() != null) {
									getListener().onAnimationEnd(BounceAnimation.this);
								}
							}
						});
					}
				}
			}
		});
	}

}
