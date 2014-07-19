package com.siyao.animationlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

public class ShakeAnimation extends Animation {
	
	float shakeDistance;
	int repetitions, shakeCount = 0;
	long duration;
	
	public ShakeAnimation() {
		shakeDistance = 50;
		repetitions = 1;
		duration = 100;
	}
	
	public ShakeAnimation(float shakeDistance, int repetitions, long duration) {
		this.shakeDistance = shakeDistance;
		this.repetitions = repetitions;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		duration /= repetitions;
		AnimatorSet shakeAnim = new AnimatorSet(), shakeAnim1 = new AnimatorSet(), shakeAnim2 = new AnimatorSet();
		shakeAnim1.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, shakeDistance), ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -shakeDistance));
		shakeAnim2.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, shakeDistance), ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0));
		shakeAnim.playSequentially(shakeAnim1, shakeAnim2);
		shakeAnim.setDuration(duration);
		shakeAnim.start();
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		shakeAnim.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator animation) {			
				shakeCount++;
				if (shakeCount != repetitions)
					animation.start();
			}
		});
	}

}
