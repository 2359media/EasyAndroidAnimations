package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to flip vertically by a customizable number of
 * degrees and at a customizable pivot point.
 *
 * @author SiYao
 */
class FlipVerticalAnimation(view: View) : Animation(), Combinable {
    /**
     * @return The number of degrees to flip by.
     */
    var degrees: Float

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP` and `PIVOT_BOTTOM`.
     *
     * @return The pivot point for flipping.
     */
    var pivot: Int
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
            var parentView: ViewGroup = view.getParent() as ViewGroup
            val rootView: ViewGroup = view.getRootView() as ViewGroup
            while (parentView !== rootView) {
                parentView.setClipChildren(false)
                parentView = parentView.getParent() as ViewGroup
            }
            rootView.setClipChildren(false)
            val pivotX: Float
            val pivotY: Float
            val viewWidth: Float = view.getWidth()
            val viewHeight: Float = view.getHeight()
            when (pivot) {
                PIVOT_TOP -> {
                    pivotX = viewWidth / 2
                    pivotY = 0f
                }
                PIVOT_BOTTOM -> {
                    pivotX = viewWidth / 2
                    pivotY = viewHeight
                }
                else -> {
                    pivotX = viewWidth / 2
                    pivotY = viewHeight / 2
                }
            }
            view.setPivotX(pivotX)
            view.setPivotY(pivotY)
            val flipSet = AnimatorSet()
            flipSet.play(ObjectAnimator.ofFloat(view, View.ROTATION_X, view.getRotationX() + degrees))
            flipSet.setInterpolator(interpolator)
            flipSet.setDuration(duration)
            flipSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@FlipVerticalAnimation)
                    }
                }
            })
            return flipSet
        }

    /**
     * In order to flip down, the number of degrees should be negative and vice
     * versa.
     *
     * @param degrees
     * The number of degrees to set to flip by.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDegrees(degrees: Float): FlipVerticalAnimation {
        this.degrees = degrees
        return this
    }

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP` and `PIVOT_BOTTOM`.
     *
     * @param pivot
     * The pivot point to set for flipping.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setPivot(pivot: Int): FlipVerticalAnimation {
        this.pivot = pivot
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
    override fun setInterpolator(interpolator: TimeInterpolator): FlipVerticalAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): FlipVerticalAnimation {
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
    override fun setListener(listener: AnimationListener?): FlipVerticalAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val PIVOT_CENTER = 0
        const val PIVOT_TOP = 1
        const val PIVOT_BOTTOM = 2
    }

    /**
     * This animation causes the view to flip vertically by a customizable
     * number of degrees and at a customizable pivot point.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        degrees = 360f
        pivot = PIVOT_CENTER
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}