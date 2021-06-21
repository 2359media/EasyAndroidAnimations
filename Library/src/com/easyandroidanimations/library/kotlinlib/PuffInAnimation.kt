package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation scales down and fades in the view.
 *
 * @author Phu
 */
class PuffInAnimation(view: View) : Animation() {
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?

    /**
     * @return The enlarging factor at the beginning of the animation.
     */
    var enlargeFactor: Float
    @Override
    fun animate() {
        var parentView: ViewGroup = view.getParent() as ViewGroup
        val rootView: ViewGroup = view.getRootView() as ViewGroup
        while (parentView !== rootView) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        view.setScaleX(enlargeFactor)
        view.setScaleY(enlargeFactor)
        view.setAlpha(0f)
        view.animate().scaleX(1f).scaleY(1f).alpha(1f)
                .setInterpolator(interpolator).setDuration(duration)
                .setListener(object : AnimatorListenerAdapter() {
                    @Override
                    fun onAnimationStart(animation: Animator?) {
                        view.setVisibility(View.VISIBLE)
                    }

                    @Override
                    fun onAnimationEnd(animation: Animator?) {
                        if (getListener() != null) {
                            getListener().onAnimationEnd(this@PuffInAnimation)
                        }
                    }
                })
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
    fun setInterpolator(interpolator: TimeInterpolator): PuffInAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): PuffInAnimation {
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
    fun setListener(listener: AnimationListener?): PuffInAnimation {
        this.listener = listener
        return this
    }

    /**
     *
     * @param factor
     * The factor of the view to be enlarged at the beginning of the animation.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    fun setEnlargeFactor(factor: Double): PuffInAnimation {
        enlargeFactor = factor.toFloat()
        return this
    }

    /**
     * This animation scales down and fades in the view.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
        enlargeFactor = 4f
    }
}