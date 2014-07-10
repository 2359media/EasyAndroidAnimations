package com.media2359.animation.libs;

import android.animation.ObjectAnimator;
import android.view.View;

public class FaceAnimation extends AnimationLibrary {
	ObjectAnimator anim;

	@Override
	public void performAnimation(View v) {
		if (anim != null) {
			anim = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
			anim.setDuration(getDuration());
			if (getListener() != null) {
				anim.addListener(getListener());
			}
			anim.start();
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void cancel(View v) {
		if (anim != null) {
			anim.cancel();
		} else {
			throw new NullPointerException();
		}
	}

}
