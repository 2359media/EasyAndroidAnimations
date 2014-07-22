package com.androidanimator.animation;

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

	int direction;
	ObjectAnimator slideAnim;

	public SlideInAnimation1() {
		direction = Constant.DIRECTION_UP;
		duration = Constant.DEFAULT_DURATION;
	}

	public SlideInAnimation1(int direction, long duration) {
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
		case Constant.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getLeft() - view.getWidth(), view.getX());
			break;
		case Constant.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight(), view.getX());
			break;
		case Constant.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getTop() - view.getHeight(), view.getY());
			break;
		case Constant.DIRECTION_DOWN:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom(), view.getY());
			break;
		default:
			break;
		}
		view.setVisibility(View.VISIBLE);
		slideAnim.setDuration(duration);
		slideAnim.start();
	}

}
