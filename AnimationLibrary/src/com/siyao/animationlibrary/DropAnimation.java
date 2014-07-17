package com.siyao.animationlibrary;

import android.view.View;

public class DropAnimation extends Animation {

	String leftRight;
	long duration;
	
	public DropAnimation() {
		leftRight = "LEFT";
		duration = 500;
	}
	
	@Override
	public void animate(final View view) {
		if (leftRight == "LEFT")
			view.animate().translationXBy(-view.getWidth()).alpha(0).setDuration(duration);
		else
			view.animate().translationXBy(view.getWidth()).alpha(0).setDuration(duration);
		view.animate().withEndAction(new Runnable() {
			
			@Override
			public void run() {
				if (leftRight == "LEFT")
					view.animate().translationXBy(view.getWidth()).alpha(1);
				else
					view.animate().translationXBy(-view.getWidth()).alpha(1);
			}
		});
	}
	
}
