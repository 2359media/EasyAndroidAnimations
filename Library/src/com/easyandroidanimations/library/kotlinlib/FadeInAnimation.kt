package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation fades the view in by animating its alpha property from 0 to 1.
 *
 * @author SiYao
 */
class FadeInAnimation(view: View) : Animation(), Combinable {
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
            view.setAlpha(0f)
            view.setVisibility(View.VISIBLE)
            val fadeSet = AnimatorSet()
            fadeSet.play(ObjectAnimator.ofFloat(view, View.ALPHA, 1f))
            fadeSet.setInterpolator(interpolator)
            fadeSet.setDuration(duration)
            fadeSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@FadeInAnimation)
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
    override fun setInterpolator(interpolator: TimeInterpolator): FadeInAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @return The duration of the entire animation to set.
     */
    override fun setDuration(duration: Long): FadeInAnimation {
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
     * @return The listener to set for the end of the animation.
     */
    override fun setListener(listener: AnimationListener?): FadeInAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation fades the view in by animating its alpha property from 0
     * to 1.
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