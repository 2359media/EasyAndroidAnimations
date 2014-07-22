package com.media2359.animation.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.media2359.animation.libs.Animation;
import com.media2359.animation.libs.BlindAnimation;
import com.media2359.animation.libs.BounceAnimation;
import com.media2359.animation.libs.ClipAnimation;
import com.media2359.animation.libs.Constant;
import com.media2359.animation.libs.DropAnimation;
import com.media2359.animation.libs.ExplodeAnimation;
import com.media2359.animation.libs.FadeAnimation;
import com.media2359.animation.libs.FoldAnimation;
import com.media2359.animation.libs.MyAnimation;
import com.media2359.animation.libs.PuffAnimation;
import com.media2359.animation.libs.PulstateAnimation;
import com.media2359.animation.libs.ScaleAnimation;
import com.media2359.animation.libs.SizeAnimation;
import com.media2359.animation.libs.TransferAnimation;

public class SampleActivity extends Activity {
    Button btnBlind, btnClip, btnDrop, btnFade, btnPuff, btnPulstate, btnScale, btnSize, btnTransfer, btnCancel;
    Button btnFold, btnBounce, btn_explode;
    View animationView;
    Animation animation;
    Context mContext;
    RadioGroup rgDirection;
    Spinner spType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mContext = this;
        initView();
        initListener();
    }

    private OnClickListener onPerformanceAnimation() {
        return new OnClickListener() {

            @Override
            public void onClick(View view) {
                View v = animationView;
                int type = spType.getSelectedItemPosition();
                if (type == 0) {
                    type = Constant.IN;
                } else {
                    type = Constant.OUT;
                }
                int direction = getDirection();
                switch (view.getId()) {
                case R.id.btn_action:
                    MyAnimation.Blind(mContext, v);
                    break;
                case R.id.btn_clip:
                    MyAnimation.clip(v, Constant.DEFAULT_DURATION, Constant.VERTICAL, null);
                    break;
                case R.id.btn_fade:
                    // TODO
                    if (type == Constant.IN) {
                        // MyAnimation.
                    }
                    break;
                case R.id.btn_puff:
                    if (type == Constant.IN)
                        MyAnimation.puffIn(v, Constant.DEFAULT_DURATION, null);
                    else
                        MyAnimation.puffOut(v);
                    break;
                case R.id.btn_pul:
                    MyAnimation.Pulstate(v);
                    break;
                case R.id.btn_scale:
                    if (type == Constant.IN)
                        MyAnimation.scaleIn(v);
                    else
                        MyAnimation.scaleOut(v);
                    break;
                case R.id.btn_size:
                    MyAnimation.size(v);
                    break;
                case R.id.btn_transfer:
                    if (type == Constant.IN)
                        MyAnimation.transferIn(v, Constant.DEFAULT_DURATION, direction, null);
                    else
                        MyAnimation.transferOut(v, Constant.DEFAULT_DURATION, direction, null);
                    break;
                case R.id.btn_drop:
                    if (type == Constant.IN)
                        MyAnimation.dropIn(v, Constant.DEFAULT_DURATION, direction, null);
                    else
                        MyAnimation.dropOut(v, Constant.DEFAULT_DURATION, direction, null);
                    break;
                case R.id.btn_fold:
                    MyAnimation.fold(mContext, v);
                    break;
                case R.id.btn_bounce:
                    MyAnimation.bounce(v, Constant.DEFAULT_DURATION, Constant.HORIZONTAL, 10, null);
                    break;
                case R.id.btn_explode:
                    MyAnimation.explode(mContext, v);
                default:
                    break;
                }

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
        btn_explode = (Button) findViewById(R.id.btn_explode);
        rgDirection = (RadioGroup) findViewById(R.id.groupDirection);
        spType = (Spinner) findViewById(R.id.spinner1);
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
        btn_explode.setOnClickListener(onPerformanceAnimation());
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleActivity.this, SampleActivity.class));
                finish();
            }
        });

    }

    private int getDirection() {
        int id = rgDirection.getCheckedRadioButtonId();
        switch (id) {
        case R.id.radioUp:
            return Constant.DIRECTION_UP;
        case R.id.radioDown:
            return Constant.DIRECTION_DOWN;
        case R.id.radioLeft:
            return Constant.DIRECTION_LEFT;
        case R.id.radioRight:
            return Constant.DIRECTION_RIGHT;

        default:
            break;
        }
        return Constant.DIRECTION_LEFT;
    }

}
