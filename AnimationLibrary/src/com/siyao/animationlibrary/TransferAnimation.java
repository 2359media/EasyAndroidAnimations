package com.siyao.animationlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TransferAnimation extends Animation {

	View destinationView;
	long duration;
	ViewGroup parentView;
	
	public TransferAnimation(View destinationView, long duration) {
		this.destinationView = destinationView;
		this.duration = duration;
	}
	
	@Override
	public void animate(final View view) {
		float translationX = destinationView.getLeft() - (view.getLeft() + (view.getWidth() - destinationView.getWidth())/2),
				translationY = destinationView.getBottom() + view.getTop() + (view.getHeight() - destinationView.getHeight())/2;
		parentView = (ViewGroup) view.getParent();
		final FrameLayout transferFrame = new FrameLayout(view.getContext());
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
		
		float scaleX = (float) destinationView.getWidth() / ((float) view.getWidth()),
				scaleY = (float) destinationView.getHeight() / ((float) view.getHeight());
		ValueAnimator scaleXAnim = ObjectAnimator.ofFloat(transferBox, View.SCALE_X, scaleX),
				scaleYAnim = ObjectAnimator.ofFloat(transferBox, View.SCALE_Y, scaleY),
				translateXAnim = ObjectAnimator.ofFloat(transferBox, View.TRANSLATION_X, translationX),
				translateYAnim = ObjectAnimator.ofFloat(transferBox, View.TRANSLATION_Y, -translationY);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(scaleXAnim, scaleYAnim, translateXAnim, translateYAnim);
		animatorSet.setDuration(duration);
		translateXAnim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				transferBox.invalidate();
			}
		});
		animatorSet.start();
		parentView = (ViewGroup) transferFrame.getParent();
		animatorSet.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator animation) {
				transferFrame.removeAllViews();
				parentView.removeView(transferFrame);
				parentView.addView(view);
			}
		});
	}
}
