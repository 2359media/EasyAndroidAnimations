package com.siyao.animationlibrary;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TransferAnimation extends Animation {

	public TransferAnimation() {
		
	}
	
	@Override
	public void animate(View view) {
		ViewGroup parentView = (ViewGroup) view.getParent();
		FrameLayout transferFrame = new FrameLayout(view.getContext());
		ImageView transferBox = new ImageView(view.getContext());
		LayoutParams layoutParams = view.getLayoutParams();
		transferFrame.setLayoutParams(layoutParams);
		transferBox.setLayoutParams(layoutParams);
		transferBox.setBackgroundColor(Color.TRANSPARENT);
		
		parentView.removeView(view);
		transferFrame.addView(view);
		transferFrame.addView(transferBox);
		parentView.addView(transferFrame);
	}

}
