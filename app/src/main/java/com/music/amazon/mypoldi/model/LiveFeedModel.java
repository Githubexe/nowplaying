package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedModel {
    public int hostScore;

    public int awayScore;

    public String elapsedTime;

    public List<LiveFeedItemModel> events = new ArrayList<LiveFeedItemModel>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private int homeScore;

        private int awayScore;

        private String elapsedTime;

        private List<LiveFeedItemModel> events = new ArrayList<LiveFeedItemModel>();

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withScore(final int homeScore,
                                  final int awayScore) {
            this.homeScore = homeScore;
            this.awayScore = awayScore;
            return this;
        }

        public Builder withTime(final String elapsedTime) {
            this.elapsedTime = elapsedTime;
            return this;
        }

        public Builder withEvents(List<LiveFeedItemModel> events) {
            this.events = events;
            return this;
        }

        public LiveFeedModel build() {
            return new LiveFeedModel(uuid,
                    homeScore,
                    awayScore,
                    elapsedTime,
                    events);
        }
    }

    private LiveFeedModel(final String uuid,
                          final int hostScore,
                          final int awayScore,
                          final String elapsedTime,
                          final List<LiveFeedItemModel> events) {
        //super(uuid);
        this.hostScore = hostScore;
        this.awayScore = awayScore;
        this.elapsedTime = elapsedTime;
        this.events = events;
    }
}
