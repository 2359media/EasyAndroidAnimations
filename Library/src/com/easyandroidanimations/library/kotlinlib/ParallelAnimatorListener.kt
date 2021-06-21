package com.easyandroidanimations.library

import com.easyandroidanimations.library.FoldLayout.Orientation

/**
 * This method is called when the parallel animation ends.
 *
 * @author SiYao
 */
interface ParallelAnimatorListener {
    /**
     * This method is called when the parallel animation ends.
     *
     * @param parallelAnimator
     * The ParallelAnimator object.
     */
    fun onAnimationEnd(parallelAnimator: ParallelAnimator?)
}