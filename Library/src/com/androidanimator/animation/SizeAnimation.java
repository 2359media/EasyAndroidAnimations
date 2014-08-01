package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class SizeAnimation extends Animation {

	long duration;
	AnimationListener listener;

	public SizeAnimation(View view) {
		this.view = view;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		view.setPivotX(1f);
		view.setPivotY(1f);
		AnimatorSet scale = new AnimatorSet(), sizeAnimSet = new AnimatorSet();
		scale.playTogether(ObjectAnimator.ofFloat(view, View.SCALE_X, 0.5f),
				ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.5f));
		sizeAnimSet.playSequentially(scale, ObjectAnimator.ofFloat(view, View.ALPHA, 0f));
		sizeAnimSet.setDuration(duration / 2);
		sizeAnimSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(SizeAnimation.this);
				}
			}
		});
		sizeAnimSet.start();
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
	public SizeAnimation setDuration(long duration) {
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
	public SizeAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}

//
// import android.animation.Animator.AnimatorListener;
// import android.animation.Animator;
// import android.animation.AnimatorSet;
// import android.animation.ObjectAnimator;
// import android.view.View;
//
// /**
// * @author phutang
// *
// */
// public class SizeAnimation extends Animation {
// AnimatorSet sizeAnimatorSet;
//
// public SizeAnimation() {
// sizeAnimatorSet = new AnimatorSet();
// }
//
// public SizeAnimation(AnimationListener listener, long duration) {
// super(listener, duration);
// sizeAnimatorSet = new AnimatorSet();
// }
//
// @Override
// public void animate(View v) {
// getAnimatorSet(v);
// sizeAnimatorSet.start();
// }
//
//
// public void reset(View v) {
// ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1f);
// ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1f);
// ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, View.ALPHA, 1f);
// AnimatorSet scale = new AnimatorSet();
// scale.playTogether(scaleX, scaleY, alphaA);
// scale.start();
// }
//
// @Override
// public AnimatorSet getAnimatorSet(View v) {
// ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1f, 0.5f);
// ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1f, 0.5f);
// ObjectAnimator alphaA = ObjectAnimator.ofFloat(v, View.ALPHA, 1f, 0f);
// AnimatorSet scale = new AnimatorSet();
// scale.playTogether(scaleX, scaleY);
// sizeAnimatorSet.playSequentially(scale, alphaA);
// sizeAnimatorSet.setDuration(getDuration());
// if (getListener() != null) {
// sizeAnimatorSet.addListener(new AnimatorListener() {
//
// @Override
// public void onAnimationStart(Animator animation) {
//
// }
//
// @Override
// public void onAnimationRepeat(Animator animation) {
//
// }
//
// @Override
// public void onAnimationEnd(Animator animation) {
// getListener().onAnimationEnd(SizeAnimation.this);
// }
//
// @Override
// public void onAnimationCancel(Animator animation) {
//
// }
// });
// }
// v.setPivotX(1f);
// v.setPivotY(1f);
// return sizeAnimatorSet;
// }
//
// }
