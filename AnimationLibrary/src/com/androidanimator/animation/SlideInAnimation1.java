package com.androidanimator.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

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

	public SlideInAnimation1(String direction) {
		this.direction = direction;
	}

	@Override
	public void animate(View view) {
		switch (direction) {
		case "LEFT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X,
					view.getX() - view.getWidth(), view.getX());
			break;
		case "RIGHT":
			slideAnim = ObjectAnimator.ofFloat(view, View.X,
					view.getX() + view.getWidth(), view.getX());
			break;
		case "TOP":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y,
					view.getY() - view.getHeight(), view.getY());
			break;
		case "BOTTOM":
			slideAnim = ObjectAnimator.ofFloat(view, View.Y,
					view.getY() + view.getHeight(), view.getY());
			break;
		default:
			break;
		}
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(slideAnim,
				ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
		animatorSet.start();
		// android.view.animation.Animation slideInX =
		// AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in);
		// view.startAnimation(slideInX);
	}

}
