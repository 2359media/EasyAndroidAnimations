package com.siyao.animationlibrary;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BlindAnimation extends Animation {

	int color;
	long duration;
	
	public BlindAnimation() {
		color = Color.WHITE;
		duration = 500;
	}
	
	public BlindAnimation(int color, long duration) {
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
