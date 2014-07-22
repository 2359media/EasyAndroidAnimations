package com.androidanimator.animation;

import android.util.Property;
import android.view.View;

/**
 * @author phutang
 * 
 */
public class Constant {
    public static final Property<View, Float> X = View.X;
    public static final Property<View, Float> Y = View.Y;
    public static final Property<View, Float> SCALE_X = View.SCALE_X;
    public static final Property<View, Float> SCALE_Y = View.SCALE_Y;
    public static final Property<View, Float> TRANSLATION_Y = View.TRANSLATION_Y;
    public static final Property<View, Float> TRANSLATION_X = View.TRANSLATION_X;
    public static final String ALPHA = "alpha";

    public static final int DEFAULT_DURATION = 500; // 500ms the default
                                                    // duration for all
                                                    // animations.
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static final int IN = 1;
    public static final int OUT = 2;

    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_DISTANCE = "distance";
    public static final String PROPERTY_AMPLITUDE = "amplitude";
    public static final String PROPERTY_ORITENTION = "Oritention";
    public static final String PROPERTY_TYPE = "type";

    public static final float PROPERTY_GROUP_PLAY_TOGETHER = 1f;

    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_UP = 3;
    public static final int DIRECTION_DOWN = 4;
    public static final int DIRECTION_LEFT_UP = 5;
    public static final int DIRECTION_LEFT_DOWN = 6;
    public static final int DIRECTION_RIGHT_UP = 7;
    public static final int DIRECTION_RIGHT_DOWN = 8;

}
