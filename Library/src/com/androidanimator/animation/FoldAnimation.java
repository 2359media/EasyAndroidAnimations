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
 * This animation hides the view by scaling its X and Y properties to mimic a
 * folding effect.
 * 
 * @author Phu
 * 
 */
public class FoldAnimation extends Animation {

	long duration;
	AnimationListener listener;

	/**
	 * This animation hides the view by scaling its X and Y properties to mimic
	 * a folding effect.
	 * 
	 * @param view
	 *            The view to be animated.
	 */
	public FoldAnimation(View view) {
		this.view = view;
		duration = DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parent = (ViewGroup) view.getParent();
		parent.removeView(view);
		LayoutParams originalParam = view.getLayoutParams(), newParam = new LayoutParams(
				view.getWidth(), view.getHeight());
		view.setLayoutParams(newParam);
		FrameLayout animationLayout = new FrameLayout(view.getContext());
		animationLayout.setId(view.getId());
		animationLayout.setLayoutParams(originalParam);
		animationLayout.addView(view);
		parent.addView(animationLayout);

		animationLayout.setPivotX(1f);
		animationLayout.setPivotY(1f);
		view.setPivotX(1f);
		view.setPivotY(1f);

		ObjectAnimator animY1 = ObjectAnimator.ofFloat(animationLayout,
				View.SCALE_Y, 1f, 0.5f), animY1_child = ObjectAnimator.ofFloat(
				view, View.SCALE_Y, 1f, 2f), animY2 = ObjectAnimator.ofFloat(
				animationLayout, View.SCALE_Y, 0.5f, 0f), animX = ObjectAnimator
				.ofFloat(animationLayout, View.SCALE_X, 1f, 0f), animY2_child = ObjectAnimator
				.ofFloat(view, View.SCALE_Y, 2f, 2.5f), animX_child = ObjectAnimator
				.ofFloat(view, View.SCALE_X, 1f, 2.5f);

		AnimatorSet fold2 = new AnimatorSet();
		fold2.playTogether(animY2, animX, animY2_child, animX_child);

		AnimatorSet step1 = new AnimatorSet();
		step1.playTogether(animY1, animY1_child);

		AnimatorSet animFold = new AnimatorSet();
		animFold.playSequentially(step1, fold2);
		animFold.setDuration(duration / 2);
		animFold.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(FoldAnimation.this);
				}
			}
		});
		animFold.start();
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
	public FoldAnimation setDuration(long duration) {
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
	public FoldAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
