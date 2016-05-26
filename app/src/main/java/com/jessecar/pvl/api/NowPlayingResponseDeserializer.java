package com.jessecar.pvl.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NowPlayingResponseDeserializer implements JsonDeserializer<NowPlayingList> {

    @Override public NowPlayingList deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        List<NowPlayingStation> stations = new ArrayList<NowPlayingStation>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            NowPlayingStation station = context.deserialize(entry.getValue(), NowPlayingStation.class);
            stations.add(station);
        }
        return new NowPlayingList(stations);
    }
}
