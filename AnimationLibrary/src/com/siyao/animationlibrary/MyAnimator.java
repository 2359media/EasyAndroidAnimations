package com.siyao.animationlibrary;
import android.view.View;


public class MyAnimator {
	
	public static void animate(View view, Animation animation) {
		animation.animate(view);
	}
	
	public static void explode(View v) {
		 new ExplodeAnimation().animate(v);
	}
	
	
	
	public static void explode(View v,int xParts, int yParts,long duration) {
		new ExplodeAnimation(xParts, yParts, duration).animate(v);
	}
	
	public static void highlight(View v){
		new HighlightAnimation().animate(v);
	}

}

 
