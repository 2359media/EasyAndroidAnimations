package com.androidanimator.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * The SlideInAnimation1 causes the view to slide in from the left, right, top
 * or bottom depending on the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class SlideInAnimation1 extends Animation {

	String direction;
	ObjectAnimator slideAnim;

	public SlideInAnimation1() {
		direction = "LEFT";
		duration = 500;
	}

	public SlideInAnimation1(String direction, long duration) {
		this.direction = direction;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		switch (direction) {
		case "LEFT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getLeft() - view.getRight(), view.getX());
			break;
		case "RIGHT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight(), view.getX());
			break;
		case "TOP":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getTop() - view.getHeight(), view.getY());
			break;
		case "BOTTOM":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom(), view.getY());
			break;
		default:
			break;
		}
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(slideAnim, ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
		animatorSet.setDuration(duration);
		animatorSet.start();
	}

}
