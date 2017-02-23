package com.music.amazon.mypoldi.model;


import java.util.Collections;
import java.util.List;

/**
 * Created by yoyosu on 2/21/17.
 */
public final class ChannelSwitcherModel {
    public final List<Object> models;

    public ChannelSwitcherModel(final String uuid, final List<Object> models) {
        this.models = Collections.unmodifiableList(models);
    }
}


