package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidanimator.animation.Animation;
import com.androidanimator.animation.Animation.AnimationListener;

/**
 * 
 * @author SiYao
 * 
 */
public class ExplodeAnimation extends Animation {

	ViewGroup parentView;
	int xParts, yParts;
	long duration;
	AnimationListener listener;

	/**
	 * The ExplodeAnimation creates a bitmap of the view, divides them into X
	 * and Y parts and translates the parts away from the center of the view to
	 * mimic an explosion. The number of parts can vary from 1x1 to 3x3. The
	 * view is set to invisible and added back for reusing.
	 * 
	 * @param xParts
	 *            the number of x parts to be exploded
	 * @param yParts
	 *            the number of y parts to be exploded
	 * @param duration
	 *            the duration of the entire animation
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public ExplodeAnimation(View view) {
		this.view = view;
		xParts = 3;
		yParts = 3;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		final LinearLayout explodeLayout = new LinearLayout(view.getContext());
		LinearLayout[] layouts = new LinearLayout[yParts];
		parentView = (ViewGroup) view.getParent();
		explodeLayout.setLayoutParams(view.getLayoutParams());
		explodeLayout.setOrientation(LinearLayout.VERTICAL);
		explodeLayout.setClipChildren(false);

		view.setDrawingCacheEnabled(true);
		Bitmap viewBmp = view.getDrawingCache(true);
		int totalParts = xParts * yParts, bmpWidth = viewBmp.getWidth()
				/ xParts, bmpHeight = viewBmp.getHeight() / yParts, widthCount = 0, heightCount = 0, middleXPart = (xParts - 1) / 2;
		int[] translation = new int[2];
		ImageView[] imageViews = new ImageView[totalParts];

		for (int i = 0; i < totalParts; i++) {
			int translateX = 0, translateY = 0;
			if (i % xParts == 0) {
				if (i != 0)
					heightCount++;
				widthCount = 0;
				layouts[heightCount] = new LinearLayout(view.getContext());
				layouts[heightCount].setClipChildren(false);
				translation = sideTranslation(heightCount, bmpWidth, bmpHeight,
						xParts, yParts);
				translateX = translation[0];
				translateY = translation[1];
			} else if (i % xParts == xParts - 1) {
				translation = sideTranslation(heightCount, -bmpWidth,
						bmpHeight, xParts, yParts);
				translateX = translation[0];
				translateY = translation[1];
			} else {
				if (widthCount == middleXPart) {
					if (heightCount == 0) {
						translateX = 0;
						translateY = -bmpHeight;
					} else if (heightCount == yParts - 1) {
						translateX = 0;
						translateY = bmpHeight;
					}
				}
			}
			if (xParts == 1) {
				translation = sideTranslation(heightCount, 0, bmpHeight,
						xParts, yParts);
				translateX = translation[0];
				translateY = translation[1];
			}

			imageViews[i] = new ImageView(view.getContext());
			imageViews[i]
					.setImageBitmap(Bitmap.createBitmap(viewBmp, bmpWidth
							* widthCount, bmpHeight * heightCount, bmpWidth,
							bmpHeight));
			imageViews[i].animate().translationXBy(translateX)
					.translationYBy(translateY).alpha(0).setDuration(duration);
			layouts[heightCount].addView(imageViews[i]);
			widthCount++;
		}

		for (int i = 0; i < yParts; i++)
			explodeLayout.addView(layouts[i]);
		final int positionView = parentView.indexOfChild(view);
		parentView.removeView(view);
		parentView.addView(explodeLayout, positionView);

		ViewGroup rootView = (ViewGroup) explodeLayout.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		imageViews[0].animate().setListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				parentView = (ViewGroup) explodeLayout.getParent();
				view.setLayoutParams(explodeLayout.getLayoutParams());
				view.setVisibility(View.INVISIBLE);
				parentView.removeView(explodeLayout);
				parentView.addView(view, positionView);
				if (getListener() != null) {
					getListener().onAnimationEnd(ExplodeAnimation.this);
				}
			}
		});
	}

	private int[] sideTranslation(int heightCount, int bmpWidth, int bmpHeight,
			int xParts, int yParts) {
		int[] translation = new int[2];
		int middleYPart = (yParts - 1) / 2;
		if (heightCount == 0) {
			translation[0] = -bmpWidth;
			translation[1] = -bmpHeight;
		} else if (heightCount == yParts - 1) {
			translation[0] = -bmpWidth;
			translation[1] = bmpHeight;
		}

		if (yParts % 2 != 0) {
			if (heightCount == middleYPart) {
				translation[0] = -bmpWidth;
				translation[1] = 0;
			}
		}
		return translation;
	}

	/**
	 * @return the xParts
	 */
	public int getXParts() {
		return xParts;
	}

	/**
	 * @param xParts
	 *            the xParts to set
	 */
	public ExplodeAnimation setXParts(int xParts) {
		this.xParts = xParts;
		return this;
	}

	/**
	 * @return the yParts
	 */
	public int getYParts() {
		return yParts;
	}

	/**
	 * @param yParts
	 *            the yParts to set
	 */
	public ExplodeAnimation setYParts(int yParts) {
		this.yParts = yParts;
		return this;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public ExplodeAnimation setDuration(long duration) {
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
	 * @param listener
	 *            the listener to set
	 */
	public ExplodeAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}

}
