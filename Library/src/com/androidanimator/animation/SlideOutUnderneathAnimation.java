package com.androidanimator.animation;

import com.androidanimator.AndroidAnimator;
import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 
 * @author SiYao
 * 
 */
public class SlideOutUnderneathAnimation extends Animation {

	int direction;

	/**
	 * The SlideOutUnderneathAnimation causes the view to slide out underneath
	 * to the left, right, up or down depending on the parameters provided by
	 * the user.
	 * 
	 */
	public SlideOutUnderneathAnimation() {
		direction = AndroidAnimator.DIRECTION_LEFT;
		duration = Animation.DEFAULT_DURATION;
	}

	/**
	 * The SlideOutUnderneathAnimation causes the view to slide out underneath
	 * to the left, right, up or down depending on the parameters provided by
	 * the user.
	 * 
	 * @param direction
	 *            the direction to slide out underneath to
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public SlideOutUnderneathAnimation(int direction, long duration,
			AnimationListener listener) {
		this.direction = direction;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final FrameLayout slideOutFrame = new FrameLayout(view.getContext());
		final int positionView = parentView.indexOfChild(view);
		slideOutFrame.setLayoutParams(view.getLayoutParams());
		slideOutFrame.setClipChildren(true);
		parentView.removeView(view);
		slideOutFrame.addView(view);
		parentView.addView(slideOutFrame, positionView);

		switch (direction) {
		case AndroidAnimator.DIRECTION_LEFT:
			view.animate().translationXBy(-view.getWidth());
			break;
		case AndroidAnimator.DIRECTION_RIGHT:
			view.animate().translationXBy(view.getWidth());
			break;
		case AndroidAnimator.DIRECTION_UP:
			view.animate().translationYBy(-view.getHeight());
			break;
		case AndroidAnimator.DIRECTION_DOWN:
			view.animate().translationYBy(view.getHeight());
			break;
		default:
			break;
		}
		view.animate().setDuration(duration)
				.setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationEnd(Animator arg0) {
						slideOutFrame.removeAllViews();
						view.setLayoutParams(slideOutFrame.getLayoutParams());
						view.setVisibility(View.INVISIBLE);
						parentView.removeView(slideOutFrame);
						parentView.addView(view, positionView);
						if (getListener() != null) {
							getListener().onAnimationEnd(
									SlideOutUnderneathAnimation.this);
						}
					}
				});
	}

}
