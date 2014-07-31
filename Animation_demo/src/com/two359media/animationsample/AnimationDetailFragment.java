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
import com.androidanimator.animation.FoldAnimation;
import com.androidanimator.animation.HighlightAnimation;
import com.androidanimator.animation.PathAnimation;
import com.androidanimator.animation.PuffInAnimation;
import com.androidanimator.animation.PuffOutAnimation;
import com.androidanimator.animation.RotationAnimation;
import com.androidanimator.animation.ScaleInAnimation;
import com.androidanimator.animation.ScaleOutAnimation;
import com.androidanimator.animation.ShakeAnimation;
import com.androidanimator.animation.SizeAnimation;
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
		if (mItem.id <= 5) {
			mImgTarget.setImageResource(R.drawable.img1);
		} else if (mItem.id > 5 && mItem.id <= 10) {
			mImgTarget.setImageResource(R.drawable.img2);
		} else if (mItem.id > 10 && mItem.id <= 20) {
			mImgTarget.setImageResource(R.drawable.img3);
		} else {
			mImgTarget.setImageResource(R.drawable.img4);
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
		if (mItem.id == 12 || mItem.id == 15 || mItem.id == 19
				|| mItem.id == 20) {
			mImgTarget.setVisibility(View.INVISIBLE);
		}
		if (mItem.id != 23) {
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
			new FlipHorizontalToAnimation().setFlipToView(mImgBehind)
					.setDuration(300).animate(mImgTarget);
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
			new FlipVerticalToAnimation().setFlipToView(mImgBehind)
					.setDuration(300).animate(mImgTarget);
			break;
		case 9:
			new FoldAnimation().animate(mImgTarget);
			break;
		case 10:
			new HighlightAnimation().setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate(mImgTarget);
			break;
		case 11:
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
		case 12:
			new PuffInAnimation().animate(mImgTarget);
			break;
		case 13:
			new PuffOutAnimation().animate(mImgTarget);
			break;
		case 14:
			new RotationAnimation().setPivot(RotationAnimation.PIVOT_TOP_LEFT)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate(mImgTarget);
			break;
		case 15:
			new ScaleInAnimation().animate(mImgTarget);
			break;
		case 16:
			new ScaleOutAnimation().animate(mImgTarget);
			break;
		case 17:
			new ShakeAnimation().setDuration(100)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate(mImgTarget);
			break;
		case 18:
			new SizeAnimation().animate(mImgTarget);
			break;
		case 19:
			new SlideInAnimation().setDirection(Animation.DIRECTION_UP)
					.animate(mImgTarget);
			break;
		case 20:
			new SlideInUnderneathAnimation().setDirection(
					Animation.DIRECTION_DOWN).animate(mImgTarget);
			break;
		case 21:
			new SlideOutAnimation().setDirection(Animation.DIRECTION_LEFT)
					.animate(mImgTarget);
			break;
		case 22:
			new SlideOutUnderneathAnimation().setDirection(
					Animation.DIRECTION_RIGHT).animate(mImgTarget);
			break;
		case 23:
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
