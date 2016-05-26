package com.jessecar.pvl.api;

import java.util.List;

public class NowPlayingList {
    public List<NowPlayingStation> stations;

    public NowPlayingList(List<NowPlayingStation> stations) {
        this.stations = stations;
    }
}
