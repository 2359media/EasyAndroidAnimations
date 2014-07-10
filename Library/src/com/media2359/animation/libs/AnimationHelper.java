package com.media2359.animation.libs;

import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class AnimationHelper {

	public static final int ANIM_DEFAULT_DURATION = 350;
	
	public static final String ANIM_PROPERTY_ALPHA = "alpha";
	public static final String ANIM_PROPERTY_TRANSLATION_Y = "translationY";
	public static final String ANIM_PROPERTY_SCALE_X = "scaleX";
	
	public static void startAnimations(int duration, int delay, ObjectAnimator... animators) {
		for (ObjectAnimator anim : animators) {
			startAnim(anim, duration, delay);
		}
	}
	
	public static void startAnim(ValueAnimator anim, AnimatorListener animListener, int duration, int delay) {
		if (animListener != null) {
			anim.addListener(animListener);
		}
		startAnim(anim, duration, delay);
	}
	
	public static void startAnim(ValueAnimator anim, int duration, int delay) {
		anim.setDuration(duration);
		anim.setStartDelay(delay);
		anim.start();
	}

	public static void showViewTopDownBelongItsHeight(View v, int duration, int delay) {
		startAnimations(duration, delay,
				ObjectAnimator.ofFloat(v, ANIM_PROPERTY_ALPHA, 0.0f, 1.0f),
				ObjectAnimator.ofFloat(v, ANIM_PROPERTY_TRANSLATION_Y, - v.getHeight(), 0.0f)
				);
	}

	public static void hideViewTopDownBelongItsHeight(TextView v, int duration, int delay) {
		startAnimations(duration, delay,
				ObjectAnimator.ofFloat(v, ANIM_PROPERTY_ALPHA, 1.0f, 0.0f),
				ObjectAnimator.ofFloat(v, ANIM_PROPERTY_TRANSLATION_Y, 0, v.getHeight())
		);
	}

	public static void scaleViewX(View v, float rateWidth, int duration, int delay, AnimatorListener animListener) {
		startAnim(ObjectAnimator.ofFloat(v, ANIM_PROPERTY_SCALE_X, rateWidth), animListener, duration, delay);
	}

	public static void scaleViewX(View v, float rateWidth, int duration, int delay) {
		startAnim(ObjectAnimator.ofFloat(v, ANIM_PROPERTY_SCALE_X, rateWidth), duration, delay);
	}
	
	public static void scaleViewWidth(final View v, int width, int duration, int delay, final Runnable onUpdateAnim) {
		Log.d("lanna", "scaleViewWidth w=" + v.getWidth() + " to width=" + width);
//		ValueAnimator anim = ValueAnimator.ofInt(v.getMeasuredWidth(), width);
		ObjectAnimator anim = ObjectAnimator.ofFloat(v, ANIM_PROPERTY_TRANSLATION_Y, 0, width);
	    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	        @Override
	        public void onAnimationUpdate(ValueAnimator valueAnimator) {
	            float width = (Float) valueAnimator.getAnimatedValue();
//	            ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
//	            layoutParams.width = width;
//	            v.setLayoutParams(layoutParams);
				Log.i("lanna", "onAnimationUpdate w=" + v.getWidth() + " to anim width=" + width);
	            if (onUpdateAnim != null) {
	            	onUpdateAnim.run();
	            }
	        }
	    });
	    startAnim(anim, duration, delay);
	}
	
	public static void animProgressSeekbar(final SeekBar seekBar, final int progressStart, final int progressEnd, 
			int duration, int delay, final AnimatorListener animListener, final Runnable onUpdateAnim) {
		seekBar.setProgress(progressStart);
		ValueAnimator anim = ValueAnimator.ofInt(progressStart, progressEnd); 
		anim.setDuration(duration);
		anim.setStartDelay(delay);
		if (animListener != null) {
			anim.addListener(animListener);
		}
		anim.addUpdateListener(new AnimatorUpdateListener() {

		        @Override
		        public void onAnimationUpdate(ValueAnimator animation) {
		            int progressEnd = (Integer) animation.getAnimatedValue();
		            seekBar.setProgress(progressEnd);
		            if (onUpdateAnim != null) {
		            	onUpdateAnim.run();
		            }
		        }
		    });
		anim.start();
	}
}
