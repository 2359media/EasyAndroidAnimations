/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easyandroidanimations.library

import android.content.Context

/**
 * The folding layout where the number of folds, the anchor point and the
 * orientation of the fold can be specified. Each of these parameters can
 * be modified individually and updates and resets the fold to a default
 * (unfolded) state. The fold factor varies between 0 (completely unfolded
 * flat image) to 1.0 (completely folded, non-visible image).
 *
 * This layout throws an exception if there is more than one child added to the view.
 * For more complicated view hierarchy's inside the folding layout, the views should all
 * be nested inside 1 parent layout.
 *
 * This layout folds the contents of its child in real time. By applying matrix
 * transformations when drawing to canvas, the contents of the child may change as
 * the fold takes place. It is important to note that there are jagged edges about
 * the perimeter of the layout as a result of applying transformations to a rectangle.
 * This can be avoided by having the child of this layout wrap its content inside a
 * 1 pixel transparent border. This will cause an anti-aliasing like effect and smoothen
 * out the edges.
 *
 */
class FoldLayout : ViewGroup {
    enum class Orientation {
        VERTICAL, HORIZONTAL
    }

    private val FOLDING_VIEW_EXCEPTION_MESSAGE = "Folding Layout can only 1 child at " +
            "most"
    private val SHADING_ALPHA = 0.8f
    private val SHADING_FACTOR = 0.5f
    private val DEPTH_CONSTANT = 1500
    private val NUM_OF_POLY_POINTS = 8
    private var mFoldRectArray: Array<Rect?>
    private var mMatrix: Array<Matrix?>
    private var mOrientation = Orientation.HORIZONTAL
    private var mAnchorFactor = 0f
    private var mFoldFactor = 0f
    private var mNumberOfFolds = 2
    private var mIsHorizontal = true
    private var mOriginalWidth = 0
    private var mOriginalHeight = 0
    private var mFoldMaxWidth = 0f
    private var mFoldMaxHeight = 0f
    private var mFoldDrawWidth = 0f
    private var mFoldDrawHeight = 0f
    private var mIsFoldPrepared = false
    private var mShouldDraw = true
    private var mSolidShadow: Paint? = null
    private var mGradientShadow: Paint? = null
    private var mShadowLinearGradient: LinearGradient? = null
    private var mShadowGradientMatrix: Matrix? = null
    private var mSrc: FloatArray
    private var mDst: FloatArray
    private var mPreviousFoldFactor = 0f
    private val mFullBitmap: Bitmap? = null
    private var mDstRect: Rect? = null

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    @Override
    protected fun addViewInLayout(child: View?, index: Int, params: LayoutParams?,
                                  preventRequestLayout: Boolean): Boolean {
        throwCustomException(getChildCount())
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }

    @Override
    fun addView(child: View?, index: Int, params: LayoutParams?) {
        throwCustomException(getChildCount())
        super.addView(child, index, params)
    }

    @Override
    protected fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val child: View = getChildAt(0)
        measureChild(child, widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    @Override
    protected fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val child: View = getChildAt(0)
        child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight())
        updateFold()
    }

    /**
     * The custom exception to be thrown so as to limit the number of views in this
     * layout to at most one.
     */
    private inner class NumberOfFoldingLayoutChildrenException(message: String?) : RuntimeException(message)

    /** Throws an exception if the number of views added to this layout exceeds one. */
    private fun throwCustomException(numOfChildViews: Int) {
        if (numOfChildViews == 1) {
            throw NumberOfFoldingLayoutChildrenException(FOLDING_VIEW_EXCEPTION_MESSAGE)
        }
    }

    var anchorFactor: Float
        get() = mAnchorFactor
        set(anchorFactor) {
            if (anchorFactor != mAnchorFactor) {
                mAnchorFactor = anchorFactor
                updateFold()
            }
        }
    var orientation: Orientation
        get() = mOrientation
        set(orientation) {
            if (orientation != mOrientation) {
                mOrientation = orientation
                updateFold()
            }
        }

    /**
     * Sets the fold factor of the folding view and updates all the corresponding
     * matrices and values to account for the new fold factor. Once that is complete,
     * it redraws itself with the new fold.  */
    var foldFactor: Float
        get() = mFoldFactor
        set(foldFactor) {
            if (foldFactor != mFoldFactor) {
                mFoldFactor = foldFactor
                calculateMatrices()
                invalidate()
            }
        }
    var numberOfFolds: Int
        get() = mNumberOfFolds
        set(numberOfFolds) {
            if (numberOfFolds != mNumberOfFolds) {
                mNumberOfFolds = numberOfFolds
                updateFold()
            }
        }

    private fun updateFold() {
        prepareFold(mOrientation, mAnchorFactor, mNumberOfFolds)
        calculateMatrices()
        invalidate()
    }

    /**
     * This method is called in order to update the fold's orientation, anchor
     * point and number of folds. This creates the necessary setup in order to
     * prepare the layout for a fold with the specified parameters. Some of the
     * dimensions required for the folding transformation are also acquired here.
     *
     * After this method is called, it will be in a completely unfolded state by default.
     */
    private fun prepareFold(orientation: Orientation, anchorFactor: Float, numberOfFolds: Int) {
        mSrc = FloatArray(NUM_OF_POLY_POINTS)
        mDst = FloatArray(NUM_OF_POLY_POINTS)
        mDstRect = Rect()
        mFoldFactor = 0f
        mPreviousFoldFactor = 0f
        mIsFoldPrepared = false
        mSolidShadow = Paint()
        mGradientShadow = Paint()
        mOrientation = orientation
        mIsHorizontal = orientation == Orientation.HORIZONTAL
        if (mIsHorizontal) {
            mShadowLinearGradient = LinearGradient(0, 0, SHADING_FACTOR, 0, Color.BLACK,
                    Color.TRANSPARENT, TileMode.CLAMP)
        } else {
            mShadowLinearGradient = LinearGradient(0, 0, 0, SHADING_FACTOR, Color.BLACK,
                    Color.TRANSPARENT, TileMode.CLAMP)
        }
        mGradientShadow.setStyle(Style.FILL)
        mGradientShadow.setShader(mShadowLinearGradient)
        mShadowGradientMatrix = Matrix()
        mAnchorFactor = anchorFactor
        mNumberOfFolds = numberOfFolds
        mOriginalWidth = getMeasuredWidth()
        mOriginalHeight = getMeasuredHeight()
        mFoldRectArray = arrayOfNulls<Rect>(mNumberOfFolds)
        mMatrix = arrayOfNulls<Matrix>(mNumberOfFolds)
        for (x in 0 until mNumberOfFolds) {
            mMatrix[x] = Matrix()
        }
        val h = mOriginalHeight
        val w = mOriginalWidth
        val delta: Int = Math.round(if (mIsHorizontal) w.toFloat() / mNumberOfFolds.toFloat() else h.toFloat() / mNumberOfFolds.toFloat())

        /* Loops through the number of folds and segments the full layout into a number
         * of smaller equal components. If the number of folds is odd, then one of the
         * components will be smaller than all the rest. Note that deltap below handles
         * the calculation for an odd number of folds.*/for (x in 0 until mNumberOfFolds) {
            if (mIsHorizontal) {
                val deltap = if ((x + 1) * delta > w) w - x * delta else delta
                mFoldRectArray[x] = Rect(x * delta, 0, x * delta + deltap, h)
            } else {
                val deltap = if ((x + 1) * delta > h) h - x * delta else delta
                mFoldRectArray[x] = Rect(0, x * delta, w, x * delta + deltap)
            }
        }
        if (mIsHorizontal) {
            mFoldMaxHeight = h.toFloat()
            mFoldMaxWidth = delta.toFloat()
        } else {
            mFoldMaxHeight = delta.toFloat()
            mFoldMaxWidth = w.toFloat()
        }
        mIsFoldPrepared = true
    }

    /*
    * Calculates the transformation matrices used to draw each of the separate folding
    * segments from this view.
    */
    private fun calculateMatrices() {
        mShouldDraw = true
        if (!mIsFoldPrepared) {
            return
        }
        /** If the fold factor is 1 than the folding view should not be seen
         * and the canvas can be left completely empty.  */
        if (mFoldFactor == 1f) {
            mShouldDraw = false
            return
        }
        mPreviousFoldFactor = mFoldFactor

        /* Reset all the transformation matrices back to identity before computing
         * the new transformation */for (x in 0 until mNumberOfFolds) {
            mMatrix[x].reset()
        }
        val cTranslationFactor = 1 - mFoldFactor
        val translatedDistance = if (mIsHorizontal) mOriginalWidth * cTranslationFactor else mOriginalHeight * cTranslationFactor
        val translatedDistancePerFold: Float = Math.round(translatedDistance / mNumberOfFolds)

        /* For an odd number of folds, the rounding error may cause the
         * translatedDistancePerFold to be grater than the max fold width or height. */mFoldDrawWidth = if (mFoldMaxWidth < translatedDistancePerFold) translatedDistancePerFold else mFoldMaxWidth
        mFoldDrawHeight = if (mFoldMaxHeight < translatedDistancePerFold) translatedDistancePerFold else mFoldMaxHeight
        val translatedDistanceFoldSquared = translatedDistancePerFold * translatedDistancePerFold

        /* Calculate the depth of the fold into the screen using pythagorean theorem. */
        val depth = if (mIsHorizontal) Math.sqrt((mFoldDrawWidth * mFoldDrawWidth -
                translatedDistanceFoldSquared).toDouble()) else Math.sqrt((mFoldDrawHeight * mFoldDrawHeight -
                translatedDistanceFoldSquared).toDouble())

        /* The size of some object is always inversely proportional to the distance
        *  it is away from the viewpoint. The constant can be varied to to affect the
        *  amount of perspective. */
        val scaleFactor = DEPTH_CONSTANT / (DEPTH_CONSTANT + depth)
        val scaledWidth: Float
        val scaledHeight: Float
        val bottomScaledPoint: Float
        val topScaledPoint: Float
        val rightScaledPoint: Float
        val leftScaledPoint: Float
        if (mIsHorizontal) {
            scaledWidth = mFoldDrawWidth * cTranslationFactor
            scaledHeight = mFoldDrawHeight * scaleFactor
        } else {
            scaledWidth = mFoldDrawWidth * scaleFactor
            scaledHeight = mFoldDrawHeight * cTranslationFactor
        }
        topScaledPoint = (mFoldDrawHeight - scaledHeight) / 2.0f
        bottomScaledPoint = topScaledPoint + scaledHeight
        leftScaledPoint = (mFoldDrawWidth - scaledWidth) / 2.0f
        rightScaledPoint = leftScaledPoint + scaledWidth
        val anchorPoint = if (mIsHorizontal) mAnchorFactor * mOriginalWidth else mAnchorFactor * mOriginalHeight

        /* The fold along which the anchor point is located. */
        val midFold = if (mIsHorizontal) anchorPoint / mFoldDrawWidth else anchorPoint /
                mFoldDrawHeight
        mSrc[0] = 0
        mSrc[1] = 0
        mSrc[2] = 0
        mSrc[3] = mFoldDrawHeight
        mSrc[4] = mFoldDrawWidth
        mSrc[5] = 0
        mSrc[6] = mFoldDrawWidth
        mSrc[7] = mFoldDrawHeight

        /* Computes the transformation matrix for each fold using the values calculated above. */for (x in 0 until mNumberOfFolds) {
            val isEven = x % 2 == 0
            if (mIsHorizontal) {
                mDst[0] = if (anchorPoint > x * mFoldDrawWidth) anchorPoint + (x - midFold) *
                        scaledWidth else anchorPoint - (midFold - x) * scaledWidth
                mDst[1] = if (isEven) 0 else topScaledPoint
                mDst[2] = mDst[0]
                mDst[3] = if (isEven) mFoldDrawHeight else bottomScaledPoint
                mDst[4] = if (anchorPoint > (x + 1) * mFoldDrawWidth) anchorPoint + (x + 1 - midFold) * scaledWidth else anchorPoint-(midFold-x-1) * scaledWidth
                mDst[5] = if (isEven) topScaledPoint else 0
                mDst[6] = mDst[4]
                mDst[7] = if (isEven) bottomScaledPoint else mFoldDrawHeight
            } else {
                mDst[0] = if (isEven) 0 else leftScaledPoint
                mDst[1] = if (anchorPoint > x * mFoldDrawHeight) anchorPoint + (x - midFold) *
                        scaledHeight else anchorPoint - (midFold - x) * scaledHeight
                mDst[2] = if (isEven) leftScaledPoint else 0
                mDst[3] = if (anchorPoint > (x + 1) * mFoldDrawHeight) anchorPoint + (x + 1 -
                        midFold) * scaledHeight else anchorPoint - (midFold - x - 1) * scaledHeight
                mDst[4] = if (isEven) mFoldDrawWidth else rightScaledPoint
                mDst[5] = mDst[1]
                mDst[6] = if (isEven) rightScaledPoint else mFoldDrawWidth
                mDst[7] = mDst[3]
            }

            /* Pixel fractions are present for odd number of folds which need to be
             * rounded off here.*/for (y in 0..7) {
                mDst[y] = Math.round(mDst[y])
            }

            /* If it so happens that any of the folds have reached a point where
            *  the width or height of that fold is 0, then nothing needs to be
            *  drawn onto the canvas because the view is essentially completely
            *  folded.*/if (mIsHorizontal) {
                if (mDst[4] <= mDst[0] || mDst[6] <= mDst[2]) {
                    mShouldDraw = false
                    return
                }
            } else {
                if (mDst[3] <= mDst[1] || mDst[7] <= mDst[5]) {
                    mShouldDraw = false
                    return
                }
            }

            /* Sets the shadow and bitmap transformation matrices.*/mMatrix[x].setPolyToPoly(mSrc, 0, mDst, 0, NUM_OF_POLY_POINTS / 2)
        }
        /* The shadows on the folds are split into two parts: Solid shadows and gradients.
         * Every other fold has a solid shadow which overlays the whole fold. Similarly,
         * the folds in between these alternating folds also have an overlaying shadow.
         * However, it is a gradient that takes up part of the fold as opposed to a solid
         * shadow overlaying the whole fold.*/

        /* Solid shadow paint object. */
        val alpha = (mFoldFactor * 255 * SHADING_ALPHA).toInt()
        mSolidShadow.setColor(Color.argb(alpha, 0, 0, 0))
        if (mIsHorizontal) {
            mShadowGradientMatrix.setScale(mFoldDrawWidth, 1)
            mShadowLinearGradient.setLocalMatrix(mShadowGradientMatrix)
        } else {
            mShadowGradientMatrix.setScale(1, mFoldDrawHeight)
            mShadowLinearGradient.setLocalMatrix(mShadowGradientMatrix)
        }
        mGradientShadow.setAlpha(alpha)
    }

    @Override
    protected fun dispatchDraw(canvas: Canvas) {
        /** If prepareFold has not been called or if preparation has not completed yet,
         * then no custom drawing will take place so only need to invoke super's
         * onDraw and return.  */
        if (!mIsFoldPrepared || mFoldFactor == 0f) {
            super.dispatchDraw(canvas)
            return
        }
        if (!mShouldDraw) {
            return
        }
        var src: Rect?
        /* Draws the bitmaps and shadows on the canvas with the appropriate transformations. */for (x in 0 until mNumberOfFolds) {
            src = mFoldRectArray[x]
            /* The canvas is saved and restored for every individual fold*/canvas.save()

            /* Concatenates the canvas with the transformation matrix for the
             *  the segment of the view corresponding to the actual image being
             *  displayed. */canvas.concat(mMatrix[x])
            /* The same transformation matrix is used for both the shadow and the image
             * segment. The canvas is clipped to account for the size of each fold and
             * is translated so they are drawn in the right place. The shadow is then drawn on
             * top of the different folds using the sametransformation matrix.*/canvas.clipRect(0, 0, src.right - src.left, src.bottom - src.top)
            if (mIsHorizontal) {
                canvas.translate(-src.left, 0)
            } else {
                canvas.translate(0, -src.top)
            }
            super.dispatchDraw(canvas)
            if (mIsHorizontal) {
                canvas.translate(src.left, 0)
            } else {
                canvas.translate(0, src.top)
            }
            /* Draws the shadows corresponding to this specific fold. */if (x % 2 == 0) {
                canvas.drawRect(0, 0, mFoldDrawWidth, mFoldDrawHeight, mSolidShadow)
            } else {
                canvas.drawRect(0, 0, mFoldDrawWidth, mFoldDrawHeight, mGradientShadow)
            }
            canvas.restore()
        }
    }
}