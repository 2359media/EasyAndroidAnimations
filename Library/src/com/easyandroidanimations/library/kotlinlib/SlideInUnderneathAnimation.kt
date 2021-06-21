package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to slide in underneath from its own borders.
 *
 * @author SiYao
 */
class SlideInUnderneathAnimation(view: View) : Animation() {
    /**
     * The available directions to slide in from are `DIRECTION_LEFT`
     * , `DIRECTION_RIGHT`, `DIRECTION_TOP` and
     * `DIRECTION_BOTTOM`.
     *
     * @return The direction to slide the view in from.
     * @see Animation
     */
    var direction: Int
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    var slideAnim: ValueAnimator? = null
    @Override
    fun animate() {
        val parentView: ViewGroup = view.getParent() as ViewGroup
        val slideInFrame = FrameLayout(view.getContext())
        val positionView: Int = parentView.indexOfChild(view)
        slideInFrame.setLayoutParams(view.getLayoutParams())
        slideInFrame.setClipChildren(true)
        parentView.removeView(view)
        slideInFrame.addView(view)
        parentView.addView(slideInFrame, positionView)
        val viewWidth: Float = view.getWidth()
        val viewHeight: Float = view.getHeight()
        when (direction) {
            DIRECTION_LEFT -> {
                view.setTranslationX(-viewWidth)
                slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, slideInFrame.getX())
            }
            DIRECTION_RIGHT -> {
                view.setTranslationX(viewWidth)
                slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, slideInFrame.getX())
            }
            DIRECTION_UP -> {
                view.setTranslationY(-viewHeight)
                slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, slideInFrame.getY())
            }
            DIRECTION_DOWN -> {
                view.setTranslationY(viewHeight)
                slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, slideInFrame.getY())
            }
            else -> {
            }
        }
        val slideSet = AnimatorSet()
        slideSet.play(slideAnim)
        slideSet.setInterpolator(interpolator)
        slideSet.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                slideInFrame.removeAllViews()
                view.setLayoutParams(slideInFrame.getLayoutParams())
                parentView.addView(view, positionView)
                if (getListener() != null) {
                    getListener().onAnimationEnd(this@SlideInUnderneathAnimation)
                }
            }

            @Override
            fun onAnimationStart(animation: Animator?) {
                view.setVisibility(View.VISIBLE)
            }
        })
        slideAnim.start()
    }

    /**
     * The available directions to slide in from are `DIRECTION_LEFT`
     * , `DIRECTION_RIGHT`, `DIRECTION_TOP` and
     * `DIRECTION_BOTTOM`.
     *
     * @param direction
     * The direction to set to slide the view in from.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see Animation
     */
    fun setDirection(direction: Int): SlideInUnderneathAnimation {
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
            interpolator: TimeInterpolator): SlideInUnderneathAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): SlideInUnderneathAnimation {
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
    fun setListener(listener: AnimationListener?): SlideInUnderneathAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation causes the view to slide in underneath from its own
     * borders.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        direction = DIRECTION_LEFT
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}