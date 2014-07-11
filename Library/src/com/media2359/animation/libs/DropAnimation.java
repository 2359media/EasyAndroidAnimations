package com.media2359.animation.libs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class DropAnimation extends Animation {
	AnimatorSet animDrop = new AnimatorSet();

	public DropAnimation() {
		animDrop = new AnimatorSet();
	}

	@Override
	public void performAnimation(View v) {
		ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.X, v.getWidth()/2);
		ObjectAnimator alpha=ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f,0f);
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

    @Override
    public void reset(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, Constant.X,0, v.getWidth());
        ObjectAnimator alpha=ObjectAnimator.ofFloat(v, Constant.ALPHA, 1f);
        animDrop.playTogether(animX, alpha);
        animDrop.start();
    }

}
