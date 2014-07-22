package com.androidanimator.animation;


import android.view.View;

/**
 * The SlideOutAnimation1 causes the view to slide out to the left, right, top or
 * bottom depending on the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class SlideOutAnimation1 extends Animation {

	String direction;
	
	public SlideOutAnimation1() {
		direction = "LEFT";
		duration = 500;
	}
	
	public SlideOutAnimation1(String direction, long duration) {
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
