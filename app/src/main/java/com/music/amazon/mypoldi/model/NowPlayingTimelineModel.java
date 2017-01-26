package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class NowPlayingTimelineModel {
    public String score;

    public String time;

    public List<GameEvent> events = new ArrayList<GameEvent>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String score;

        private String time;

        private List<GameEvent> events = new ArrayList<GameEvent>();

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withScore(String score) {
            this.score = score;
            return this;
        }

        public Builder withTime(String time) {
            this.time = time;
            return this;
        }

        public Builder withEvents(List<GameEvent> events) {
            this.events = events;
            return this;
        }

        public NowPlayingTimelineModel build() {
            return new NowPlayingTimelineModel(uuid,
                    score,
                    time,
                    events);
        }
    }

    private NowPlayingTimelineModel(final String uuid,
                                final String score,
                                final String time,
                                final List<GameEvent> events) {
        //super(uuid);
        this.score = score;
        this.time = time;
        this.events = events;
    }
}
