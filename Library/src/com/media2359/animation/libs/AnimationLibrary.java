package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
import android.view.View;

public abstract class AnimationLibrary {

	public static final int DEFAULT_DURATION = 500;

	private AnimatorListener listener;
	private int duration = DEFAULT_DURATION;

	public AnimatorListener getListener() {
		return listener;
	}

	public void setListener(AnimatorListener listener) {
		this.listener = listener;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public abstract void performAnimation(View v);

	public abstract void cancel(View v);

}
