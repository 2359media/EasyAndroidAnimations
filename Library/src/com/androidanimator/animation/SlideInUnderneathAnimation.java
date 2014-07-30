package com.androidanimator.animation;

import com.androidanimator.AndroidAnimator;
import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 
 * @author SiYao
 * 
 */
public class SlideInUnderneathAnimation extends Animation {
	
	int direction;
	long duration;
	AnimationListener listener;
	
	/**
	 * The SlideInUnderneathAnimation causes the view to slide in underneath
	 * from the left, right, up or down depending on the parameters provided by
	 * the user.
	 * 
	 * @param direction
	 *            the direction to slide in underneath from
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public SlideInUnderneathAnimation() {
		direction = AndroidAnimator.DIRECTION_LEFT;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(final View view) {
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final FrameLayout slideInFrame = new FrameLayout(view.getContext());
		final int positionView = parentView.indexOfChild(view);
		slideInFrame.setLayoutParams(view.getLayoutParams());
		slideInFrame.setClipChildren(true);
		parentView.removeView(view);
		slideInFrame.addView(view);
		parentView.addView(slideInFrame, positionView);
		
		ObjectAnimator slideInAnim = null;
		switch (direction) {
		case AndroidAnimator.DIRECTION_LEFT:
			slideInAnim = ObjectAnimator.ofFloat(view, View.X, -view.getWidth(), slideInFrame.getX());
			break;
		case AndroidAnimator.DIRECTION_RIGHT:
			slideInAnim = ObjectAnimator.ofFloat(view, View.X, view.getWidth(), slideInFrame.getX());
			break;
		case AndroidAnimator.DIRECTION_UP:
			slideInAnim = ObjectAnimator.ofFloat(view, View.Y, -view.getHeight(), slideInFrame.getY());
			break;
		case AndroidAnimator.DIRECTION_DOWN:
			slideInAnim = ObjectAnimator.ofFloat(view, View.Y, view.getHeight(), slideInFrame.getY());
			break;
		default:
			break;
		}
		view.setVisibility(View.VISIBLE);
		slideInAnim.setDuration(duration);
		slideInAnim.start();
		slideInAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				slideInFrame.removeAllViews();
				view.setLayoutParams(slideInFrame.getLayoutParams());
				parentView.addView(view, positionView);
				if (getListener() != null) {
					getListener().onAnimationEnd(SlideInUnderneathAnimation.this);
				}
			}
		});
	}
	
	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public SlideInUnderneathAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public SlideInUnderneathAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return the listener
	 */
	public AnimationListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public SlideInUnderneathAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
