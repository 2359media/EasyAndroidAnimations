package com.androidanimator.animation;

import android.view.View;

public abstract class Animation {
	
	long duration;
	
	/**
	 * 
	 * @return	the duration of the entire animation
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * 
	 * @param duration	the duration of the entire animation
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * 
	 * @param view	the view to be animated
	 */
	public abstract void animate(View view);
}
