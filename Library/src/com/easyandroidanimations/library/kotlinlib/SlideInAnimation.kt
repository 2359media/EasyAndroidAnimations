package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation causes the view to slide in from the borders of the screen.
 *
 * @author SiYao
 */
class SlideInAnimation(view: View) : Animation(), Combinable {
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
            while (!parentView.equals(rootView)) {
                parentView.setClipChildren(false)
                parentView = parentView.getParent() as ViewGroup
            }
            rootView.setClipChildren(false)
            val locationView = IntArray(2)
            view.getLocationOnScreen(locationView)
            var slideAnim: ObjectAnimator? = null
            when (direction) {
                DIRECTION_LEFT -> slideAnim = ObjectAnimator.ofFloat(view, View.X, -locationView[0] - view.getWidth(), view.getX())
                DIRECTION_RIGHT -> slideAnim = ObjectAnimator.ofFloat(view, View.X, rootView.getRight(), view.getX())
                DIRECTION_UP -> slideAnim = ObjectAnimator.ofFloat(view, View.Y, -locationView[1] - view.getHeight(), view.getY())
                DIRECTION_DOWN -> slideAnim = ObjectAnimator.ofFloat(view, View.Y, rootView.getBottom(), view.getY())
                else -> {
                }
            }
            val slideSet = AnimatorSet()
            slideSet.play(slideAnim)
            slideSet.setInterpolator(interpolator)
            slideSet.setDuration(duration)
            slideSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationStart(animation: Animator?) {
                    view.setVisibility(View.VISIBLE)
                }

                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@SlideInAnimation)
                    }
                }
            })
            return slideSet
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
    fun setDirection(direction: Int): SlideInAnimation {
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
    override fun setInterpolator(interpolator: TimeInterpolator): SlideInAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): SlideInAnimation {
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
    override fun setListener(listener: AnimationListener?): SlideInAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation causes the view to slide in from the borders of the
     * screen.
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