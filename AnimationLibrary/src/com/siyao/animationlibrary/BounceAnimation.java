package com.siyao.animationlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

public class BounceAnimation extends Animation {

	String direction;
	float bounce;
	long duration;
	
	public BounceAnimation() {
		direction = "Y";
		bounce = 20;
		duration = 80;
	}
	
	public BounceAnimation(String direction, float bounce, long duration) {
		this.direction = direction.toUpperCase();
		this.bounce = bounce;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		AnimatorSet bounceAnim = new AnimatorSet(), bounceAnim1 = new AnimatorSet(), bounceAnim2 = new AnimatorSet();
		bounceAnim1.playSequentially(ObjectAnimator.ofFloat(view, "translation" + direction, bounce), ObjectAnimator.ofFloat(view, "translation" + direction, -bounce));
		bounceAnim2.playSequentially(ObjectAnimator.ofFloat(view, "translation" + direction, bounce), ObjectAnimator.ofFloat(view, "translation" + direction, 0));
		bounceAnim.playSequentially(bounceAnim1, bounceAnim2);
		bounceAnim.setDuration(duration);
		bounceAnim.start();
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
	}

}
