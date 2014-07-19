package com.siyao.animationlibrary;

import android.view.View;

public class SlideOutAnimation extends Animation {

	String direction;
	long duration;
	
	public SlideOutAnimation() {
		direction = "LEFT";
		duration = 500;
	}
	
	public SlideOutAnimation(String direction, long duration) {
		this.direction = direction;
		this.duration = duration;
	}
	
	@Override
	public void animate(final View view) {
		switch (direction) {
		case "LEFT":
			view.animate().translationXBy(-view.getWidth()).alpha(0);
			break;
		case "RIGHT":
			view.animate().translationXBy(view.getWidth()).alpha(0);
			break;
		case "TOP":
			view.animate().translationYBy(-view.getHeight()).alpha(0);
			break;
		case "BOTTOM":
			view.animate().translationYBy(view.getHeight()).alpha(0);
			break;
		default:
			break;
		}
		view.animate().setDuration(duration).withEndAction(new Runnable() {
			
			@Override
			public void run() {
				switch (direction) {
				case "LEFT":
					view.animate().translationXBy(view.getWidth()).alpha(1);
					break;
				case "RIGHT":
					view.animate().translationXBy(-view.getWidth()).alpha(1);
					break;
				case "TOP":
					view.animate().translationYBy(view.getHeight()).alpha(1);
					break;
				case "BOTTOM":
					view.animate().translationYBy(-view.getHeight()).alpha(1);
					break;
				default:
					break;
				}
			}
		});
	}
	
}
