package com.easyandroidanimations.library.kotlinlib

/**
 * The parent class of all animation classes.
 *
 */
abstract class Animation {

    var view: View? = null

    /**
     * This method animates the properties of the view specific to the Animation
     * object.
     */
    abstract fun animate(){

    }

    // constants
    companion object {
        const val DIRECTION_LEFT = 1
        const val DIRECTION_RIGHT = 2
        const val DIRECTION_UP = 3
        const val DIRECTION_DOWN = 4

        const val DURATION_DEFAULT = 300 // 300 ms
        const val DURATION_SHORT = 100 // 100 ms
        const val DURATION_LONG = 500 // 500 ms
    }
}