package com.jessecar.pvl.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RadioStreamDeserializer implements JsonDeserializer<RadioStream> {
    @Override public RadioStream deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();

        RadioStream stream = new RadioStream();
        stream.setId(jsonObject.get("id").getAsInt());
        stream.setName(jsonObject.get("name").getAsString());
        stream.setUrl(jsonObject.get("url").getAsString());
        stream.setType(jsonObject.get("type").getAsString());
        stream.setIsDefault(jsonObject.get("is_default").getAsBoolean());
        stream.setStatus(jsonObject.get("status").getAsString());

        if (jsonObject.has("bitrate") && !jsonObject.get("bitrate").isJsonNull())
            stream.setBitrate(Integer.parseInt(jsonObject.get("bitrate").getAsString()));

        if (jsonObject.has("format") && !jsonObject.get("format").isJsonNull())
            stream.setFormat(jsonObject.get("format").getAsString());

        if (jsonObject.has("listeners") && !jsonObject.get("listeners").isJsonArray())
            stream.setListeners(new Gson().fromJson(jsonObject.get("listeners"), RadioListeners.class));

        if (jsonObject.has("current_song") && !jsonObject.get("current_song").isJsonArray())
            stream.setCurrentSong(new Gson().fromJson(jsonObject.get("current_song"), Song.class));

        if (jsonObject.has("song_history") && !jsonObject.get("song_history").isJsonArray())
            stream.setSongHistory((ArrayList<SongHistoryItem>) new Gson().fromJson(jsonObject.get("song_history"), new TypeToken<ArrayList<ArrayList<SongHistoryItem>>>() {
            }.getType()));

        return stream;
    }
}
