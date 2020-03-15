package com.test.jberet.partition;

import java.io.Serializable;

public class TestState implements Serializable {

    int curIndex;

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }
}
