package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class GameModel {
    public String gameId;

    public String startTimeUTC;

    public boolean live;

    public GameModel(final String gameId,
                      final String startTimeUTC,
                      final boolean live) {
        this.gameId = gameId;
        this.startTimeUTC = startTimeUTC;
        this.live = live;
    }

    public static final class Builder {
        private String gameId;

        private String startTimeUTC;

        private boolean live;

        private Builder(final String gameId, final boolean live) {
            this.gameId = gameId;
            this.live = live;
        }

        public Builder withStartTimeUTO(final String startTimeUTC) {
            this.startTimeUTC = startTimeUTC;
            return this;
        }

        public GameModel build() {
            return new GameModel(gameId,
                    startTimeUTC,
                    live);
        }
    }
}
