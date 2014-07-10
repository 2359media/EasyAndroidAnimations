package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class DropAnimation extends AnimationLibrary {
	AnimatorSet animDrop = new AnimatorSet();

	public DropAnimation() {
		animDrop = new AnimatorSet();
	}

	@Override
	public void performAnimation(View v) {
		ObjectAnimator animX = ObjectAnimator.ofFloat(v, "x", v.getWidth()/2);
		ObjectAnimator alpha=ObjectAnimator.ofFloat(v, "alpha", 1f,0f);
		animDrop.setDuration(getDuration());
		animDrop.playTogether(animX, alpha);
		if(getListener()!=null){
			animDrop.addListener(getListener());
		}
		animDrop.start();
	}

	@Override
	public void cancel(View v) {
		animDrop.cancel();
	}

}
