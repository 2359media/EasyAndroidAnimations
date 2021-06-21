package com.easyandroidanimations.library

import java.util.ArrayList

/**
 * This animation translates the view within its parent view and according to
 * the ArrayList<Point> provided by the user. The values of X and Y in each
 * Point must be in the range of 0-100. Note: The status bar and action bar are
 * not taken into consideration for the translation.
 *
 * @author SiYao
</Point> */
class PathAnimation(view: View) : Animation(), Combinable {
    var points: ArrayList<Point>?

    /**
     * The available anchor points at which to translate the view are
     * `ANCHOR_CENTER`, `ANCHOR_TOP_LEFT`,
     * `ANCHOR_TOP_RIGHT`, `ANCHOR_BOTTOM_LEFT` and
     * `ANCHOR_BOTTOM_RIGHT`.
     *
     * @return The anchor point at which to translate the view.
     */
    var anchorPoint: Int
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
            val pathSet = AnimatorSet()
            val numOfPoints: Int = points.size()
            val pathAnimSetArray: Array<AnimatorSet?> = arrayOfNulls<AnimatorSet>(numOfPoints)
            val pathAnimList: List<Animator> = ArrayList<Animator>()
            parentView = view.getParent() as ViewGroup
            val parentWidth: Int = parentView.getWidth()
            val parentHeight: Int = parentView.getHeight()
            val viewWidth: Int = view.getWidth()
            val viewHeight: Int = view.getHeight()
            var posX: Float
            var posY: Float
            for (i in 0 until numOfPoints) {
                posX = points.get(i).x / 100f * parentWidth
                posY = points.get(i).y / 100f * parentHeight
                when (anchorPoint) {
                    ANCHOR_CENTER -> {
                        posX = posX - viewWidth / 2
                        posY = posY - viewHeight / 2
                    }
                    ANCHOR_TOP_RIGHT -> posX -= viewWidth.toFloat()
                    ANCHOR_BOTTOM_LEFT -> posY -= viewHeight.toFloat()
                    ANCHOR_BOTTOM_RIGHT -> {
                        posX = posX - viewWidth
                        posY = posY - viewHeight
                    }
                    else -> {
                    }
                }
                pathAnimSetArray[i] = AnimatorSet()
                pathAnimSetArray[i].playTogether(
                        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, posX),
                        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, posY))
                pathAnimList.add(pathAnimSetArray[i])
            }
            pathSet.playSequentially(pathAnimList)
            pathSet.setInterpolator(interpolator)
            pathSet.setDuration(duration / numOfPoints)
            pathSet.addListener(object : AnimatorListenerAdapter() {
                @Override
                fun onAnimationEnd(animation: Animator?) {
                    if (getListener() != null) {
                        getListener().onAnimationEnd(this@PathAnimation)
                    }
                }
            })
            return pathSet
        }

    /**
     * @return The ArrayList<Point> for the view to translate to.
     * @see java.util.ArrayList
    </Point> */
    fun getPoints(): ArrayList<Point>? {
        return points
    }

    /**
     * @param points
     * The ArrayList<Point> to set for the view to translate to.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     * @see java.util.ArrayList
    </Point> */
    fun setPoints(points: ArrayList<Point?>): PathAnimation {
        this.points = points
        return this
    }

    /**
     * The available anchor points at which to translate the view are
     * `ANCHOR_CENTER`, `ANCHOR_TOP_LEFT`,
     * `ANCHOR_TOP_RIGHT`, `ANCHOR_BOTTOM_LEFT` and
     * `ANCHOR_BOTTOM_RIGHT`.
     *
     * @param anchorPoint
     * The anchor point to set at which to translate the view.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setAnchorPoint(anchorPoint: Int): PathAnimation {
        this.anchorPoint = anchorPoint
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
    override fun setInterpolator(interpolator: TimeInterpolator): PathAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    override fun setDuration(duration: Long): PathAnimation {
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
    override fun setListener(listener: AnimationListener?): PathAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val ANCHOR_CENTER = 0
        const val ANCHOR_TOP_LEFT = 1
        const val ANCHOR_TOP_RIGHT = 2
        const val ANCHOR_BOTTOM_LEFT = 3
        const val ANCHOR_BOTTOM_RIGHT = 4
    }

    /**
     * This animation translates the view within its parent view and according
     * to the ArrayList<Point> provided by the user. The values of X and Y in
     * each Point must be in the range of 0-100. Note: The status bar and action
     * bar are not taken into consideration for the translation.
     *
     * @param view
     * The view to be animated.
    </Point> */
    init {
        view = view
        points = null
        anchorPoint = ANCHOR_CENTER
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}