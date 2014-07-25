package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
		duration = Constant.DEFAULT_DURATION;
	}

	public HighlightAnimation1(int color, long duration, AnimationListener listener) {
		this.color = color;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final LayoutParams layoutParams = view.getLayoutParams();
		final FrameLayout highlightFrame = new FrameLayout(view.getContext());
		highlightFrame.setLayoutParams(layoutParams);
		final ImageView highlight = new ImageView(view.getContext());
		highlight.setLayoutParams(layoutParams);
		highlight.setBackgroundColor(color);
		highlight.setAlpha(0.5f);
		final int positionView = parentView.indexOfChild(view);
		parentView.removeView(view);
		highlightFrame.addView(view);
		highlightFrame.addView(highlight);
		parentView.addView(highlightFrame, positionView);
		
		highlight.animate().alpha(0).setDuration(duration).setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(HighlightAnimation1.this);
				}
				highlightFrame.removeAllViews();
				parentView.removeView(highlightFrame);
				view.setLayoutParams(layoutParams);
				parentView.addView(view, positionView);
			}
		});
	}

}
