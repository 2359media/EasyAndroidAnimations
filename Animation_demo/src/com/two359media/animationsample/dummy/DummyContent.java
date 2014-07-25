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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem(1, "Blind"));
        addItem(new DummyItem(2, "Shake"));
        addItem(new DummyItem(3, "Drop"));
        addItem(new DummyItem(4, "Explode"));
        addItem(new DummyItem(5, "Fly"));
        addItem(new DummyItem(6, "Fold"));
        addItem(new DummyItem(7, "Highlight"));
        addItem(new DummyItem(8, "Path"));
        addItem(new DummyItem(9, "Puff"));
        addItem(new DummyItem(10, "Pulsate"));
        addItem(new DummyItem(11, "Scale"));
        addItem(new DummyItem(12, "Size"));
        addItem(new DummyItem(13, "Slide In"));
        addItem(new DummyItem(14, "Slide Out"));
        addItem(new DummyItem(15, "Slide Out Underneath"));
        addItem(new DummyItem(16, "Transfer"));
        addItem(new DummyItem(17, "Flip"));
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
