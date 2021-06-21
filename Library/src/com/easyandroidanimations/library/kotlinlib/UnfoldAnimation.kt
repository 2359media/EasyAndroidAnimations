package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation folds in the view for a customizable number of folds,
 * orientation and at a customizable anchor factor.
 *
 * @author SiYao
 */
class UnfoldAnimation(view: View) : Animation() {
    private val ANTIALIAS_PADDING = 1

    /**
     * @return The number of folds.
     */
    var numOfFolds: Int
    var orientation: Orientation

    /**
     * The available anchor factors range from 0 to 1. For example, in order to
     * anchor the fold on the left, the anchor factor should be 0.
     *
     * @return The anchor factor of the fold.
     */
    var anchorFactor: Float
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        val parentView: ViewGroup = view.getParent() as ViewGroup
        val positionView: Int = parentView.indexOfChild(view)
        val mFoldLayout = FoldLayout(view.getContext())
        mFoldLayout.setLayoutParams(LayoutParams(view.getWidth(), view
                .getHeight()))
        mFoldLayout.setX(view.getLeft())
        mFoldLayout.setY(view.getTop())
        parentView.removeView(view)
        parentView.addView(mFoldLayout, positionView)
        mFoldLayout.addView(view)
        view.setPadding(ANTIALIAS_PADDING, ANTIALIAS_PADDING,
                ANTIALIAS_PADDING, ANTIALIAS_PADDING)
        mFoldLayout.setNumberOfFolds(numOfFolds)
        mFoldLayout.setOrientation(orientation)
        mFoldLayout.setAnchorFactor(anchorFactor)
        mFoldLayout.setFoldFactor(1)
        mFoldLayout.setVisibility(View.INVISIBLE)
        val foldInAnim: ObjectAnimator = ObjectAnimator.ofFloat(mFoldLayout,
                "foldFactor", 1, 0)
        foldInAnim.setDuration(duration)
        foldInAnim.setInterpolator(interpolator)
        foldInAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                if (getListener() != null) {
                    getListener().onAnimationEnd(this@UnfoldAnimation)
                }
            }
        })
        foldInAnim.start()
        val foldOutAnim: ObjectAnimator = ObjectAnimator.ofFloat(mFoldLayout,
                "foldFactor", 1)
        foldOutAnim.setDuration(1)
        foldOutAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                mFoldLayout.setVisibility(View.VISIBLE)
                view.setVisibility(View.VISIBLE)
                foldInAnim.start()
            }
        })
        foldOutAnim.start()
    }

    /**
     * @param numOfFolds
     * The number of folds to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setNumOfFolds(numOfFolds: Int): UnfoldAnimation {
        this.numOfFolds = numOfFolds
        return this
    }

    /**
     * The available orientations are `Orientation.HORIZONTAL` and
     * `Orientation.VERTICAL`.
     *
     * @return The orientation of the fold.
     */
    fun getOrientation(): Orientation {
        return orientation
    }

    /**
     * The available orientations are `Orientation.HORIZONTAL` and
     * `Orientation.VERTICAL`.
     *
     * @param orientation
     * The orientation of the fold to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setOrientation(orientation: Orientation): UnfoldAnimation {
        this.orientation = orientation
        return this
    }

    /**
     * The available anchor factors range from 0 to 1. For example, in order to
     * anchor the fold on the left, the anchor factor should be 0.
     *
     * @param anchorFactor
     * The anchor factor of the fold to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setAnchorFactor(anchorFactor: Float): UnfoldAnimation {
        this.anchorFactor = anchorFactor
        return this
    }

    /**
     * @return The interpolator of the entire animation.
     */
    fun getInterpolator(): TimeInterpolator {
        return interpolator
    }

    /**
     * @param interpolator
     * The interpolator of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setInterpolator(interpolator: TimeInterpolator): UnfoldAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): UnfoldAnimation {
        this.duration = duration
        return this
    }

    /**
     * @return The listener for the end of the animation.
     */
    fun getListener(): AnimationListener? {
        return listener
    }

    /**
     * @param listener
     * The listener to set for the end of the animation.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setListener(listener: AnimationListener?): UnfoldAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation folds in the view for a customizable number of folds,
     * orientation and at a customizable anchor factor.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        numOfFolds = 1
        orientation = Orientation.HORIZONTAL
        anchorFactor = 0f
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}