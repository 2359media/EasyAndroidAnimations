package com.siyao.animationlibrary;

import android.view.View;
import android.view.ViewGroup;

public class ScaleAnimation extends Animation {
	
	int x, y;
	long duration;
	ViewGroup parentView;
	
	public ScaleAnimation() {
		x = 0;
		y = 0;
		duration = 500;
	}
	
	public ScaleAnimation(int x, int y, long duration) {
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
