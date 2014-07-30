package com.two359media.animationsample;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidanimator.animation.Animation;
import com.androidanimator.animation.Animation.AnimationListener;
import com.androidanimator.animation.BlindAnimation;
import com.androidanimator.animation.BlinkAnimation;
import com.androidanimator.animation.BounceAnimation;
import com.androidanimator.animation.ExplodeAnimation;
import com.androidanimator.animation.FlipHorizontalAnimation;
import com.androidanimator.animation.FlipHorizontalToAnimation;
import com.androidanimator.animation.FlipVerticalAnimation;
import com.androidanimator.animation.FlipVerticalToAnimation;
import com.androidanimator.animation.HighlightAnimation;
import com.androidanimator.animation.PathAnimation;
import com.androidanimator.animation.RotationAnimation;
import com.androidanimator.animation.ShakeAnimation;
import com.androidanimator.animation.SlideInAnimation;
import com.androidanimator.animation.SlideInUnderneathAnimation;
import com.androidanimator.animation.SlideOutAnimation;
import com.androidanimator.animation.SlideOutUnderneathAnimation;
import com.androidanimator.animation.TransferAnimation;
import com.two359media.animationsample.dummy.DemoItem;

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
	private DemoItem.DummyItem mItem;
	private View mPlayView, mDestination;
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
			mItem = DemoItem.ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_animation_detail,
				container, false);

		initView(rootView);
		// AA.fadeIn(mParentView);
		if (mItem.id <= 5) {
			mImgTarget.setImageResource(R.drawable.img1);
		} else if (mItem.id > 5 && mItem.id <= 10) {
			mImgTarget.setImageResource(R.drawable.img2);
		} else if (mItem.id > 10 && mItem.id <= 20) {
			mImgTarget.setImageResource(R.drawable.img3);
		} else {
			mImgTarget.setImageResource(R.drawable.img4);
		}

		if (mItem.id == 9 || mItem.id == 14 || mItem.id == 17 || mItem.id == 21
				|| mItem.id == 22) {
			mImgTarget.setVisibility(View.INVISIBLE);
		}
		return rootView;
	}

	private void initView(View v) {
		mPlayView = v.findViewById(R.id.imgPlay);
		mImgTarget = (ImageView) v.findViewById(R.id.imgTarget);
		mPlayView.setOnClickListener(this);
		mImgBehind = (ImageView) v.findViewById(R.id.imgBehind);
		mDestination = v.findViewById(R.id.textView1);
		mImgBehind.setVisibility(View.INVISIBLE);
		if (mItem.id != 25) {
			mDestination.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(final View v) {
		mPlayView.setVisibility(View.INVISIBLE);
		doAnimation();
	}

	private void doAnimation() {
		switch (mItem.id) {
		case 1:
			new BlindAnimation().animate(mImgTarget);
			break;
		case 2:
			new BlinkAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 3:
			new BounceAnimation().setDuration(100)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate(mImgTarget);
			break;
		case 4:
			new ExplodeAnimation().animate(mImgTarget);
			break;
		case 5:
			new FlipHorizontalAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 6:
			new FlipHorizontalToAnimation().setFlipToView(mImgBehind).animate(
					mImgTarget);
			break;
		case 7:
			new FlipVerticalAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 8:
			new FlipVerticalToAnimation().setFlipToView(mImgBehind).animate(
					mImgTarget);
			break;
		case 9:
			// fly in
			break;
		case 10:
			// fly out
			break;
		case 11:
			// fold
			break;
		case 12:
			new HighlightAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 13:
			ArrayList<Point> points = new ArrayList<>();
			points.add(new Point(0, 100));
			points.add(new Point(50, 0));
			points.add(new Point(100, 100));
			points.add(new Point(0, 50));
			points.add(new Point(100, 50));
			points.add(new Point(0, 100));
			points.add(new Point(50, 50));
			new PathAnimation().setPoints(points)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate(mImgTarget);
			break;
		case 14:
			// puff in
			break;
		case 15:
			// puff out
			break;
		case 16:
			new RotationAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 17:
			// scale in
			break;
		case 18:
			// scale out
			break;
		case 19:
			new ShakeAnimation().setDuration(100)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate(mImgTarget);
			break;
		case 20:
			// size
			break;
		case 21:
			new SlideInAnimation().animate(mImgTarget);
			break;
		case 22:
			new SlideInUnderneathAnimation().animate(mImgTarget);
			break;
		case 23:
			new SlideOutAnimation().animate(mImgTarget);
			break;
		case 24:
			new SlideOutUnderneathAnimation().animate(mImgTarget);
			break;
		case 25:
			new TransferAnimation().setDestinationView(mDestination).animate(
					mImgTarget);
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
