package com.androidanimator.animation;

import java.util.ArrayList;

public class CombinableAnimation extends Animation {

	ArrayList<Combinable> animationList;
	AnimationListener listener;
	
	public CombinableAnimation combine(Combinable combinable) {
		if (animationList == null) {
			animationList = new ArrayList<Combinable>();
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
