package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation scales out the view from 1 to 0. On animation end, the view is
 * restored to its original state and is set to `View.INVISIBLE`.
 *
 * @author SiYao
 */
class ScaleOutAnimation(view: View) : Animation(), Combinable {
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    override var duration: Long
    var listener: AnimationListener?

    @Override
    override fun animate() {
        animatorSet.start()
    }

    @get:Override
    override val animatorSet: AnimatorSet
        get() {
            val originalScaleX: Float = view.getScaleX()
            val originalScaleY: Float = view.getScaleY()
            val scaleSet = AnimatorSet()
            scaleSet.playTogether(ObjectAnimator.ofFloat(view, View.SCALE_X, 0f), ObjectAnimator.ofFloat(view, View.SCALE_Y, 0f))
            scaleSet.setInterpolator(interpolator)
            scaleSet.setDuration(duration)
            scaleSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    view.setVisibility(View.INVISIBLE)
                    view.setScaleX(originalScaleX)
                    view.setScaleY(originalScaleY)
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@ScaleOutAnimation)
                    }
                }
            })
            return scaleSet
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
    override fun setInterpolator(interpolator: TimeInterpolator): ScaleOutAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): ScaleOutAnimation {
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
    override fun setListener(listener: AnimationListener?): ScaleOutAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation scales out the view from 1 to 0. On animation end, the
     * view is restored to its original state and is set to
     * `View.INVISIBLE`.
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