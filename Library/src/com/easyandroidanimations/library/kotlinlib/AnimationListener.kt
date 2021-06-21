package com.easyandroidanimations.library.kotlinlib

/**
 * This interface is a custom listener to determine the end of an animation.
 *
 * @author Phu
 **/
interface AnimationListener {
    /**
     * This method is called when the animation ends.
     *
     * @param animation
     * The Animation object.
     */
    fun onAnimationEnd(animation: Animation?){

    }
}