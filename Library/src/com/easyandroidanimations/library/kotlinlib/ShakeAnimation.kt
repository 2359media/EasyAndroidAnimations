package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to shake from left to right for a customizable
 * number of times before returning to its original position.
 *
 * @author SiYao
 */
class ShakeAnimation(view: View) : Animation() {
    /**
     * @return The maximum shake distance.
     */
    var shakeDistance: Float

    /**
     * @return The number of shakes.
     */
    var numOfShakes: Int

    /**
     * @return The direction for the animation.
     */
    var direction: Int
    var shakeCount = 0
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        var singleShakeDuration = duration / numOfShakes / 2
        if (singleShakeDuration == 0L) singleShakeDuration = 1
        val shakeAnim = AnimatorSet()
        if (direction == DIRECTION_HORIZONTAL) {
            shakeAnim.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_X, shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0))
        } else {
            shakeAnim.playSequentially(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, shakeDistance),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0))
        }
        shakeAnim.setInterpolator(interpolator)
        shakeAnim.setDuration(singleShakeDuration)
        var parentView: ViewGroup = view.getParent() as ViewGroup
        val rootView: ViewGroup = view.getRootView() as ViewGroup
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        shakeAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                shakeCount++
                if (shakeCount == numOfShakes) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@ShakeAnimation)
                    }
                } else {
                    shakeAnim.start()
                }
            }
        })
        shakeAnim.start()
    }

    /**
     * @param shakeDistance
     * The maximum shake distance to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setShakeDistance(shakeDistance: Float): ShakeAnimation {
        this.shakeDistance = shakeDistance
        return this
    }

    /**
     * @param numOfShakes
     * The number of shakes to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setNumOfShakes(numOfShakes: Int): ShakeAnimation {
        this.numOfShakes = numOfShakes
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
    fun setInterpolator(interpolator: TimeInterpolator): ShakeAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): ShakeAnimation {
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
    fun setListener(listener: AnimationListener?): ShakeAnimation {
        this.listener = listener
        return this
    }

    /**
     * @param direction
     * The direction of the animation. 0 for left-right, 1 for up-down.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDirection(direction: Int): ShakeAnimation {
        this.direction = direction
        return this
    }

    companion object {
        const val DIRECTION_HORIZONTAL = 0
        const val DIRECTION_VERTICAL = 1
    }

    /**
     * This animation causes the view to shake from left to right / up to down for a
     * customizable number of times before returning to its original position.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        shakeDistance = 20f
        direction = 0
        numOfShakes = 2
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}