package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * The ScaleAnimation1 scales the view either upwards or downwards depending on
 * the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class ScaleAnimation1 extends Animation {

	float x, y;

	public ScaleAnimation1() {
		x = 0;
		y = 0;
		duration = Constant.DEFAULT_DURATION;
	}

	public ScaleAnimation1(float x, float y, long duration, AnimationListener listener) {
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		if (x < 1 && y < 1)
			view.animate().scaleX(x).scaleY(y).setDuration(duration);
		else {
			view.animate().scaleX(x).scaleY(y).setDuration(duration);
			ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
			while (!parentView.equals(rootView)) {
				parentView.setClipChildren(false);
				parentView = (ViewGroup) parentView.getParent();
			}
			rootView.setClipChildren(false);
		}
		view.animate().setListener(new AnimatorListenerAdapter() {
		
			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(ScaleAnimation1.this);
				}
				view.animate().scaleX(1).scaleY(1);
			}
		});
	}

}
