package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation scales in the view from 0 to 1.
 *
 * @author SiYao
 */
class ScaleInAnimation(view: View) : Animation(), Combinable {
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    override var duration: Long
    var listener: AnimationListener?

    /**
     * @return The enlarging factor at the end of the animation.
     */
    var enlargeFactor: Float

    @Override
    override fun animate() {
        animatorSet.start()
    }

    @get:Override
    override val animatorSet: AnimatorSet
        get() {
            var parentView: ViewGroup = view.getParent() as ViewGroup
            val rootView: ViewGroup = view.getRootView() as ViewGroup
            while (parentView !== rootView) {
                parentView.setClipChildren(false)
                parentView = parentView.getParent() as ViewGroup
            }
            view.setScaleX(0f)
            view.setScaleY(0f)
            view.setVisibility(View.VISIBLE)
            val scaleSet = AnimatorSet()
            scaleSet.playTogether(ObjectAnimator.ofFloat(view, View.SCALE_X, enlargeFactor),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, enlargeFactor))
            scaleSet.setInterpolator(interpolator)
            scaleSet.setDuration(duration)
            scaleSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@ScaleInAnimation)
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
    override fun setInterpolator(interpolator: TimeInterpolator): ScaleInAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): ScaleInAnimation {
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
    override fun setListener(listener: AnimationListener?): ScaleInAnimation {
        this.listener = listener
        return this
    }

    /**
     * @param factor
     * The factor of the view to be enlarged at the end of the animation.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    fun setEnlargeFactor(factor: Double): ScaleInAnimation {
        enlargeFactor = factor.toFloat()
        return this
    }

    /**
     * This animation scales in the view from 0 to 1.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
        enlargeFactor = 1f
    }
}