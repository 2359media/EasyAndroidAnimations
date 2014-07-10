package com.media2359.animation.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.media2359.animation.libs.AnimationLibrary;
import com.media2359.animation.libs.ClipAnimation;
import com.media2359.animation.libs.DropAnimation;
import com.media2359.animation.libs.ScaleAnimation;

public class SampleActivity extends Activity {
    Button btnAction, btnCancel;
    View animationView;
    AnimationLibrary animationLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        btnAction = (Button) findViewById(R.id.btn_action);
        btnCancel=(Button) findViewById(R.id.btn_cancel);
        animationView = findViewById(R.id.textView1);
        animationLibrary = new ScaleAnimation();
        btnAction.setOnClickListener(onPerformanceAnimation());
        
        btnCancel.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                animationLibrary.cancel(animationView);
            }
        });
    }

    private OnClickListener onPerformanceAnimation() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                // ObjectAnimator anim = ObjectAnimator.ofFloat(animationView,
                // "y", 0, 270);
                // anim.setRepeatMode(Animation.REVERSE);
                // anim.setDuration(1000);
                // anim.start();

                animationLibrary.performAnimation(animationView);

                // TranslateAnimation animate = new
                // TranslateAnimation(0,-animationView.getWidth(),0,0);
                // animate.setDuration(500);
                // animate.setFillAfter(true);
                // animationView.startAnimation(animate);
                // animationView.setVisibility(View.GONE);
            }
        };
    }
}
