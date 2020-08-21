package com.kelvincodes.searchablespinnerview;

public class SpinnerObjIndex {
    Object object;
    int index;

    public SpinnerObjIndex(Object object, int index) {
        this.object = object;
        this.index = index;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
