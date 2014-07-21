package com.siyao.animationlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideInAnimation extends Animation {
	
	String direction;
	long duration;
	ObjectAnimator slideAnim;
	
	public SlideInAnimation() {
		direction = "LEFT";
		duration = 500;
	}
	
	public SlideInAnimation(String direction, long duration) {
		this.direction = direction;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		switch (direction) {
		case "LEFT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X, view.getX() - view.getWidth(), view.getX());
			break;
		case "RIGHT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X, view.getX() + view.getWidth(), view.getX());
			break;
		case "TOP":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, view.getY() - view.getHeight(), view.getY());
			break;
		case "BOTTOM":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, view.getY() + view.getHeight(), view.getY());
			break;
		default:
			break;
		}
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(slideAnim, ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
		animatorSet.start();
	}

}
