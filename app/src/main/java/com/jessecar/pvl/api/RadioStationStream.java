package com.jessecar.pvl.api;

import java.io.Serializable;

public class RadioStationStream implements Serializable {
    public int id;
    public String name;
    public String url;
    public String type;
    public boolean is_default;
}
