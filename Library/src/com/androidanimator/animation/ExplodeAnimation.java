package com.androidanimator.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * @author phutang
 *
 */
public class ExplodeAnimation extends Animation {
    AnimatorSet animExplode = new AnimatorSet();
    View child;
    GridLayout animationLayout;
    LayoutParams originalParam;
    Context mContext;
    ImageView img1_1, img1_2, img2_1, img2_2;

    public ExplodeAnimation(Context context) {
        animExplode = new AnimatorSet();
        mContext = context;
    }
    
    public ExplodeAnimation(AnimationListener listener, long duration, Context mContext) {
        super(listener, duration);
        this.mContext = mContext;
        animExplode = new AnimatorSet();
    }



    @Override
    public void animate(View v) {
        getAnimatorSet(v);

        animExplode.start();
    }

    private void moveImage(float distanceX, float distanceY) {
        AnimatorSet anim1_1 = new AnimatorSet();
        ObjectAnimator moveLeft1_1 = ObjectAnimator.ofFloat(img1_1, Constant.TRANSLATION_X, 0, -distanceX);
        ObjectAnimator moveUp1_1 = ObjectAnimator.ofFloat(img1_1, Constant.TRANSLATION_Y, 0, -distanceY);
        anim1_1.playTogether(moveLeft1_1, moveUp1_1);

        AnimatorSet anim2_1 = new AnimatorSet();
        ObjectAnimator moveRight2_1 = ObjectAnimator.ofFloat(img2_1, Constant.TRANSLATION_X, 0, distanceX);
        ObjectAnimator moveUp2_1 = ObjectAnimator.ofFloat(img2_1, Constant.TRANSLATION_Y, 0, -distanceY);
        anim2_1.playTogether(moveRight2_1, moveUp2_1);

        AnimatorSet anim1_2 = new AnimatorSet();
        ObjectAnimator moveLeft1_2 = ObjectAnimator.ofFloat(img1_2, Constant.TRANSLATION_X, 0, -distanceX);
        ObjectAnimator moveDown1_2 = ObjectAnimator.ofFloat(img1_2, Constant.TRANSLATION_Y, 0, distanceY);
        anim1_2.playTogether(moveLeft1_2, moveDown1_2);

        AnimatorSet anim2_2 = new AnimatorSet();
        ObjectAnimator moveRight2_2 = ObjectAnimator.ofFloat(img2_2, Constant.TRANSLATION_X, 0, distanceX);
        ObjectAnimator moveDown2_2 = ObjectAnimator.ofFloat(img2_2, Constant.TRANSLATION_Y, 0, distanceY);
        anim2_2.playTogether(moveRight2_2, moveDown2_2);

        AnimatorSet animLayout = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_X, 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout, Constant.SCALE_Y, 1f, 2f);
        animLayout.playTogether(scaleX, scaleY);
        animExplode.playTogether(anim1_1, anim1_2, anim2_1, anim2_2, animLayout);
        if(getListener()!=null){
            animExplode.addListener(new AnimatorListener() {
                
                @Override
                public void onAnimationStart(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onAnimationRepeat(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onAnimationEnd(Animator animation) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onAnimationCancel(Animator animation) {
                    getListener().onAnimationEnd(ExplodeAnimation.this);
                }
            });
        }
    }


    public void addToAnimatioView(View v) {
        child = v;
        child.setDrawingCacheEnabled(true);
        Bitmap bmp = child.getDrawingCache();

        int splitYCoord = bmp.getHeight() / 2;
        int splitXCoord = bmp.getWidth() / 2;

        Bitmap mBmp1_1 = Bitmap.createBitmap(bmp, 0, 0, splitXCoord, splitYCoord);
        Bitmap mBmp1_2 = Bitmap.createBitmap(bmp, 0, splitYCoord, splitXCoord, splitYCoord);

        Bitmap mBmp2_1 = Bitmap.createBitmap(bmp, splitXCoord, 0, splitXCoord, splitYCoord);
        Bitmap mBmp2_2 = Bitmap.createBitmap(bmp, splitXCoord, splitYCoord, splitXCoord, splitYCoord);

        img1_1 = new ImageView(mContext);
        img1_1.setImageBitmap(mBmp1_1);

        img1_2 = new ImageView(mContext);
        img1_2.setImageBitmap(mBmp1_2);

        img2_1 = new ImageView(mContext);
        img2_1.setImageBitmap(mBmp2_1);

        img2_2 = new ImageView(mContext);
        img2_2.setImageBitmap(mBmp2_2);

        ViewGroup parent = (ViewGroup) v.getParent();
        parent.removeView(v);
        originalParam = v.getLayoutParams();
        v.setLayoutParams(originalParam);
        animationLayout = new GridLayout(mContext);
        animationLayout.setColumnCount(2);
        animationLayout.setRowCount(2);
        animationLayout.setId(v.getId());
        animationLayout.setLayoutParams(originalParam);

        animationLayout.addView(img1_1, 0);
        animationLayout.addView(img2_1, 1);
        animationLayout.addView(img1_2, 2);
        animationLayout.addView(img2_2, 3);
        parent.addView(animationLayout);
    }

    @Override
    public AnimatorSet getAnimatorSet(View v) {
        float distanceX = v.getWidth() / 2;
        float distanceY = v.getHeight() / 2;
        addToAnimatioView(v);

        moveImage(distanceX, distanceY);
        return animExplode;
    }
}
