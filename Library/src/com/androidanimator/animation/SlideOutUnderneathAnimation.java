package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * The SlideOutUnderneathAnimation causes the view to slide out underneath to
 * the left, right, up or down depending on the parameters provided by the user.
 * 
 * @author SiYao
 * 
 */
public class SlideOutUnderneathAnimation extends Animation {

	int direction;

	public SlideOutUnderneathAnimation() {
		this.direction = Constant.DIRECTION_LEFT;
		this.duration = Constant.DEFAULT_DURATION;
	}

	public SlideOutUnderneathAnimation(int direction, long duration,
			AnimationListener listener) {
		this.direction = direction;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		ViewGroup parentView = (ViewGroup) view.getParent();
		FrameLayout slideOutFrame = new FrameLayout(view.getContext());
		slideOutFrame.setLayoutParams(view.getLayoutParams());
		parentView.removeView(view);
		slideOutFrame.addView(view);
		parentView.addView(slideOutFrame);

		switch (direction) {
		case Constant.DIRECTION_LEFT:
			view.animate().translationXBy(-view.getWidth());
			break;
		case Constant.DIRECTION_RIGHT:
			view.animate().translationXBy(view.getWidth());
			break;
		case Constant.DIRECTION_UP:
			view.animate().translationYBy(-view.getHeight());
			break;
		case Constant.DIRECTION_DOWN:
			view.animate().translationYBy(view.getHeight());
			break;
		default:
			break;
		}
		view.animate().setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator arg0) {
						if (getListener() != null) {
							getListener().onAnimationEnd(
									SlideOutUnderneathAnimation.this);
						}
						switch (direction) {
						case Constant.DIRECTION_LEFT:
							view.animate().translationXBy(view.getWidth());
							break;
						case Constant.DIRECTION_RIGHT:
							view.animate().translationXBy(-view.getWidth());
							break;
						case Constant.DIRECTION_UP:
							view.animate().translationYBy(view.getHeight());
							break;
						case Constant.DIRECTION_DOWN:
							view.animate().translationYBy(-view.getHeight());
							break;
						default:
							break;
						}
						view.animate().setListener(
								new AnimatorListenerAdapter() {

									@Override
									public void onAnimationEnd(
											Animator animation) {
										animation.cancel();
									}
								});
					}
				});
	}

}
