package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * This animation causes the view to slide out underneath to its own borders.
 * 
 * @author SiYao
 * 
 */
public class SlideOutUnderneathAnimation extends Animation {

	int direction;
	TimeInterpolator interpolator;
	long duration;
	AnimationListener listener;

	/**
	 * This animation causes the view to slide out underneath to its own
	 * borders.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public SlideOutUnderneathAnimation(View view) {
		this.view = view;
		direction = DIRECTION_LEFT;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final FrameLayout slideOutFrame = new FrameLayout(view.getContext());
		final int positionView = parentView.indexOfChild(view);
		slideOutFrame.setLayoutParams(view.getLayoutParams());
		slideOutFrame.setClipChildren(true);
		parentView.removeView(view);
		slideOutFrame.addView(view);
		parentView.addView(slideOutFrame, positionView);

		switch (direction) {
		case DIRECTION_LEFT:
			view.animate().translationXBy(-view.getWidth());
			break;
		case DIRECTION_RIGHT:
			view.animate().translationXBy(view.getWidth());
			break;
		case DIRECTION_UP:
			view.animate().translationYBy(-view.getHeight());
			break;
		case DIRECTION_DOWN:
			view.animate().translationYBy(view.getHeight());
			break;
		default:
			break;
		}
		view.animate().setInterpolator(interpolator).setDuration(duration)
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

	/**
	 * The available directions to slide in from are <code>DIRECTION_LEFT</code>
	 * , <code>DIRECTION_RIGHT</code>, <code>DIRECTION_TOP</code> and
	 * <code>DIRECTION_BOTTOM</code>.
	 * 
	 * @return The direction to slide the view out to.
	 * @see Animation
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * The available directions to slide in from are <code>DIRECTION_LEFT</code>
	 * , <code>DIRECTION_RIGHT</code>, <code>DIRECTION_TOP</code> and
	 * <code>DIRECTION_BOTTOM</code>.
	 * 
	 * @param direction
	 *            The direction to set to slide the view out to.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 * @see Animation
	 */
	public SlideOutUnderneathAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * @return The interpolator of the entire animation.
	 */
	public TimeInterpolator getInterpolator() {
		return interpolator;
	}

	/**
	 * @param interpolator
	 *            The interpolator of the entire animation to set.
	 */
	public SlideOutUnderneathAnimation setInterpolator(
			TimeInterpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}

	/**
	 * @return The duration of the entire animation.
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            The duration of the entire animation to set.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 */
	public SlideOutUnderneathAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return The listener for the end of the animation.
	 */
	public AnimationListener getListener() {
		return listener;
	}

	/**
	 * @param listener
	 *            The listener to set for the end of the animation.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 */
	public SlideOutUnderneathAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
