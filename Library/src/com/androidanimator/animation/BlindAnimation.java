package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/**
 * This animation hides the view by scaling its Y property to mimic the
 * "pulling of blinds".
 * 
 * @author Phu
 * 
 */
public class BlindAnimation extends Animation {

	long duration;
	AnimationListener listener;

	/**
	 * This animation hides the view by scaling its Y property to mimic the
	 * "pulling of blinds".
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public BlindAnimation(View view) {
		this.view = view;
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parent = (ViewGroup) view.getParent();
		parent.removeView(view);
		LayoutParams originalParam = view.getLayoutParams();
		LayoutParams newParam = new LayoutParams(view.getWidth(),
				view.getHeight());
		view.setLayoutParams(newParam);
		ViewGroup animationLayout = new FrameLayout(view.getContext());
		animationLayout.setId(view.getId());
		animationLayout.setLayoutParams(originalParam);
		animationLayout.addView(view);
		parent.addView(animationLayout);

		ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout,
				View.SCALE_Y, 1f, 0f), scaleY_child = ObjectAnimator.ofFloat(
				view, View.SCALE_Y, 1f, 2.5f);
		view.setPivotX(1f);
		view.setPivotY(1f);
		AnimatorSet blindAnimationSet = new AnimatorSet();
		blindAnimationSet.playTogether(scaleY, scaleY_child);
		blindAnimationSet.setDuration(duration / 2);
		blindAnimationSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(BlindAnimation.this);
				}
			}
		});
		animationLayout.setPivotX(1f);
		animationLayout.setPivotY(1f);
		blindAnimationSet.start();
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
	public BlindAnimation setDuration(long duration) {
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
	public BlindAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
