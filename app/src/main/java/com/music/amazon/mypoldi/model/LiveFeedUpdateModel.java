package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedUpdateModel {
    public int hostScore;

    public int awayScore;

    public String elapsedTime;

    public List<LeftLiveFeedItemModel> events = new ArrayList<LeftLiveFeedItemModel>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private int homeScore;

        private int awayScore;

        private String elapsedTime;

        private List<LeftLiveFeedItemModel> events = new ArrayList<LeftLiveFeedItemModel>();

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

        public Builder withEvents(final List<LeftLiveFeedItemModel> events) {
            this.events = events;
            return this;
        }

        public LiveFeedUpdateModel build() {
            return new LiveFeedUpdateModel(uuid,
                    homeScore,
                    awayScore,
                    elapsedTime,
                    events);
        }
    }

    private LiveFeedUpdateModel(final String uuid,
                                final int hostScore,
                                final int awayScore,
                                final String elapsedTime,
                                final List<LeftLiveFeedItemModel> events) {
        //super(uuid);
        this.hostScore = hostScore;
        this.awayScore = awayScore;
        this.elapsedTime = elapsedTime;
        this.events = events;
    }
}
