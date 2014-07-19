package com.siyao.animationlibrary;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TransferAnimation extends Animation {

	View destinationView;
	long duration;
	
	public TransferAnimation(View destinationView, long duration) {
		this.destinationView = destinationView;
		this.duration = duration;
	}
	
	@Override
	public void animate(View view) {
		float translationX = destinationView.getLeft() - (view.getLeft() + (view.getWidth() - destinationView.getWidth())/2),
				translationY = destinationView.getBottom() + view.getTop() + (view.getHeight() - destinationView.getHeight())/2;
		ViewGroup parentView = (ViewGroup) view.getParent();
		FrameLayout transferFrame = new FrameLayout(view.getContext());
		final ImageView transferBox = new ImageView(view.getContext());
		LayoutParams layoutParams = view.getLayoutParams();
		transferFrame.setLayoutParams(layoutParams);
		transferBox.setLayoutParams(layoutParams);
		transferBox.setBackgroundResource(R.drawable.border);
		
		parentView.removeView(view);
		transferFrame.addView(view);
		transferFrame.addView(transferBox);
		parentView.addView(transferFrame);
		
		ViewGroup rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		
//		parentView = (ViewGroup) destinationView.getParent();
//		while (!parentView.equals(rootView)) {
//			parentView.setClipChildren(false);
//			parentView = (ViewGroup) parentView.getParent();
//		}
		
		float scaleX = (float) destinationView.getWidth() / (float) view.getWidth(),
				scaleY = (float) destinationView.getHeight() / (float) view.getHeight();
		transferBox.animate().scaleX(scaleX).scaleY(scaleY).x(translationX).y(-translationY).setDuration(duration);
	}
}
