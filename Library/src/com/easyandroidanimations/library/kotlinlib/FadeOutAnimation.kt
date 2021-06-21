package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation fades the view out by animating its alpha property to 0. On
 * animation end, the view is restored to its original state and is set to
 * `View.INVISIBLE`.
 *
 * @author SiYao
 */
class FadeOutAnimation(view: View) : Animation(), Combinable {
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
            val originalAlpha: Float = view.getAlpha()
            val fadeSet = AnimatorSet()
            fadeSet.play(ObjectAnimator.ofFloat(view, View.ALPHA, 0f))
            fadeSet.setInterpolator(interpolator)
            fadeSet.setDuration(duration)
            fadeSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    view.setVisibility(View.INVISIBLE)
                    view.setAlpha(originalAlpha)
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@FadeOutAnimation)
                    }
                }
            })
            return fadeSet
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
    override fun setInterpolator(interpolator: TimeInterpolator): FadeOutAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     */
    override fun setDuration(duration: Long): FadeOutAnimation {
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
    override fun setListener(listener: AnimationListener?): FadeOutAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation fades the view out by animating its alpha property to 0.
     * On animation end, the view is restored to its original state and is set
     * to `View.INVISIBLE`.
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