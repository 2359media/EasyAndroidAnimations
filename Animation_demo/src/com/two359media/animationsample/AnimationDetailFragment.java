package com.two359media.animationsample;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidanimator.MyAnimator;
import com.androidanimator.animation.Animation;
import com.androidanimator.animation.PathAnimation;
import com.androidanimator.animation.Animation.AnimationListener;
import com.androidanimator.animation.Constant;
import com.two359media.animationsample.dummy.DummyContent;

/**
 * A fragment representing a single Animation detail screen. This fragment is
 * either contained in a {@link AnimationListActivity} in two-pane mode (on
 * tablets) or a {@link AnimationDetailActivity} on handsets.
 */
public class AnimationDetailFragment extends Fragment implements OnClickListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    private View mParentView, mPlayView, mDestination;
    private ImageView mImgTarget;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AnimationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation_detail, container, false);

        initView(rootView);
        MyAnimator.fadeIn(mParentView);
        if (mItem.id != 14) {
            mDestination.setVisibility(View.GONE);
        }
        if (mItem.id <= 3) {
            mImgTarget.setBackgroundResource(R.drawable.img1);
        } else if (mItem.id > 3 && mItem.id < 6) {
            mImgTarget.setBackgroundResource(R.drawable.img2);
        } else if (mItem.id < 9) {
            mImgTarget.setBackgroundResource(R.drawable.img3);
        } else if (mItem.id < 12) {
            mImgTarget.setBackgroundResource(R.drawable.img4);
        } else {
            mImgTarget.setBackgroundResource(R.drawable.img5);
        }
        return rootView;
    }

    private void initView(View v) {
        mParentView = v.findViewById(R.id.animation_detail);
        mPlayView = v.findViewById(R.id.imgPlay);
        mImgTarget = (ImageView) v.findViewById(R.id.imgTarget);
        mDestination = v.findViewById(R.id.textView1);
        mPlayView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyAnimator.fadeOut(mPlayView, 300, new AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                doAnimation();
            }
        });
    }

    private void doAnimation() {
        switch (mItem.id) {
        case 1:
            MyAnimator.blind(mImgTarget);
            break;
        case 2:
            MyAnimator.shake(mImgTarget, 10, 10, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.fadeIn(mPlayView);
                }
            });
            break;
        case 3:
            MyAnimator.dropOut(mImgTarget, 300, Constant.DIRECTION_LEFT, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.dropIn(mImgTarget, 300, Constant.DIRECTION_LEFT, new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            MyAnimator.fadeIn(mPlayView);
                        }
                    });
                }
            });
            break;
        case 4:
            MyAnimator.explode(mImgTarget);
            break;
        case 5:
            MyAnimator.flyOut(mImgTarget, 300, Constant.DIRECTION_RIGHT, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.flyIn(mImgTarget, 300, Constant.DIRECTION_RIGHT, new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            MyAnimator.fadeIn(mPlayView);
                        }
                    });
                }
            });
            break;
        case 6:
            MyAnimator.fold(mImgTarget);
            break;
        case 7:
            MyAnimator.highlight(mImgTarget, Color.YELLOW, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.fadeIn(mPlayView);
                }
            });
            break;
        case 8:
        	final ArrayList<Point> points = new ArrayList<>();
			points.add(new Point(50, 0));
			points.add(new Point(100, 100));
			points.add(new Point(0, 50));
			points.add(new Point(100, 50));
			points.add(new Point(0, 100));
            MyAnimator.path(mImgTarget, points, PathAnimation.ANCHOR_CENTER, 500, new AnimationListener() {
				
				@Override
				public void onAnimationEnd(Animation animation) {
					points.clear();
					points.add(new Point(50, 50));
					MyAnimator.path(mImgTarget, points, PathAnimation.ANCHOR_CENTER, 300, new AnimationListener() {
						
						@Override
						public void onAnimationEnd(Animation animation) {
							MyAnimator.fadeIn(mPlayView);
						}
					});
				}
			});
            break;
        case 9:
            MyAnimator.puffOut(mImgTarget, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.puffIn(mImgTarget, 300, new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            MyAnimator.fadeIn(mPlayView);
                        }
                    });
                }
            });
            break;
        case 10:
            MyAnimator.pulsate(mImgTarget, 3, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.fadeIn(mPlayView);
                }
            });
            break;
        case 11:
            MyAnimator.scaleOut(mImgTarget, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.scaleIn(mImgTarget, 300, new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            MyAnimator.fadeIn(mPlayView);
                        }
                    });
                }
            });
            break;
        case 12:
            MyAnimator.size(mImgTarget);
            break;
        case 13:
        	MyAnimator.slideIn(mImgTarget, Constant.DIRECTION_UP, 300, new AnimationListener() {
				
				@Override
				public void onAnimationEnd(Animation animation) {
					MyAnimator.fadeIn(mPlayView);
				}
			});
        	break;
        case 14:
        	MyAnimator.slideOut(mImgTarget, Constant.DIRECTION_UP, 300, new AnimationListener() {
				
				@Override
				public void onAnimationEnd(Animation animation) {
					MyAnimator.fadeIn(mPlayView);
				}
			});
        case 15:
            MyAnimator.slideOutUnderneath(mImgTarget, Constant.DIRECTION_UP, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.fadeIn(mPlayView);

                }
            });
            break;
        case 16:
            MyAnimator.transfer(mImgTarget, mDestination, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.fadeIn(mPlayView);

                }
            });
            break;
        case 17:
            MyAnimator.flipOut(mImgTarget, Constant.HORIZONTAL, 300, new AnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    MyAnimator.flipIn(mImgTarget, Constant.HORIZONTAL, 300, new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            MyAnimator.fadeIn(mPlayView);
                        }
                    });
                }
            });
            break;
        default:
            break;
        }
    }
}
