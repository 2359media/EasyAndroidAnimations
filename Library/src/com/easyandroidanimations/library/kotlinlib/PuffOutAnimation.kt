package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation scales up and fades out the view. On animation end, the view
 * is restored to its original state and is set to `View.INVISIBLE`.
 *
 * @author Phu
 */
class PuffOutAnimation(view: View) : Animation() {
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
        val originalScaleX: Float = view.getScaleX()
        val originalScaleY: Float = view.getScaleY()
        val originalAlpha: Float = view.getAlpha()
        view.animate().scaleX(enlargeFactor).scaleY(enlargeFactor).alpha(0f)
                .setInterpolator(interpolator).setDuration(duration)
                .setListener(object : AnimatorListenerAdapter() {
                    @Override
                    fun onAnimationEnd(animation: Animator?) {
                        view.setVisibility(View.INVISIBLE)
                        view.setScaleX(originalScaleX)
                        view.setScaleY(originalScaleY)
                        view.setAlpha(originalAlpha)
                        if (getListener() != null) {
                            getListener().onAnimationEnd(this@PuffOutAnimation)
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
    fun setInterpolator(interpolator: TimeInterpolator): PuffOutAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): PuffOutAnimation {
        this.duration = duration
        return this
    }

    /**
     *
     * @param factor
     * The factor of the view to be enlarged at the beginning of the animation.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    fun setEnlargeFactor(factor: Double): PuffOutAnimation {
        enlargeFactor = factor.toFloat()
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
    fun setListener(listener: AnimationListener?): PuffOutAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation scales up and fades out the view. On animation end, the
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
        enlargeFactor = 4f
    }
}