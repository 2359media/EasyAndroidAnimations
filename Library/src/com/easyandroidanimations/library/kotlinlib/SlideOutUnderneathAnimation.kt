package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to slide out underneath to its own borders. On
 * animation end, the view is restored to its original state and is set to
 * `View.INVISIBLE`.
 *
 * @author SiYao
 */
class SlideOutUnderneathAnimation(view: View) : Animation() {
    /**
     * The available directions to slide in from are `DIRECTION_LEFT`
     * , `DIRECTION_RIGHT`, `DIRECTION_TOP` and
     * `DIRECTION_BOTTOM`.
     *
     * @return The direction to slide the view out to.
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
        val slideOutFrame = FrameLayout(view.getContext())
        val positionView: Int = parentView.indexOfChild(view)
        slideOutFrame.setLayoutParams(view.getLayoutParams())
        slideOutFrame.setClipChildren(true)
        parentView.removeView(view)
        slideOutFrame.addView(view)
        parentView.addView(slideOutFrame, positionView)
        when (direction) {
            DIRECTION_LEFT -> slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X,
                    view.getTranslationX() - view.getWidth())
            DIRECTION_RIGHT -> slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X,
                    view.getTranslationX() + view.getWidth())
            DIRECTION_UP -> slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                    view.getTranslationY() - view.getHeight())
            DIRECTION_DOWN -> slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                    view.getTranslationY() + view.getHeight())
            else -> {
            }
        }
        val slideSet = AnimatorSet()
        slideSet.play(slideAnim)
        slideSet.setInterpolator(interpolator)
        slideSet.setDuration(duration)
        slideSet.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                view.setVisibility(View.INVISIBLE)
                slideAnim.reverse()
                slideOutFrame.removeAllViews()
                parentView.removeView(slideOutFrame)
                parentView.addView(view, positionView)
                if (getListener() != null) {
                    getListener().onAnimationEnd(
                            this@SlideOutUnderneathAnimation)
                }
            }
        })
        slideSet.start()
    }

    /**
     * The available directions to slide in from are `DIRECTION_LEFT`
     * , `DIRECTION_RIGHT`, `DIRECTION_TOP` and
     * `DIRECTION_BOTTOM`.
     *
     * @param direction
     * The direction to set to slide the view out to.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see Animation
     */
    fun setDirection(direction: Int): SlideOutUnderneathAnimation {
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
            interpolator: TimeInterpolator): SlideOutUnderneathAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): SlideOutUnderneathAnimation {
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
    fun setListener(listener: AnimationListener?): SlideOutUnderneathAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation causes the view to slide out underneath to its own
     * borders. On animation end, the view is restored to its original state and
     * is set to `View.INVISIBLE`.
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