package com.androidanimator.animation;

import java.util.ArrayList;

/**
 * This class allows the adding of certain Animation objects to an
 * ArrayList<Combinable> to be animated together.
 * 
 * @author SiYao
 * 
 */
public class ParallelAnimator extends Animation {

	ArrayList<Combinable> animationList;
	AnimationListener listener;

	/**
	 * This class allows the adding of certain Animation objects to an
	 * ArrayList<Combinable> to be animated together.
	 */
	public ParallelAnimator() {
		animationList = new ArrayList<Combinable>();
	}

	/**
	 * This method adds this Animation object to an ArrayList<Combinable>.
	 * 
	 * @param combinable
	 *            The Animation object that implements the {@link Combinable}
	 *            interface and is allowed to animate with other animations.
	 * @return This object, allowing calls to methods in this class to be
	 *         chained.
	 */
	public ParallelAnimator add(Combinable combinable) {
		animationList.add(combinable);
		return this;
	}

	/**
	 * This method gets the ArrayList<Combinable> to start each and every
	 * animation stored on it.
	 */
	@Override
	public void animate() {
		for (int i = 0; i < animationList.size(); i++) {
			animationList.get(i).animate();
		}
	}

}
