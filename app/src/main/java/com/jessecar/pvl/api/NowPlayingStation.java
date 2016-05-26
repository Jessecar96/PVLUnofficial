package com.jessecar.pvl.api;

import java.io.Serializable;
import java.util.List;

public class NowPlayingStation implements Serializable {
    public String status;
    public RadioStation station;
    public List<RadioStream> streams;
}
