package com.androidanimator.animation;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * The BlindAnimation1 makes use of a box that is of the same size as the view
 * to translate upwards to mimic the blind animation.
 * 
 * @author SiYao
 * 
 */
public class BlindAnimation1 extends Animation {

	int color;
	
	public BlindAnimation1() {
		color = Color.WHITE;
		duration = 500;
	}
	
	public BlindAnimation1(int color, long duration) {
		this.color = color;
		this.duration = duration;
	}

	@Override
	public void animate(final View view) {
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final FrameLayout blindFrame = new FrameLayout(view.getContext());
		LayoutParams layoutParams = view.getLayoutParams();
		blindFrame.setLayoutParams(layoutParams);
		ImageView box = new ImageView(view.getContext());
		box.setLayoutParams(layoutParams);
		box.setY(view.getHeight());
		box.setBackgroundColor(color);
		box.animate().translationYBy(-view.getHeight()).setDuration(duration);
		int viewPosition = parentView.indexOfChild(view);
		parentView.removeView(view);
		blindFrame.addView(view);
		blindFrame.addView(box);
		parentView.addView(blindFrame, viewPosition);
		blindFrame.animate().alpha(0).setDuration(duration).withEndAction(new Runnable() {
			
			@Override
			public void run() {
				blindFrame.removeAllViews();
				parentView.removeView(blindFrame);
				parentView.addView(view);
			}
		});
	}

}
