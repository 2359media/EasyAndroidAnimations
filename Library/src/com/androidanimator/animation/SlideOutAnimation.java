package com.androidanimator.animation;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * The SlideOutAnimation1 causes the view to slide out to the left, right, top or
 * bottom depending on the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class SlideOutAnimation extends Animation {

	int direction;
	ObjectAnimator slideAnim;
	
	public SlideOutAnimation() {
		direction = Constant.DIRECTION_LEFT;
		duration = Constant.DEFAULT_DURATION;
	}

	public SlideOutAnimation(int direction, long duration, AnimationListener listener) {
		this.direction = direction;
		this.duration = duration;
		this.listener = listener;
	}
	
	@Override
	public void animate(final View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		
		final int[] locationView = new int[2];
		view.getLocationOnScreen(locationView);
		
		switch (direction) {
		case Constant.DIRECTION_LEFT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, -locationView[0] - view.getWidth());
			break;
		case Constant.DIRECTION_RIGHT:
			slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight());
			break;
		case Constant.DIRECTION_UP:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, -locationView[1] - view.getHeight());
			break;
		case Constant.DIRECTION_DOWN:
			slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom());
			break;
		default:
			break;
		}
		slideAnim.setDuration(duration);
		slideAnim.start();
		slideAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(SlideOutAnimation.this);
				}
			}
		});
	}
	
}
