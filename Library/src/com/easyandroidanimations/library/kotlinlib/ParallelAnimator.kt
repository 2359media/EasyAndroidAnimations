package com.easyandroidanimations.library

import java.util.ArrayList

/**
 * This class allows multiple Combinable objects to be animated in parallel.
 *
 * @author SiYao
 */
class ParallelAnimator : Animation() {
    var combinableList: ArrayList<Combinable>
    var interpolator: TimeInterpolator? = null

    /**
     * @return The duration of the entire parallel animation.
     */
    var duration: Long = 0
    var listener: ParallelAnimatorListener?

    /**
     * This method adds this Combinable object to an ArrayList.
     *
     * @param combinable
     * The Animation object that implements the [Combinable]
     * interface and is allowed to animate with other animations.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun add(combinable: Combinable?): ParallelAnimator {
        combinableList.add(combinable)
        return this
    }

    /**
     * This method gets the ArrayList, sets any parameters if needed and plays
     * all animations in parallel.
     */
    @Override
    fun animate() {
        val animatorList: ArrayList<Animator> = ArrayList<Animator>()
        for (i in 0 until combinableList.size()) {
            if (duration > 0) {
                combinableList.get(i).setDuration(duration)
            }
            animatorList.add(combinableList.get(i).getAnimatorSet())
        }
        val parallelSet = AnimatorSet()
        parallelSet.playTogether(animatorList)
        if (interpolator != null) {
            parallelSet.setInterpolator(interpolator)
        }
        parallelSet.addListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                if (getListener() != null) {
                    getListener()!!.onAnimationEnd(this@ParallelAnimator)
                }
            }
        })
        parallelSet.start()
    }

    /**
     * @return The interpolator of the entire parallel animation.
     */
    fun getInterpolator(): TimeInterpolator? {
        return interpolator
    }

    /**
     * This method overrides the `setInterpolator()` methods of all
     * the parallel animations.
     *
     * @param interpolator
     * The interpolator of the entire parallel animation to set.
     */
    fun setInterpolator(interpolator: TimeInterpolator?): ParallelAnimator {
        this.interpolator = interpolator
        return this
    }

    /**
     * This method overrides the `setDuration()` methods of all the
     * parallel animations.
     *
     * @param duration
     * The duration of the entire parallel animation to set.
     */
    fun setDuration(duration: Long): ParallelAnimator {
        this.duration = duration
        return this
    }

    /**
     * @return The listener for the end of the parallel animation.
     */
    fun getListener(): ParallelAnimatorListener? {
        return listener
    }

    /**
     * @param listener
     * The listener to set for the end of the parallel animation.
     */
    fun setListener(listener: ParallelAnimatorListener?): ParallelAnimator {
        this.listener = listener
        return this
    }

    /**
     * This class allows multiple Combinable objects to be animated in parallel.
     */
    init {
        combinableList = ArrayList<Combinable>()
        listener = null
    }
}