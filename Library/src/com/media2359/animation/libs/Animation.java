package com.media2359.animation.libs;

import java.util.HashMap;
import java.util.Map;

import android.animation.Animator.AnimatorListener;
import android.view.View;

public abstract class Animation {

    private AnimatorListener listener;
    protected Map<String, Float> properties;

    public Animation() {
        properties = new HashMap<String, Float>();
        setDuration(Constant.DEFAULT_DURATION);
    };

    public AnimatorListener getListener() {
        return listener;
    }

    public void setListener(AnimatorListener listener) {
        this.listener = listener;
    }

    public int getDuration() {
        return Math.round(properties.get(Constant.PROPERTY_DURATION));
    }

    public void setDuration(int duration) {
        properties.put(Constant.PROPERTY_DURATION, (float) duration);
    }

    /**
     * @param propertyName
     *            it should be define in Constant.java
     * @param value
     *            the param of the animation.
     */
    public void putProperty(String propertyName, float value) {
        properties.put(propertyName, value);
    }
    
    /**
     * @return all of property in for this animation
     */
    public Map<String, Float> getAllProperties(){
        return properties;
    }

    public abstract void performAnimation(View v);

    public abstract void cancel(View v);

    public abstract void reset(View v);

}
