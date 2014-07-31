package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BlindAnimation extends Animation {

	long duration;
	AnimationListener listener;

	public BlindAnimation(View view) {
		this.view = view;
		duration = Animation.DEFAULT_DURATION;
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
		blindAnimationSet.setDuration(duration);
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
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public BlindAnimation setDuration(long duration) {
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
	 * @param listener
	 *            the listener to set
	 */
	public BlindAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}
	//
	// AnimatorSet blindAnimationSet;
	// View child;
	// ViewGroup animationLayout;
	// LayoutParams originalParam;
	// Context mContext;
	//
	// public BlindAnimation() {
	// blindAnimationSet = new AnimatorSet();
	// }
	//
	//
	// public BlindAnimation(AnimationListener listener, long duration) {
	// super(listener, duration);
	// blindAnimationSet = new AnimatorSet();
	// }
	//
	// @Override
	// public void animate(View v) {
	// getAnimatorSet(v);
	// blindAnimationSet.start();
	// }
	//
	//
	// public View addToAnimatioView(View v) {
	// child = v;
	// ViewGroup parent = (ViewGroup) v.getParent();
	// parent.removeView(v);
	// originalParam = v.getLayoutParams();
	// LayoutParams newParam = new LayoutParams(v.getWidth(), v.getHeight());
	// v.setLayoutParams(newParam);
	// animationLayout = new FrameLayout(v.getContext());
	// animationLayout.setId(v.getId());
	// animationLayout.setLayoutParams(originalParam);
	// animationLayout.addView(v);
	// parent.addView(animationLayout);
	// return animationLayout;
	// }
	//
	// @Override
	// public AnimatorSet getAnimatorSet(View v) {
	// addToAnimatioView(v);
	// ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout,
	// View.SCALE_Y, 1f, 0f);
	// child.setPivotX(1f);
	// child.setPivotY(1f);
	// ObjectAnimator scaleY_child = ObjectAnimator.ofFloat(child, View.SCALE_Y,
	// 1f, 2.5f);
	// blindAnimationSet.playTogether(scaleY, scaleY_child);
	// blindAnimationSet.setDuration(getDuration());
	// if (getListener() != null) {
	// blindAnimationSet.addListener(new AnimatorListenerAdapter() {
	//
	// @Override
	// public void onAnimationEnd(Animator animation) {
	// getListener().onAnimationEnd(BlindAnimation.this);
	// }
	// });
	// }
	// animationLayout.setPivotX(1f);
	// animationLayout.setPivotY(1f);
	// return blindAnimationSet;
	// }
	//
}
