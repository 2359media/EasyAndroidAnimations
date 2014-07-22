package com.androidanimator.animation;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * The HighlightAnimation1 makes use of a translucent box to overlay the view to
 * mimic the highlighting of the view.
 * 
 * @author SiYao
 * 
 */
public class HighlightAnimation1 extends Animation {

	int color;

	public HighlightAnimation1() {
		color = Color.YELLOW;
		duration = 500;
	}

	public HighlightAnimation1(int color) {
		this.color = color;
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
