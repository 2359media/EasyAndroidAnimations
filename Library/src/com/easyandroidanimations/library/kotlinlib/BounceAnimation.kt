package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to bounce by translating up and down for a
 * customizable number of times before returning to its original position.
 *
 * @author SiYao
 */
class BounceAnimation(view: View) : Animation() {
    /**
     * @return The maximum bounce distance.
     */
    var bounceDistance: Float

    /**
     * @return The number of bounces.
     */
    var numOfBounces: Int
    var bounceCount = 0
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        var singleBounceDuration = duration / numOfBounces / 4
        if (singleBounceDuration == 0L) singleBounceDuration = 1
        val bounceAnim = AnimatorSet()
        bounceAnim.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, bounceDistance),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -bounceDistance),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, bounceDistance),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0))
        bounceAnim.setInterpolator(interpolator)
        bounceAnim.setDuration(singleBounceDuration)
        var parentView: ViewGroup = view.getParent() as ViewGroup
        val rootView: ViewGroup = view.getRootView() as ViewGroup
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        bounceAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                bounceCount++
                if (bounceCount == numOfBounces) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@BounceAnimation)
                    }
                } else {
                    bounceAnim.start()
                }
            }
        })
        bounceAnim.start()
    }

    /**
     * @param bounceDistance
     * The maximum bounce distance to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setBounceDistance(bounceDistance: Float): BounceAnimation {
        this.bounceDistance = bounceDistance
        return this
    }

    /**
     * @param numOfBounces
     * The number of bounces to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setNumOfBounces(numOfBounces: Int): BounceAnimation {
        this.numOfBounces = numOfBounces
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
     */
    fun setInterpolator(interpolator: TimeInterpolator): BounceAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): BounceAnimation {
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
    fun setListener(listener: AnimationListener?): BounceAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation causes the view to bounce by translating up and down for a
     * customizable number of times before returning to its original position.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        bounceDistance = 20f
        numOfBounces = 2
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}