package com.media2359.animation.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Transformation;
import android.widget.Button;

import com.media2359.animation.libs.Animation;
import com.media2359.animation.libs.BlindAnimation;
import com.media2359.animation.libs.BounceAnimation;
import com.media2359.animation.libs.ClipAnimation;
import com.media2359.animation.libs.Constant;
import com.media2359.animation.libs.DropAnimation;
import com.media2359.animation.libs.FadeAnimation;
import com.media2359.animation.libs.FoldAnimation;
import com.media2359.animation.libs.PuffAnimation;
import com.media2359.animation.libs.PulstateAnimation;
import com.media2359.animation.libs.ScaleAnimation;
import com.media2359.animation.libs.SizeAnimation;
import com.media2359.animation.libs.TransferAnimation;

public class SampleActivity extends Activity {
    Button btnBlind, btnClip, btnDrop, btnFade, btnPuff, btnPulstate, btnScale, btnSize, btnTransfer, btnCancel;
    Button btnFold, btnBounce;
    View animationView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        initView();
        initListener();
    }

    private OnClickListener onPerformanceAnimation() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                case R.id.btn_action:
                    animation = new BlindAnimation();
                    break;
                case R.id.btn_clip:
                    animation = new ClipAnimation();
                    break;
                case R.id.btn_fade:
                    animation = new FadeAnimation();
                    break;
                case R.id.btn_puff:
                    animation = new PuffAnimation();
                    break;
                case R.id.btn_pul:
                    animation = new PulstateAnimation();
                    break;
                case R.id.btn_scale:
                    animation = new ScaleAnimation();
                    break;
                case R.id.btn_size:
                    animation = new SizeAnimation();
                    break;
                case R.id.btn_transfer:
                    animation = new TransferAnimation();
                    break;
                case R.id.btn_drop:
                    animation = new DropAnimation();
                    break;
                case R.id.btn_fold:
                    animation = new FoldAnimation();
                    break;
                case R.id.btn_bounce:
                    animation = new BounceAnimation();
                    animation.putProperty(Constant.PROPERTY_ORITENTION, Constant.VERTICAL);
                    break;
                default:
                    break;
                }
                animation.performAnimation(animationView);

            }
        };
    }

    private void initView() {
        btnBlind = (Button) findViewById(R.id.btn_action);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        animationView = findViewById(R.id.textView1);
        btnClip = (Button) findViewById(R.id.btn_clip);
        btnDrop = (Button) findViewById(R.id.btn_drop);
        btnFade = (Button) findViewById(R.id.btn_fade);
        btnPuff = (Button) findViewById(R.id.btn_puff);
        btnPulstate = (Button) findViewById(R.id.btn_pul);
        btnScale = (Button) findViewById(R.id.btn_scale);
        btnSize = (Button) findViewById(R.id.btn_size);
        btnTransfer = (Button) findViewById(R.id.btn_transfer);
        btnFold = (Button) findViewById(R.id.btn_fold);
        btnBounce = (Button) findViewById(R.id.btn_bounce);
    }

    private void initListener() {
        btnBlind.setOnClickListener(onPerformanceAnimation());
        btnClip.setOnClickListener(onPerformanceAnimation());
        btnDrop.setOnClickListener(onPerformanceAnimation());
        btnFade.setOnClickListener(onPerformanceAnimation());
        btnPuff.setOnClickListener(onPerformanceAnimation());
        btnPulstate.setOnClickListener(onPerformanceAnimation());
        btnScale.setOnClickListener(onPerformanceAnimation());
        btnSize.setOnClickListener(onPerformanceAnimation());
        btnTransfer.setOnClickListener(onPerformanceAnimation());
        btnFold.setOnClickListener(onPerformanceAnimation());
        btnBounce.setOnClickListener(onPerformanceAnimation());

        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                animation.reset(animationView);
            }
        });

    }
}
