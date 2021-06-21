package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation makes use of a translucent box to overlay the view before
 * animating its alpha property to mimic the highlighting of the view.
 *
 * @author SiYao
 */
class HighlightAnimation(view: View) : Animation() {
    /**
     * @return The color of the highlight.
     * @see android.graphics.Color
     */
    var color: Int
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        val highlightFrame = FrameLayout(view.getContext())
        val layoutParams = LayoutParams(view.getWidth(),
                view.getHeight())
        val highlightView = ImageView(view.getContext())
        highlightView.setBackgroundColor(color)
        highlightView.setAlpha(0.5f)
        highlightView.setVisibility(View.VISIBLE)
        val parentView: ViewGroup = view.getParent() as ViewGroup
        val positionView: Int = parentView.indexOfChild(view)
        parentView.addView(highlightFrame, positionView, layoutParams)
        highlightFrame.setX(view.getLeft())
        highlightFrame.setY(view.getTop())
        parentView.removeView(view)
        highlightFrame.addView(view)
        highlightFrame.addView(highlightView)
        highlightView.animate().alpha(0).setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(object : AnimatorListenerAdapter() {
                    @Override
                    fun onAnimationEnd(animation: Animator?) {
                        highlightFrame.removeAllViews()
                        parentView.addView(view, positionView)
                        view.setX(highlightFrame.getLeft())
                        view.setY(highlightFrame.getTop())
                        parentView.removeView(highlightFrame)
                        if (getListener() != null) {
                            getListener().onAnimationEnd(
                                    this@HighlightAnimation)
                        }
                    }
                })
    }

    /**
     * @param color
     * The color of the highlight to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see android.graphics.Color
     */
    fun setColor(color: Int): HighlightAnimation {
        this.color = color
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
    fun setInterpolator(interpolator: TimeInterpolator): HighlightAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): HighlightAnimation {
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
    fun setListener(listener: AnimationListener?): HighlightAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation makes use of a translucent box to overlay the view before
     * animating its alpha property to mimic the highlighting of the view.
     *
     * @param color
     * the color of the highlight
     * @param duration
     * the duration of the entire animation
     * @param listener
     * the AnimationListener of animation @see
     * [AnimationListener]
     */
    init {
        view = view
        color = Color.YELLOW
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}