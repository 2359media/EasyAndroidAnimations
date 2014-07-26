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
import com.androidanimator.animation.Animation.AnimationListener;
import com.androidanimator.animation.Constant;
import com.androidanimator.animation.PathAnimation;
import com.androidanimator.animation.RotationAnimation;
import com.two359media.animationsample.dummy.DummyContent;

/**
 * A fragment representing a single Animation detail screen. This fragment is
 * either contained in a {@link AnimationListActivity} in two-pane mode (on
 * tablets) or a {@link AnimationDetailActivity} on handsets.
 */
public class AnimationDetailFragment extends Fragment implements
		OnClickListener {
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
	private ImageView mImgTarget, mImgBehind;

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
			mItem = DummyContent.ITEM_MAP.get(getArguments()
					.getInt(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_animation_detail,
				container, false);

		initView(rootView);
		MyAnimator.fadeIn(mParentView);
		if (mItem.id <= 3) {
			mImgTarget.setImageResource(R.drawable.img1);
		} else if (mItem.id > 3 && mItem.id < 6) {
			mImgTarget.setImageResource(R.drawable.img2);
		} else if (mItem.id < 9) {
			mImgTarget.setImageResource(R.drawable.img3);
		} else if (mItem.id < 12) {
			mImgTarget.setImageResource(R.drawable.img4);
		} else {
			mImgTarget.setImageResource(R.drawable.img1);
		}

		if (mItem.id == 4 || mItem.id == 9 || mItem.id == 14 || mItem.id == 17 || mItem.id == 21 || mItem.id == 22) {
			mImgTarget.setVisibility(View.INVISIBLE);
		}
		return rootView;
	}

	private void initView(View v) {
		mParentView = v.findViewById(R.id.animation_detail);
		mPlayView = v.findViewById(R.id.imgPlay);
		mImgTarget = (ImageView) v.findViewById(R.id.imgTarget);
		mPlayView.setOnClickListener(this);
		mImgBehind = (ImageView) v.findViewById(R.id.imgBehind);
		mDestination = v.findViewById(R.id.textView1);
	}

	@Override
	public void onClick(final View v) {
		MyAnimator.fadeOut(mPlayView, 300, new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				mPlayView.setVisibility(View.GONE);
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
			MyAnimator.blink(mImgTarget, 5, Constant.DEFAULT_DURATION,
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 3:
			MyAnimator.bounce(mImgTarget, 10, 5, Constant.DEFAULT_DURATION,
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 4:
			MyAnimator.dropIn(mImgTarget, Constant.DEFAULT_DURATION, Constant.DIRECTION_UP, null);
			break;
		case 5:
			MyAnimator.dropOut(mImgTarget, Constant.DEFAULT_DURATION,
					Constant.DIRECTION_LEFT, null);
			break;
		case 6:
			MyAnimator.explode(mImgTarget, 3, 3, Constant.DEFAULT_DURATION,
					null);
			break;
		case 7:
			MyAnimator.flipOut(mImgTarget, Constant.HORIZONTAL,
					Constant.DEFAULT_DURATION, new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							MyAnimator.flipIn(mImgTarget, Constant.HORIZONTAL,
									Constant.DEFAULT_DURATION,
									new AnimationListener() {

										@Override
										public void onAnimationEnd(
												Animation animation) {
											mPlayView
													.setVisibility(View.VISIBLE);
											MyAnimator.fadeIn(mPlayView);
										}
									});
						}
					});
			break;
		case 8:
			mImgBehind.setVisibility(View.VISIBLE);
			MyAnimator.flipTogether(mImgTarget, mImgBehind, Constant.VERTICAL,
					Constant.DEFAULT_DURATION, new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							// swap
							ImageView temp = mImgBehind;
							mImgBehind = mImgTarget;
							mImgTarget = temp;
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 9:
			MyAnimator.flyIn(mImgTarget, Constant.DEFAULT_DURATION,
					Constant.DIRECTION_UP, null);
			break;
		case 10:
			MyAnimator.flyOut(mImgTarget, Constant.DEFAULT_DURATION,
					Constant.DIRECTION_UP, null);
			break;
		case 11:
			MyAnimator.fold(mImgTarget);
			break;
		case 12:
			MyAnimator.highlight(mImgTarget, Color.YELLOW,
					Constant.DEFAULT_DURATION, new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 13:
			final ArrayList<Point> points = new ArrayList<>();
			points.add(new Point(50, 0));
			points.add(new Point(100, 100));
			points.add(new Point(0, 50));
			points.add(new Point(100, 50));
			points.add(new Point(0, 100));
			points.add(new Point(50, 50));
			MyAnimator.path(mImgTarget, points, PathAnimation.ANCHOR_CENTER,
					Constant.DEFAULT_DURATION, new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 14:
			MyAnimator.puffIn(mImgTarget, Constant.DEFAULT_DURATION, null);
			break;
		case 15:
			MyAnimator.puffOut(mImgTarget, Constant.DEFAULT_DURATION, null);
			break;
		case 16:
			MyAnimator.rotate(mImgTarget, 360,
					RotationAnimation.PIVOT_TOP_LEFT,
					Constant.DEFAULT_DURATION, new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 17:
			MyAnimator.scaleIn(mImgTarget, Constant.DEFAULT_DURATION, null);
			break;
		case 18:
			MyAnimator.scaleOut(mImgTarget, Constant.DEFAULT_DURATION, null);
			break;
		case 19:
			MyAnimator.shake(mImgTarget, 10, 5, Constant.DEFAULT_DURATION,
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
							MyAnimator.fadeIn(mPlayView);
						}
					});
			break;
		case 20:
			MyAnimator.size(mImgTarget);
			break;
		case 21:
			MyAnimator.slideIn(mImgTarget, Constant.DIRECTION_UP,
					Constant.DEFAULT_DURATION, null);
			break;
		case 22:
			MyAnimator.slideInUnderneath(mImgTarget, Constant.DIRECTION_UP,
					Constant.DEFAULT_DURATION, null);
			break;
		case 23:
			MyAnimator.slideOut(mImgTarget, Constant.DIRECTION_UP,
					Constant.DEFAULT_DURATION, null);
			break;
		case 24:
			MyAnimator.slideOutUnderneath(mImgTarget, Constant.DIRECTION_UP,
					Constant.DEFAULT_DURATION, null);
			break;
		case 25:
			MyAnimator.transfer(mImgTarget, mDestination,
					Constant.DEFAULT_DURATION, null);
			break;
		default:
			break;
		}
	}

	/**
	 * This is to prevent any padding from obstructing the animations.
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ViewGroup vg = (ViewGroup) view;
		// vg.setClipChildren(false);
		vg.setClipToPadding(false);
	}
}
