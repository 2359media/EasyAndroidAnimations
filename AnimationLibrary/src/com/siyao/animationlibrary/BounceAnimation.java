package com.siyao.animationlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

public class BounceAnimation extends Animation {

	String direction;
	float bounceDistance;
	int repetitions, bounceCount = 0;
	long duration;
	
	public BounceAnimation() {
		direction = "Y";
		bounceDistance = 50;
		repetitions = 1;
		duration = 100;
	}
	
	public BounceAnimation(String direction, float bounce, int repetitions, long duration) {
		this.direction = direction.toUpperCase();
		this.bounceDistance = bounce;
		this.repetitions = repetitions;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		duration /= repetitions;
		AnimatorSet bounceAnim = new AnimatorSet(), bounceAnim1 = new AnimatorSet(), bounceAnim2 = new AnimatorSet();
		bounceAnim1.playSequentially(ObjectAnimator.ofFloat(view, "translation" + direction, bounceDistance), ObjectAnimator.ofFloat(view, "translation" + direction, -bounceDistance));
		bounceAnim2.playSequentially(ObjectAnimator.ofFloat(view, "translation" + direction, bounceDistance), ObjectAnimator.ofFloat(view, "translation" + direction, 0));
		bounceAnim.playSequentially(bounceAnim1, bounceAnim2);
		bounceAnim.setDuration(duration);
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
				if (bounceCount != repetitions)
					animation.start();
			}
		});
	}

}
