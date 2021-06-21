package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation rotates the view by a customizable number of degrees and at a
 * customizable pivot point.
 *
 * @author SiYao
 */
class RotationAnimation(view: View) : Animation(), Combinable {
    /**
     * @return The number of degrees to rotate by.
     */
    var degrees: Float

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP_LEFT`, `PIVOT_TOP_RIGHT`,
     * `PIVOT_BOTTOM_LEFT` and `PIVOT_BOTTOM_RIGHT`.
     *
     * @return The pivot point for rotation.
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
                PIVOT_TOP_LEFT -> {
                    pivotX = 1f
                    pivotY = 1f
                }
                PIVOT_TOP_RIGHT -> {
                    pivotX = viewWidth
                    pivotY = 1f
                }
                PIVOT_BOTTOM_LEFT -> {
                    pivotX = 1f
                    pivotY = viewHeight
                }
                PIVOT_BOTTOM_RIGHT -> {
                    pivotX = viewWidth
                    pivotY = viewHeight
                }
                else -> {
                    pivotX = viewWidth / 2
                    pivotY = viewHeight / 2
                }
            }
            view.setPivotX(pivotX)
            view.setPivotY(pivotY)
            val rotationSet = AnimatorSet()
            rotationSet.play(ObjectAnimator.ofFloat(view, View.ROTATION, view.getRotation() + degrees))
            rotationSet.setInterpolator(interpolator)
            rotationSet.setDuration(duration)
            rotationSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@RotationAnimation)
                    }
                }
            })
            return rotationSet
        }

    /**
     * In order to rotate anti-clockwise, the number of degrees should be
     * negative.
     *
     * @param degrees
     * The number of degrees to set to rotate by.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDegrees(degrees: Float): RotationAnimation {
        this.degrees = degrees
        return this
    }

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP_LEFT`, `PIVOT_TOP_RIGHT`,
     * `PIVOT_BOTTOM_LEFT` and `PIVOT_BOTTOM_RIGHT`.
     *
     * @param pivot
     * The pivot point to set for rotation.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setPivot(pivot: Int): RotationAnimation {
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
    override fun setInterpolator(interpolator: TimeInterpolator): RotationAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): RotationAnimation {
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
    override fun setListener(listener: AnimationListener?): RotationAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val PIVOT_CENTER = 0
        const val PIVOT_TOP_LEFT = 1
        const val PIVOT_TOP_RIGHT = 2
        const val PIVOT_BOTTOM_LEFT = 3
        const val PIVOT_BOTTOM_RIGHT = 4
    }

    /**
     * This animation rotates the view by a customizable number of degrees and
     * at a customizable pivot point.
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