package com.androidanimator.animation;

import java.util.ArrayList;
import java.util.List;

import com.androidanimator.animation.Animation.AnimationListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/**
 * 
 * @author SiYao
 * 
 */
public class PathAnimation extends Animation {

	public static final int ANCHOR_CENTER = 0, ANCHOR_TOP_LEFT = 1,
			ANCHOR_TOP_RIGHT = 2, ANCHOR_BOTTOM_LEFT = 3,
			ANCHOR_BOTTOM_RIGHT = 4;

	ArrayList<Point> points;
	int anchorPosition;
	long duration;
	AnimationListener listener;

	/**
	 * The PathAnimation translates the view within its parent view and
	 * according to the ArrayList of Points provided by the user. The values of
	 * x and y in each Point must be in the range of 0-100. Note that the status
	 * bar and action bar are not taken into consideration.
	 * 
	 * @param points
	 *            the ArrayList of Points that the view is translated to within
	 *            its parent
	 * @param duration
	 *            the duration of the entire animation
	 * @param anchorPosition
	 *            the anchor position whereby the view is translated from
	 * @param listener
	 *            the AnimationListener of animation @see
	 *            {@link AnimationListener}
	 */
	public PathAnimation(View view) {
		this.view = view;
		points = null;
		anchorPosition = ANCHOR_CENTER;
		duration = Animation.DEFAULT_DURATION;
		listener = null;
	}

	@Override
	public void animate() {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view
				.getRootView();
		AnimatorSet allPathsAnim = new AnimatorSet(), pathAnim = new AnimatorSet();
		int numOfPoints = points.size();
		AnimatorSet[] pathAnimSetArray = new AnimatorSet[numOfPoints];
		List<Animator> pathAnimList = new ArrayList<Animator>();

		int parentWidth = parentView.getWidth(), parentHeight = parentView
				.getHeight(), viewWidth = view.getWidth(), viewHeight = view
				.getHeight();
		float posX, posY;
		for (int i = 0; i < numOfPoints; i++) {
			posX = (points.get(i).x / 100f * parentWidth);
			posY = (points.get(i).y / 100f * parentHeight);

			switch (anchorPosition) {
			case ANCHOR_CENTER:
				posX = posX - viewWidth / 2;
				posY = posY - viewHeight / 2;
				break;
			case ANCHOR_TOP_RIGHT:
				posX -= viewWidth;
				break;
			case ANCHOR_BOTTOM_LEFT:
				posY -= viewHeight;
				break;
			case ANCHOR_BOTTOM_RIGHT:
				posX = posX - viewWidth;
				posY = posY - viewHeight;
				break;
			default:
				break;
			}
			pathAnimSetArray[i] = new AnimatorSet();
			pathAnimSetArray[i].playTogether(
					ObjectAnimator.ofFloat(view, View.X, posX),
					ObjectAnimator.ofFloat(view, View.Y, posY));
			pathAnimSetArray[i].setInterpolator(new LinearInterpolator());
			pathAnimList.add(pathAnimSetArray[i]);
		}

		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);

		allPathsAnim.playSequentially(pathAnimList);
		pathAnim.play(allPathsAnim);
		allPathsAnim.setDuration(duration / numOfPoints);
		allPathsAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (getListener() != null) {
					getListener().onAnimationEnd(PathAnimation.this);
				}
			}
		});
		allPathsAnim.start();
	}

	/**
	 * @return the points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public PathAnimation setPoints(ArrayList<Point> points) {
		this.points = points;
		return this;
	}

	/**
	 * @return the anchorPosition
	 */
	public int getAnchorPosition() {
		return anchorPosition;
	}

	/**
	 * @param anchorPosition the anchorPosition to set
	 */
	public PathAnimation setAnchorPosition(int anchorPosition) {
		this.anchorPosition = anchorPosition;
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
	public PathAnimation setDuration(long duration) {
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
	public PathAnimation setListener(AnimationListener listener) {
		this.listener = listener;
		return this;
	}
}
