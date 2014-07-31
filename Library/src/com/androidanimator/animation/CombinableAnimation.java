package com.androidanimator.animation;

import java.util.ArrayList;

public class CombinableAnimation extends Animation {

	ArrayList<Animation> animationList;
	AnimationListener listener;
	
	public CombinableAnimation combine(Animation combinable) {
		if (animationList == null) {
			animationList = new ArrayList<Animation>();
		}
		animationList.add(combinable);
		return this;
	}

	@Override
	public void animate() {
		for (int i = 0; i < animationList.size(); i++) {
			animationList.get(i).animate();
		}
	}

}
