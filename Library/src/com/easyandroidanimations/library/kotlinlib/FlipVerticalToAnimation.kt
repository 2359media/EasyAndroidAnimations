package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to flip vertically to reveal another
 * user-provided view at the back of the original view. On animation end, the
 * original view is restored to its original state and is set to
 * `View.INVISIBLE`.
 *
 * @author SiYao
 */
class FlipVerticalToAnimation(view: View) : Animation() {
    var flipToView: View?

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP` and `PIVOT_BOTTOM`.
     *
     * @return The pivot point for flipping.
     */
    var pivot: Int

    /**
     * The available flip directions are `DIRECTION_UP` and
     * `DIRECTION_DOWN`.
     *
     * @return The direction of the flip.
     * @see Animation
     */
    var direction: Int
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        var parentView: ViewGroup = view.getParent() as ViewGroup
        val rootView: ViewGroup = view
                .getRootView() as ViewGroup
        val pivotX: Float
        val pivotY: Float
        var flipAngle = 270f
        val viewWidth: Float = view.getWidth()
        val viewHeight: Float = view
                .getHeight()
        val originalRotationX: Float = view.getRotationX()
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
                flipAngle = 90f
            }
        }
        view.setPivotX(pivotX)
        view.setPivotY(pivotY)
        flipToView.setLayoutParams(view.getLayoutParams())
        flipToView.setLeft(view.getLeft())
        flipToView.setTop(view.getTop())
        flipToView.setPivotX(pivotX)
        flipToView.setPivotY(pivotY)
        flipToView.setVisibility(View.VISIBLE)
        while (parentView !== rootView) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        val flipToAnim = AnimatorSet()
        if (direction == DIRECTION_UP) {
            flipToView.setRotationX(270f)
            flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
                    View.ROTATION_X, 0f, flipAngle), ObjectAnimator.ofFloat(
                    flipToView, View.ROTATION_X, 270f, 360f))
        } else {
            flipToView.setRotationX(-270f)
            flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
                    View.ROTATION_X, 0f, -flipAngle), ObjectAnimator.ofFloat(
                    flipToView, View.ROTATION_X, -270f, -360f))
        }
        flipToAnim.setInterpolator(interpolator)
        flipToAnim.setDuration(duration / 2)
        flipToAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                view.setVisibility(View.INVISIBLE)
                view.setRotationX(originalRotationX)
                if (getListener() != null) {
                    getListener().onAnimationEnd(this@FlipVerticalToAnimation)
                }
            }
        })
        flipToAnim.start()
    }

    /**
     * @return The view to be revealed after flipping the original view.
     */
    fun getFlipToView(): View? {
        return flipToView
    }

    /**
     * @param flipToView
     * The view to set to be revealed after flipping the original
     * view.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setFlipToView(flipToView: View?): FlipVerticalToAnimation {
        this.flipToView = flipToView
        return this
    }

    /**
     * The available pivot points are `PIVOT_CENTER`,
     * `PIVOT_TOP` and `PIVOT_BOTTOM`.
     *
     * @param pivot
     * The pivot to set for flipping.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setPivot(pivot: Int): FlipVerticalToAnimation {
        this.pivot = pivot
        return this
    }

    /**
     * The available flip directions are `DIRECTION_UP` and
     * `DIRECTION_DOWN`.
     *
     * @param flipDirection
     * The direction of the flip to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see Animation
     */
    fun setDirection(direction: Int): FlipVerticalToAnimation {
        this.direction = direction
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
    fun setInterpolator(interpolator: TimeInterpolator): FlipVerticalToAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): FlipVerticalToAnimation {
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
    fun setListener(listener: AnimationListener?): FlipVerticalToAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val PIVOT_CENTER = 0
        const val PIVOT_TOP = 1
        const val PIVOT_BOTTOM = 2
    }

    /**
     * This animation causes the view to flip vertically to reveal another
     * user-provided view at the back of the original view. On animation end,
     * the original view is restored to its original state and is set to
     * `View.INVISIBLE`.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        flipToView = null
        pivot = PIVOT_CENTER
        direction = DIRECTION_UP
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}