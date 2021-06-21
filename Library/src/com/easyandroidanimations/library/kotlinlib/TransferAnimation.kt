package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation transfers the view to another view provided by the user
 * through scaling and translation. The view is scaled to the same size and is
 * translated to the same position as the destination view.
 *
 * @author SiYao
 */
class TransferAnimation(view: View) : Animation() {
    var destinationView: View?
    var transX = 0
    var transY = 0
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    var parentView: ViewGroup? = null
    @Override
    fun animate() {
        parentView = view.getParent() as ViewGroup
        val rootView: ViewGroup = view.getRootView() as ViewGroup
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        val scaleX = destinationView.getWidth() as Float / view.getWidth() as Float
        val scaleY = destinationView.getHeight() as Float / view.getHeight() as Float
        val locationDest = IntArray(2)
        val locationView = IntArray(2)
        view.getLocationOnScreen(locationView)
        destinationView.getLocationOnScreen(locationDest)
        transX = locationDest[0] - locationView[0]
        transY = locationDest[1] - locationView[1]
        transX = transX - view.getWidth() / 2 + destinationView.getWidth() / 2
        transY = transY - view.getHeight() / 2 + destinationView.getHeight() / 2
        view.animate().scaleX(scaleX).scaleY(scaleY).translationX(transX)
                .translationY(transY).setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(object : AnimatorListenerAdapter() {
                    @Override
                    fun onAnimationEnd(animation: Animator?) {
                        if (getListener() != null) {
                            getListener().onAnimationEnd(this@TransferAnimation)
                        }
                    }
                })
    }

    /**
     * @return The destination view to transfer the original view to.
     */
    fun getDestinationView(): View? {
        return destinationView
    }

    /**
     * @param destinationView
     * The destination view to set to transfer the original view to.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDestinationView(destinationView: View?): TransferAnimation {
        this.destinationView = destinationView
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
    fun setInterpolator(interpolator: TimeInterpolator): TransferAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): TransferAnimation {
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
    fun setListener(listener: AnimationListener?): TransferAnimation {
        this.listener = listener
        return this
    }

    /**
     * This animation transfers the view to another view provided by the user
     * through scaling and translation. The view is scaled to the same size and
     * is translated to the same position as the destination view.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        destinationView = null
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}