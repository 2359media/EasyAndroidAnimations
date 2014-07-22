package com.androidanimator.animation;


import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * The SlideOutAnimation1 causes the view to slide out to the left, right, top or
 * bottom depending on the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class SlideOutAnimation1 extends Animation {

	int direction;
	ObjectAnimator slideAnim;
	
	public SlideOutAnimation1() {
		direction = Constant.DIRECTION_LEFT;
		duration = Constant.DEFAULT_DURATION;
	}
	
	public SlideOutAnimation1(int direction, long duration) {
		this.direction = direction;
		this.duration = duration;
	}
	
	@Override
	public void animate(final View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		Log.d("top", "" + rootView.getTop());
		switch (direction) {
		case Constant.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getLeft() - view.getWidth());
			break;
		case Constant.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight());
			break;
		case Constant.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getTop() - view.getHeight());
			break;
		case Constant.DIRECTION_DOWN:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom());
			break;
		default:
			break;
		}
		slideAnim.setDuration(duration);
		slideAnim.start();
	}
	
}
