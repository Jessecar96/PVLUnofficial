package com.jessecar.pvl.api;

import java.io.Serializable;

public class RadioListeners implements Serializable {
    int current;
    int unique;
    int total;

    public int getCurrent() {
        return current;
    }

    public int getUnique() {
        return unique;
    }

    public int getTotal() {
        return total;
    }
}
