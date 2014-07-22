package com.androidanimator.animation;

import android.view.View;
import android.view.ViewGroup;

/**
 * The ScaleAnimation1 scales the view either upwards or downwards depending on
 * the parameters provided by the user before fading out of view.
 * 
 * @author SiYao
 * 
 */
public class ScaleAnimation1 extends Animation {

	int x, y;
	ViewGroup parentView;

	public ScaleAnimation1() {
		x = 0;
		y = 0;
		duration = Constant.DEFAULT_DURATION;
	}

	public ScaleAnimation1(int x, int y, long duration) {
		this.x = x;
		this.y = y;
		this.duration = duration;
	}

	@Override
	public void animate(final View view) {
		parentView = (ViewGroup) view.getParent();
		if (x == 0 && y == 0)
			view.animate().scaleX(x).scaleY(y).setDuration(duration);
		else {
			view.animate().scaleX(x).scaleY(y).alpha(0).setDuration(duration);
			ViewGroup rootView = (ViewGroup) view.getRootView();
			while (!parentView.equals(rootView)) {
				parentView.setClipChildren(false);
				parentView = (ViewGroup) parentView.getParent();
			}
			rootView.setClipChildren(false);
		}
		view.animate().withEndAction(new Runnable() {

			@Override
			public void run() {
				view.animate().scaleX(1).scaleY(1).alpha(1);
			}
		});
	}

}
