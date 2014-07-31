package com.androidanimator.animation;

import android.view.View;

public abstract class Animation {

	// constants
	public static final int DIRECTION_LEFT = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_UP = 3;
	public static final int DIRECTION_DOWN = 4;

	public static final int DEFAULT_DURATION = 500; // 500 ms

	View view;

	public abstract void animate();

	public interface AnimationListener {
		// TODO add onAnimationStart
		public void onAnimationEnd(Animation animation);
	}

}
