package com.music.amazon.mypoldi.model;

import java.util.Set;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class LiveChannelModel {
    public Set<GameModel> games;

    public LiveChannelModel(final Set<GameModel> games) {
        this.games = games;
    }
}



