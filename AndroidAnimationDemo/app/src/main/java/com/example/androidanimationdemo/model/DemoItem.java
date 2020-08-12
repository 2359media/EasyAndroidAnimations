package com.example.androidanimationdemo.model;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;

public class DemoItem {

    public static List<DemoAnimation> ITEMS = new ArrayList<DemoAnimation>();

    public static SparseArray<DemoAnimation> ITEM_MAP = new SparseArray<DemoAnimation>();

    static {

    }

    private static void addItem(DemoAnimation item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DemoAnimation {
        public int id;
        public String code;
        public String title;

        public DemoAnimation(int id, String title, String code) {
            this.id = id;
            this.title = title;
            this.code = code;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
