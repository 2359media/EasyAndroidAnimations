package com.androidanimator.demo;

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
import com.androidanimator.animation.AnimationListener;
import com.androidanimator.animation.BlindAnimation;
import com.androidanimator.animation.BlinkAnimation;
import com.androidanimator.animation.BounceAnimation;
import com.androidanimator.animation.CombinableAnimations;
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
import com.androidanimator.animation.SlideInAnimation;
import com.androidanimator.animation.SlideInUnderneathAnimation;
import com.androidanimator.animation.SlideOutAnimation;
import com.androidanimator.animation.SlideOutUnderneathAnimation;
import com.androidanimator.animation.TransferAnimation;
import com.androidanimator.demo.model.DemoItem;
import com.two359media.animationsample.R;

/**
 * This is a fragment representing a single Animation detail screen which shows
 * off all the possible animations available in the AndroidAnimator library.
 * This fragment is either contained in {@link AnimationListActivity} in
 * two-pane mode (on tablets) or {@link AnimationDetailActivity} (on phones).
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

		mPlayView = rootView.findViewById(R.id.imgPlay);
		mImgTarget = (ImageView) rootView.findViewById(R.id.imgTarget);
		mPlayView.setOnClickListener(this);
		mImgBehind = (ImageView) rootView.findViewById(R.id.imgBehind);
		mDestination = rootView.findViewById(R.id.textView1);
		mImgBehind.setVisibility(View.INVISIBLE);

		// Differentiates the images for all animations
		if (mItem.id <= 5) {
			mImgTarget.setImageResource(R.drawable.img1);
		} else if (mItem.id > 5 && mItem.id <= 10) {
			mImgTarget.setImageResource(R.drawable.img2);
		} else if (mItem.id > 10 && mItem.id <= 20) {
			mImgTarget.setImageResource(R.drawable.img3);
		} else {
			mImgTarget.setImageResource(R.drawable.img4);
		}

		mPlayView.setLayoutParams(mImgTarget.getLayoutParams());

		// Scales the image smaller for PathAnimation
		if (mItem.id == 11) {
			mImgTarget.setScaleX(0.5f);
			mImgTarget.setScaleY(0.5f);
		}

		// Sets view to <code>View.INVISIBLE</code> for entrance animations
		if (mItem.id == 0 || mItem.id == 12 || mItem.id == 15 || mItem.id == 18
				|| mItem.id == 19 || mItem.id == 23) {
			mImgTarget.setVisibility(View.INVISIBLE);
		}

		// Sets destination view to <code>View.VISIBLE</code> only for
		// TransferAnimation
		if (mItem.id != 22) {
			mDestination.setVisibility(View.INVISIBLE);
		}

		return rootView;
	}

	@Override
	public void onClick(final View v) {
		// Sets play button to <code>View.INVISIBLE</code> before starting
		// animation
		mPlayView.setVisibility(View.INVISIBLE);
		doAnimation();
	}

	/**
	 * This method performs the various animations available in the
	 * AndroidAnimator library.
	 */
	private void doAnimation() {
		switch (mItem.id) {
		case 0:
			new CombinableAnimations()
					.add(new FlipHorizontalAnimation(mImgTarget)
							.setDuration(1000))
					.add(new FlipVerticalAnimation(mImgTarget)
							.setDuration(1000))
					.add(new ScaleInAnimation(mImgTarget).setDuration(1000)
							.setListener(new AnimationListener() {

								@Override
								public void onAnimationEnd(Animation animation) {
									new CombinableAnimations()
											.add(new BounceAnimation(mImgTarget)
													.setDuration(1000))
											.add(new SlideOutAnimation(
													mImgTarget)
													.setDuration(1000))
											.animate();
								}
							})).animate();
			break;
		case 1:
			new BlindAnimation(mImgTarget).animate();
			break;
		case 2:
			new BlinkAnimation(mImgTarget).setListener(new AnimationListener() {

				@Override
				public void onAnimationEnd(Animation animation) {
					mPlayView.setVisibility(View.VISIBLE);
				}
			}).animate();
			break;
		case 3:
			new BounceAnimation(mImgTarget).setDuration(100)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 4:
			new ExplodeAnimation(mImgTarget).animate();
			break;
		case 5:
			new FlipHorizontalAnimation(mImgTarget).setListener(
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 6:
			new FlipHorizontalToAnimation(mImgTarget).setFlipToView(mImgBehind)
					.setDuration(300).animate();
			break;
		case 7:
			new FlipVerticalAnimation(mImgTarget).setListener(
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 8:
			new FlipVerticalToAnimation(mImgTarget).setFlipToView(mImgBehind)
					.setDuration(300).animate();
			break;
		case 9:
			new FoldAnimation(mImgTarget).animate();
			break;
		case 10:
			new HighlightAnimation(mImgTarget).setListener(
					new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
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
			new PathAnimation(mImgTarget).setPoints(points).setDuration(2000)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 12:
			new PuffInAnimation(mImgTarget).animate();
			break;
		case 13:
			new PuffOutAnimation(mImgTarget).animate();
			break;
		case 14:
			new RotationAnimation(mImgTarget)
					.setPivot(RotationAnimation.PIVOT_TOP_LEFT)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 15:
			new ScaleInAnimation(mImgTarget).animate();
			break;
		case 16:
			new ScaleOutAnimation(mImgTarget).animate();
			break;
		case 17:
			new ShakeAnimation(mImgTarget).setDuration(100)
					.setListener(new AnimationListener() {

						@Override
						public void onAnimationEnd(Animation animation) {
							mPlayView.setVisibility(View.VISIBLE);
						}
					}).animate();
			break;
		case 18:
			new SlideInAnimation(mImgTarget).setDirection(
					Animation.DIRECTION_UP).animate();
			break;
		case 19:
			new SlideInUnderneathAnimation(mImgTarget).setDirection(
					Animation.DIRECTION_DOWN).animate();
			break;
		case 20:
			new SlideOutAnimation(mImgTarget).setDirection(
					Animation.DIRECTION_LEFT).animate();
			break;
		case 21:
			new SlideOutUnderneathAnimation(mImgTarget).setDirection(
					Animation.DIRECTION_RIGHT).animate();
			break;
		case 22:
			new TransferAnimation(mImgTarget).setDestinationView(mDestination)
					.animate();
			break;
		default:
			break;
		}
	}

	/**
	 * This method is prevents any padding from obstructing the animations.
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ViewGroup vg = (ViewGroup) view;
		// vg.setClipChildren(false);
		vg.setClipToPadding(false);
	}
}
