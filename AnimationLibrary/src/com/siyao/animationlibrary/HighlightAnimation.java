package com.siyao.animationlibrary;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class HighlightAnimation extends Animation {
	
	int color;
	long duration;
	
	public HighlightAnimation() {
		color = Color.YELLOW;
		duration = 500;
	}
	
	public HighlightAnimation(int color, long duration) {
		this.color = color;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent();
		LayoutParams layoutParams = view.getLayoutParams();
		FrameLayout highlightFrame = new FrameLayout(view.getContext());
		highlightFrame.setLayoutParams(layoutParams);
		ImageView highlight = new ImageView(view.getContext());
		highlight.setLayoutParams(layoutParams);
		highlight.setBackgroundColor(color);
		highlight.setAlpha(0.5f);
		highlight.animate().alpha(0).setDuration(duration);
		
		int viewPosition = parentView.indexOfChild(view);
		parentView.removeView(view);
		highlightFrame.addView(view);
		highlightFrame.addView(highlight);
		parentView.addView(highlightFrame, viewPosition);
	}

}
