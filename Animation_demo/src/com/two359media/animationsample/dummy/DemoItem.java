package com.two359media.animationsample.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DemoItem {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();

	static {
		// Animations not added: BounceAnimationPhu, FadeAnimation
		addItem(new DummyItem(1, "Blind"));
		addItem(new DummyItem(2, "Blink"));
		addItem(new DummyItem(3, "Bounce"));
		addItem(new DummyItem(4, "Drop In"));
		addItem(new DummyItem(5, "Drop Out"));
		addItem(new DummyItem(6, "Explode"));
		addItem(new DummyItem(7, "Flip"));
		addItem(new DummyItem(8, "Flip Together"));
		addItem(new DummyItem(9, "Fly In"));
		addItem(new DummyItem(10, "Fly Out"));
		addItem(new DummyItem(11, "Fold"));
		addItem(new DummyItem(12, "Highlight"));
		addItem(new DummyItem(13, "Path"));
		addItem(new DummyItem(14, "Puff In"));
		addItem(new DummyItem(15, "Puff Out"));
		addItem(new DummyItem(16, "Rotate"));
		addItem(new DummyItem(17, "Scale In"));
		addItem(new DummyItem(18, "Scale Out"));
		addItem(new DummyItem(19, "Shake"));
		addItem(new DummyItem(20, "Size"));
		addItem(new DummyItem(21, "Slide In"));
		addItem(new DummyItem(22, "Slide In Underneath"));
		addItem(new DummyItem(23, "Slide Out"));
		addItem(new DummyItem(24, "Slide Out Underneath"));
		addItem(new DummyItem(25, "Transfer"));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public int id;
		public String content;

		public DummyItem(int id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
