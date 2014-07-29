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
 * 
 * @author SiYao
 * 
 */
public class HighlightAnimation extends Animation {

	int color;

	/**
	 * The HighlightAnimation makes use of a translucent box to overlay the view
	 * to mimic the highlighting of the view.
	 * 
	 */
	public HighlightAnimation() {
		color = Color.YELLOW;
		duration = Animation.DEFAULT_DURATION;
	}

	/**
	 * The HighlightAnimation makes use of a translucent box to overlay the view
	 * to mimic the highlighting of the view.
	 * 
	 * @param color
	 *            the color of the highlight
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public HighlightAnimation(int color, long duration, AnimationListener listener) {
		this.color = color;
		this.duration = duration;
		this.listener = listener;
	}

	@Override
	public void animate(final View view) {
		final FrameLayout highlightFrame = new FrameLayout(view.getContext());
		final LayoutParams layoutParams = new LayoutParams(view.getLayoutParams());
		
		ImageView highlightView = new ImageView(view.getContext());
		highlightView.setBackgroundColor(color);
		highlightView.setAlpha(0.5f);
		
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final int positionView = parentView.indexOfChild(view);
		parentView.removeViewAt(positionView);
		highlightFrame.addView(view, layoutParams);
		highlightFrame.addView(highlightView, layoutParams);
		parentView.addView(highlightFrame, positionView, layoutParams);
		
		highlightView.animate().alpha(0).setDuration(duration).setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				highlightFrame.removeAllViews();
				parentView.removeViewAt(positionView);
				parentView.addView(view, positionView, layoutParams);
				if (getListener() != null) {
					getListener().onAnimationEnd(HighlightAnimation.this);
				}
			}
		});
	}

}
