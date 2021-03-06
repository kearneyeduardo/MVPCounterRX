package com.globant.counter.android.mvp.model;

public class CountModel {

    private int count = 0;

    public void reset() {
        count = 0;
    }

    public void inc() {
        count += 1;
    }

    public int getCount() {
        return count;
    }

    public int getResult(int a, int b) {
        return a + b;
    }

}
