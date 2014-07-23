package com.androidanimator.animation;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class PathAnimation extends Animation{
	
	ArrayList<Point> points;
	
	public PathAnimation(ArrayList<Point> points, long duration) {
		this.points = points;
		this.duration = duration;
	}

	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent(), rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		
		AnimatorSet allPathsAnim = new AnimatorSet(), pathAnim = new AnimatorSet();
		int numOfPoints = points.size();
		AnimatorSet[] pathAnimSetArray = new AnimatorSet[numOfPoints];
		List<Animator> pathAnimList = new ArrayList<Animator>();  

		float posX, posY;
		int[] locationView = new int[2];
		view.getLocationOnScreen(locationView);
		for (int i = 0; i < numOfPoints; i++) {
			posX = points.get(i).x - rootView.getLeft();
			posY = points.get(i).y - locationView[1];
			
			pathAnimSetArray[i] = new AnimatorSet();
			pathAnimSetArray[i].playTogether(ObjectAnimator.ofFloat(view, View.X, posX), ObjectAnimator.ofFloat(view, View.Y, posY));
			//pathAnimSetArray[i].setInterpolator(new LinearInterpolator());
			pathAnimList.add(pathAnimSetArray[i]);
		}
		
		allPathsAnim.playSequentially(pathAnimList);
		pathAnim.play(allPathsAnim);
		pathAnim.setDuration(duration);
		allPathsAnim.start();
	}

}
