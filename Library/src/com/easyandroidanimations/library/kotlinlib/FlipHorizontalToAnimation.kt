package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to flip horizontally to reveal another
 * user-provided view at the back of the original view. On animation end, the
 * original view is restored to its original state and is set to
 * `View.INVISIBLE`.
 *
 * @author SiYao
 */
class FlipHorizontalToAnimation(view: View) : Animation() {
    var flipToView: View?

    /**
     * The available pivot points to flip are `PIVOT_CENTER`,
     * `PIVOT_LEFT` and `PIVOT_RIGHT`.
     *
     * @return The pivot point for flipping.
     */
    var pivot: Int

    /**
     * The available flip directions are `DIRECTION_LEFT` and
     * `DIRECTION_RIGHT`.
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
        val originalRotationY: Float = view.getRotationY()
        when (pivot) {
            PIVOT_LEFT -> {
                pivotX = 0f
                pivotY = viewHeight / 2
            }
            PIVOT_RIGHT -> {
                pivotX = viewWidth
                pivotY = viewHeight / 2
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
        if (direction == DIRECTION_RIGHT) {
            flipToView.setRotationY(270f)
            flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
                    View.ROTATION_Y, 0f, flipAngle), ObjectAnimator.ofFloat(
                    flipToView, View.ROTATION_Y, 270f, 360f))
        } else {
            flipToView.setRotationY(-270f)
            flipToAnim.playSequentially(ObjectAnimator.ofFloat(view,
                    View.ROTATION_Y, 0f, -flipAngle), ObjectAnimator.ofFloat(
                    flipToView, View.ROTATION_Y, -270f, -360f))
        }
        flipToAnim.setInterpolator(interpolator)
        flipToAnim.setDuration(duration / 2)
        flipToAnim.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                view.setVisibility(View.INVISIBLE)
                view.setRotationY(originalRotationY)
                if (getListener() != null) {
                    getListener()
                            .onAnimationEnd(this@FlipHorizontalToAnimation)
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
    fun setFlipToView(flipToView: View?): FlipHorizontalToAnimation {
        this.flipToView = flipToView
        return this
    }

    /**
     * The available pivot points to flip are `PIVOT_CENTER`,
     * `PIVOT_LEFT` and `PIVOT_RIGHT`.
     *
     * @param pivot
     * The pivot point to set for flipping.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setPivot(pivot: Int): FlipHorizontalToAnimation {
        this.pivot = pivot
        return this
    }

    /**
     * The available flip directions are `DIRECTION_LEFT` and
     * `DIRECTION_RIGHT`.
     *
     * @param direction
     * The direction of the flip to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see Animation
     */
    fun setDirection(direction: Int): FlipHorizontalToAnimation {
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
    fun setInterpolator(
            interpolator: TimeInterpolator): FlipHorizontalToAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): FlipHorizontalToAnimation {
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
    fun setListener(listener: AnimationListener?): FlipHorizontalToAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val PIVOT_CENTER = 0
        const val PIVOT_LEFT = 1
        const val PIVOT_RIGHT = 2
    }

    /**
     * This animation causes the view to flip horizontally to reveal another
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
        direction = DIRECTION_RIGHT
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}