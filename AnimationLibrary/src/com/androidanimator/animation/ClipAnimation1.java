package com.androidanimator.animation;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * The ClipAnimation1 makes use of a box that is of the same size as the view to
 * translate upwards while translating the view downwards to mimic the clip
 * animation.
 * 
 * @author SiYao
 * 
 */
public class ClipAnimation1 extends Animation {

	int color;

	public ClipAnimation1() {
		color = Color.WHITE;
		duration = 500;
	}

	public ClipAnimation1(int color) {
		this.color = color;
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
		box.animate().translationYBy(-view.getHeight() / 2)
				.setDuration(duration);
		view.animate().translationYBy(view.getHeight() / 2)
				.setDuration(duration);
		int viewPosition = parentView.indexOfChild(view);
		parentView.removeView(view);
		blindFrame.addView(view);
		blindFrame.addView(box);
		parentView.addView(blindFrame, viewPosition);
		blindFrame.animate().alpha(0).setDuration(duration)
				.withEndAction(new Runnable() {

					@Override
					public void run() {
						blindFrame.removeAllViews();
						parentView.removeView(blindFrame);
						parentView.addView(view);
						view.animate().translationYBy(-view.getHeight() / 2)
								.alpha(1);
					}
				});
	}

}
