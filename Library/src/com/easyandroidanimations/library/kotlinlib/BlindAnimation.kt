package com.easyandroidanimations.library.kotlinlib

/**
 * This animation hides the view by scaling its Y property to mimic the
 * "pulling of blinds". On animation end, the view is restored to its original
 * state and is set to `View.INVISIBLE`.
 *
 * @author Phu
 *
 */
class BlindAnimation(view: View) : Animation() {
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?

    fun animate() {
        val parent: ViewGroup = view.getParent() as ViewGroup
        FrameLayout(view.getContext())

        val positionView: Int = parent.indexOfChild(view)
        animationLayout.setLayoutParams(view.getLayoutParams())
        parent.removeView(view)
        animationLayout.addView(view)
        parent.addView(animationLayout, positionView)

        val originalScaleY: Float = view.getScaleY()
        val scaleY: ObjectAnimator = ObjectAnimator.ofFloat<View>(animationLayout, View.SCALE_Y, 0f)
        val scaleY_child: ObjectAnimator = ObjectAnimator.ofFloat<View>(view, View.SCALE_Y, 2.5f)
        animationLayout.setPivotX(1f)
        animationLayout.setPivotY(1f)
        view.setPivotX(1f)
        view.setPivotY(1f)

        val blindAnimationSet = AnimatorSet()
        blindAnimationSet.playTogether(scaleY, scaleY_child)
        blindAnimationSet.setInterpolator(interpolator)
        blindAnimationSet.setDuration(duration / 2)
        blindAnimationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.setVisibility(View.INVISIBLE)
                view.setScaleY(originalScaleY)
                animationLayout.removeAllViews()
                parent.removeView(animationLayout)
                if (animationLayout.getLayoutParams() != null) {
                    parent.addView(view, positionView, animationLayout.getLayoutParams())
                } else {
                    parent.addView(view, positionView)
                }
                if (getListener() != null) {
                    getListener().onAnimationEnd(this@BlindAnimation)
                }
            }
        })
        blindAnimationSet.start()
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
     */
    fun setInterpolator(interpolator: TimeInterpolator): BlindAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): BlindAnimation {
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
    fun setListener(listener: AnimationListener?): BlindAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation hides the view by scaling its Y property to mimic the
     * "pulling of blinds". On animation end, the view is restored to its
     * original state and is set to `View.INVISIBLE`.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}