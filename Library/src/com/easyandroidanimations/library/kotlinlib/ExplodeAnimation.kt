package com.easyandroidanimations.library

import android.animation.Animator

/**
 * This animation creates a bitmap of the view, divides them into customizable
 * number of X and Y parts and translates the parts away from the center of the
 * view to mimic an explosion. The number of parts can vary from 1x2 to 3x3. The
 * view is set to invisible and added back for reuse.
 *
 * @author SiYao
 */
class ExplodeAnimation(view: View) : Animation() {
    private var xParts = 0
    private var yParts = 0
    var parentView: ViewGroup? = null

    /**
     * The available matrices are `MATRIX_1X2`,
     * `MATRIX_1X3`, `MATRIX_2X1`, `MATRIX_2X2`
     * , `MATRIX_2X3`, `MATRIX_3X1`,
     * `MATRIX_3X2` and `MATRIX_3X3`.
     *
     * @return The matrix that determines the number of X and Y parts.
     */
    var explodeMatrix = 0
    var interpolator: TimeInterpolator

    /**
     * @return The duration of the entire animation.
     */
    var duration: Long
    var listener: AnimationListener?
    @Override
    fun animate() {
        val explodeLayout = LinearLayout(view.getContext())
        val layouts: Array<LinearLayout?> = arrayOfNulls<LinearLayout>(yParts)
        parentView = view.getParent() as ViewGroup
        explodeLayout.setLayoutParams(view.getLayoutParams())
        explodeLayout.setOrientation(LinearLayout.VERTICAL)
        explodeLayout.setClipChildren(false)
        view.setDrawingCacheEnabled(true)
        val viewBmp: Bitmap = view.getDrawingCache(true)
        val totalParts = xParts * yParts
        val bmpWidth: Int = (viewBmp.getWidth()
                / xParts)
        val bmpHeight: Int = viewBmp.getHeight() / yParts
        var widthCount = 0
        var heightCount = 0
        val middleXPart = (xParts - 1) / 2
        var translation = IntArray(2)
        val imageViews: Array<ImageView?> = arrayOfNulls<ImageView>(totalParts)
        for (i in 0 until totalParts) {
            var translateX = 0
            var translateY = 0
            if (i % xParts == 0) {
                if (i != 0) heightCount++
                widthCount = 0
                layouts[heightCount] = LinearLayout(view.getContext())
                layouts[heightCount].setClipChildren(false)
                translation = sideTranslation(heightCount, bmpWidth, bmpHeight,
                        xParts, yParts)
                translateX = translation[0]
                translateY = translation[1]
            } else if (i % xParts == xParts - 1) {
                translation = sideTranslation(heightCount, -bmpWidth,
                        bmpHeight, xParts, yParts)
                translateX = translation[0]
                translateY = translation[1]
            } else {
                if (widthCount == middleXPart) {
                    if (heightCount == 0) {
                        translateX = 0
                        if (yParts != 1) {
                            translateY = -bmpHeight
                        }
                    } else if (heightCount == yParts - 1) {
                        translateX = 0
                        translateY = bmpHeight
                    }
                }
            }
            if (xParts == 1) {
                translation = sideTranslation(heightCount, 0, bmpHeight,
                        xParts, yParts)
                translateX = translation[0]
                translateY = translation[1]
            }
            imageViews[i] = ImageView(view.getContext())
            imageViews[i]
                    .setImageBitmap(Bitmap.createBitmap(viewBmp, bmpWidth
                            * widthCount, bmpHeight * heightCount, bmpWidth,
                            bmpHeight))
            imageViews[i].animate().translationXBy(translateX)
                    .translationYBy(translateY).alpha(0)
                    .setInterpolator(interpolator).setDuration(duration)
            layouts[heightCount].addView(imageViews[i])
            widthCount++
        }
        for (i in 0 until yParts) explodeLayout.addView(layouts[i])
        val positionView: Int = parentView.indexOfChild(view)
        parentView.removeView(view)
        parentView.addView(explodeLayout, positionView)
        val rootView: ViewGroup = explodeLayout.getRootView() as ViewGroup
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(false)
            parentView = parentView.getParent() as ViewGroup
        }
        rootView.setClipChildren(false)
        imageViews[0].animate().setListener(object : AnimatorListenerAdapter() {
            @Override
            fun onAnimationEnd(animation: Animator?) {
                parentView = explodeLayout.getParent() as ViewGroup
                view.setLayoutParams(explodeLayout.getLayoutParams())
                view.setVisibility(View.INVISIBLE)
                parentView.removeView(explodeLayout)
                parentView.addView(view, positionView)
                if (getListener() != null) {
                    getListener().onAnimationEnd(this@ExplodeAnimation)
                }
            }
        })
    }

    private fun sideTranslation(heightCount: Int, bmpWidth: Int, bmpHeight: Int,
                                xParts: Int, yParts: Int): IntArray {
        val translation = IntArray(2)
        val middleYPart = (yParts - 1) / 2
        if (heightCount == 0) {
            translation[0] = -bmpWidth
            translation[1] = -bmpHeight
        } else if (heightCount == yParts - 1) {
            translation[0] = -bmpWidth
            translation[1] = bmpHeight
        }
        if (yParts % 2 != 0) {
            if (heightCount == middleYPart) {
                translation[0] = -bmpWidth
                translation[1] = 0
            }
        }
        return translation
    }

    /**
     * The available matrices are `MATRIX_1X2`,
     * `MATRIX_1X3`, `MATRIX_2X1`, `MATRIX_2X2`
     * , `MATRIX_2X3`, `MATRIX_3X1`,
     * `MATRIX_3X2` and `MATRIX_3X3`.
     *
     * @param matrix
     * The matrix that determines the number of X and Y parts to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setExplodeMatrix(matrix: Int): ExplodeAnimation {
        explodeMatrix = matrix
        xParts = matrix / 10
        yParts = matrix % 10
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
    fun setInterpolator(interpolator: TimeInterpolator): ExplodeAnimation {
        this.interpolator = interpolator
        return this
    }

    /**
     * @param duration
     * The duration of the entire animation to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setDuration(duration: Long): ExplodeAnimation {
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
     *
     * @param listener
     * The listener to set for the end of the animation.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    fun setListener(listener: AnimationListener?): ExplodeAnimation {
        this.listener = listener
        return this
    }

    companion object {
        const val MATRIX_1X2 = 12
        const val MATRIX_1X3 = 13
        const val MATRIX_2X1 = 21
        const val MATRIX_2X2 = 22
        const val MATRIX_2X3 = 23
        const val MATRIX_3X1 = 31
        const val MATRIX_3X2 = 32
        const val MATRIX_3X3 = 33
    }

    /**
     * This animation creates a bitmap of the view, divides them into
     * customizable number of X and Y parts and translates the parts away from
     * the center of the view to mimic an explosion. The number of parts can
     * vary from 1x2 to 3x3. The view is set to invisible and added back for
     * reuse.
     *
     * @param view
     * The view to be animated.
     */
    init {
        view = view
        setExplodeMatrix(MATRIX_3X3)
        interpolator = AccelerateDecelerateInterpolator()
        duration = DURATION_LONG
        listener = null
    }
}