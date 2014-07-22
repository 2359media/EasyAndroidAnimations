package com.androidanimator.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.siyao.animationlibrary.R;

/**
 * The TransferAnimation1 transfers the view to another view provided by the
 * user through scaling and translation.
 * 
 * @author SiYao
 * 
 */
public class TransferAnimation1 extends Animation {

	View destinationView;
	ViewGroup parentView;
	float positionX, positionY;
	
	public TransferAnimation1(View destinationView) {
		this.destinationView = destinationView;
	}
	
	@Override
	public void animate(final View view) {
		parentView = (ViewGroup) view.getParent();
		final FrameLayout transferFrame = new FrameLayout(view.getContext());
		//final ImageView transferBox = new ImageView(view.getContext());
		LayoutParams layoutParams = view.getLayoutParams();
		transferFrame.setLayoutParams(layoutParams);
		//transferBox.setLayoutParams(layoutParams);
		//transferBox.setBackgroundResource(R.drawable.border);
		int[] locationOriginalView = new int[2];
		view.getLocationOnScreen(locationOriginalView);
		Log.d("locationOriginalView", locationOriginalView[0] + " " + locationOriginalView[1]);
		
		int positionView = parentView.indexOfChild(view);
		//parentView.removeView(view);
		//transferFrame.addView(view);
		//transferFrame.addView(transferBox);
		//parentView.addView(transferFrame, positionView);
		
		final ViewGroup rootView = (ViewGroup) view.getRootView();
		while (!parentView.equals(rootView)) {
			parentView.setClipChildren(false);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(false);
		
		
		
		final float scaleX = (float) destinationView.getWidth() / ((float) view.getWidth()),
				scaleY = (float) destinationView.getHeight() / ((float) view.getHeight());
		
		int[] locationDest = new int[2];
		int[] locationView = new int[2];
		//int[] locationTB = new int[2];
		//transferBox.getLocationOnScreen(locationTB);
		int transX, transY;
		view.getLocationOnScreen(locationView);
		destinationView.getLocationOnScreen(locationDest);
		
		Log.d("locationView", locationView[0] + " " + locationView[1]);
		Log.d("locationDest", locationDest[0] + " " + locationDest[1]);
		//Log.d("locationTB", locationTB[0] + " " + locationTB[1]);
		
		
		
		if (locationView[0] < locationDest[0])
			transX = locationDest[0] - locationView[0]; 
		else
			transX = locationView[0] - locationDest[0];
		
		if (locationView[1] > locationDest[1])
			transY = locationDest[1] - locationView[1]; 
		else
			transY = locationView[1] - locationDest[1];
		
		//transX = transX - view.getWidth()/2 + destinationView.getWidth();
		//transY = transY - view.getHeight()/2 + destinationView.getHeight();
		//transY = transY + destinationView.getHeight()/2;
		
		final ValueAnimator scaleXAnim = ObjectAnimator.ofFloat(view, View.SCALE_X, scaleX),
				scaleYAnim = ObjectAnimator.ofFloat(view, View.SCALE_Y, scaleY),
				translateXAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, transX),
				translateYAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, transY),
				alphaAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0.5f);
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(translateXAnim, translateYAnim, alphaAnim);
		animatorSet.setDuration(duration);
		translateXAnim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				//view.invalidate();
			}
		});
		//parentView.invalidate(); 
		animatorSet.start();
//				animatorSet.addListener(new AnimatorListenerAdapter() {
//					
//					@Override
//					public void onAnimationEnd(Animator animation) {
//						scaleXAnim.reverse();
//						scaleYAnim.reverse();
//						translateXAnim.reverse();
//						translateYAnim.reverse();
//					}
//				}); 
	}
}
