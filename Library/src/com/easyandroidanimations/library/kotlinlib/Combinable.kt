package com.easyandroidanimations.library

import android.animation.AnimatorSet

/**
 * This interface is implemented only by animation classes that can be combined
 * to animate together.
 *
 */
interface Combinable {
    fun animate()
    val animatorSet: AnimatorSet?
    fun setInterpolator(interpolator: TimeInterpolator?): Animation?
    val duration: Long
    fun setDuration(duration: Long): Animation?
    fun setListener(listener: AnimationListener?): Animation?
}