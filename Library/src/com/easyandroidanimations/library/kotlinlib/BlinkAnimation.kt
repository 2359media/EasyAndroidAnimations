package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to fade in and fade out a customizable number
 * of times.
 *
 * @author SiYao
 */
class BlinkAnimation(view: View) : Animation() {
    /**
     * @return The number of blinks.
     */
    var numOfBlinks: Int
    var blinkCount = 0
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        var singleBlinkDuration = duration / numOfBlinks / 2
        if (singleBlinkDuration == 0L) singleBlinkDuration = 1
        val fadeOut: ObjectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0)
        val fadeIn: ObjectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1)
        val blinkAnim = AnimatorSet()
        blinkAnim.playSequentially(fadeOut, fadeIn)
        blinkAnim.setInterpolator(interpolator)
        blinkAnim.setDuration(singleBlinkDuration)
        blinkAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                blinkCount++
                if (blinkCount == numOfBlinks) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@BlinkAnimation)
                    }
                } else {
                    blinkAnim.start()
                }
            }
        })
        blinkAnim.start()
    }

    /**
     * @param numOfBlinks
     * The number of blinks to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setNumOfBlinks(numOfBlinks: Int): BlinkAnimation {
        this.numOfBlinks = numOfBlinks
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
    fun setInterpolator(interpolator: TimeInterpolator): BlinkAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     */
    fun setDuration(duration: Long): BlinkAnimation {
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
     */
    fun setListener(listener: AnimationListener?): BlinkAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation causes the view to fade in and fade out a customizable
     * number of times.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        numOfBlinks = 2
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}