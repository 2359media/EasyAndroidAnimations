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
	long duration;
	AnimationListener listener;

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
	public HighlightAnimation() {
		color = Color.YELLOW;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate(final View view) {
		final FrameLayout highlightFrame = new FrameLayout(view.getContext());
		LayoutParams layoutParams = new LayoutParams(view.getWidth(), view.getHeight());
		ImageView highlightView = new ImageView(view.getContext());
		highlightView.setBackgroundColor(color);
		highlightView.setAlpha(0.5f);
		highlightView.setVisibility(View.VISIBLE);
		
		final ViewGroup parentView = (ViewGroup) view.getParent();
		final int positionView = parentView.indexOfChild(view);
		parentView.addView(highlightFrame, positionView, layoutParams);
		highlightFrame.setX(view.getLeft());
		highlightFrame.setY(view.getTop());
		parentView.removeView(view);
		highlightFrame.addView(view);
		highlightFrame.addView(highlightView);
		
		highlightView.animate().alpha(0).setDuration(duration).setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				highlightFrame.removeAllViews();
				parentView.addView(view, positionView);
				view.setX(highlightFrame.getLeft());
				view.setY(highlightFrame.getTop());
				parentView.removeView(highlightFrame);
				if (getListener() != null) {
					getListener().onAnimationEnd(HighlightAnimation.this);
				}
			}
		});
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public HighlightAnimation setColor(int color) {
		this.color = color;
		return this;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public HighlightAnimation setDuration(long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return the listener
	 */
	public AnimationListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public HighlightAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
