package com.siyao.animationlibrary;

import android.view.View;

public class MyAnimator {
	
	public static void blind(View view) {
		new BlindAnimation().animate(view);
	}
	
	public static void blind(View view, int color, long duration) {
		new BlindAnimation(color, duration).animate(view);
	}
	
	public static void bounce(View view) {
		new BounceAnimation().animate(view);
	}
	
	public static void bounce(View view, float bounceDistance, int repetitions, long duration) {
		new BounceAnimation(bounceDistance, repetitions, duration).animate(view);
	}
	
	public static void clip(View view) {
		new ClipAnimation().animate(view);
	}
	
	public static void clip(View view, int color, long duration) {
		new ClipAnimation(color, duration).animate(view);
	}
	
	public static void explode(View view) {
		 new ExplodeAnimation().animate(view);
	}
	
	public static void explode(View view, int xParts, int yParts, long duration) {
		new ExplodeAnimation(xParts, yParts, duration).animate(view);
	}
	
	public static void highlight(View view) {
		new HighlightAnimation().animate(view);
	}
	
	public static void highlight(View view, int color, long duration) {
		new HighlightAnimation(color, duration).animate(view);
	}
	
	public static void pulsate(View view) {
		new PulsateAnimation().animate(view);
	}
	
	public static void scale(View view) {
		new ScaleAnimation().animate(view);
	}
	
	public static void scale(View view, int x, int y, long duration) {
		new ScaleAnimation(x, y, duration).animate(view);
	}
	
	public static void shake(View view) {
		new ShakeAnimation().animate(view);
	}
	
	public static void shake(View view, float shakeDistance, int repetitions, long duration) {
		new ShakeAnimation(shakeDistance, repetitions, duration).animate(view);
	}
	
	public static void slideOut(View view) {
		new SlideOutAnimation().animate(view);
	}
	
	public static void slideOut(View view, String direction, long duration) {
		new SlideOutAnimation(direction, duration).animate(view);
	}
	
	public static void transfer(View view, View destinationView, long duration) {
		new TransferAnimation(destinationView, duration).animate(view);
	}

}

 
