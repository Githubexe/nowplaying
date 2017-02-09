package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedModel {
    public String hostTeamScore;

    public String visitingTeamScore;

    public int minutes;

    public int seconds;

    public List<LiveFeedItemModel> events = new ArrayList<LiveFeedItemModel>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String hostTeamScore;

        private String visitingTeamScore;

        private int minutes;

        private int seconds;

        private List<LiveFeedItemModel> events = new ArrayList<LiveFeedItemModel>();

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withHostTeamScore(final String hostTeamScore) {
            this.hostTeamScore = hostTeamScore;
            return this;
        }

        public Builder withVisitingTeamScore(final String visitingTeamScore) {
            this.visitingTeamScore = visitingTeamScore;
            return this;
        }

        public Builder withMinutes(int minutes) {
            this.minutes = minutes;
            return this;
        }

        public Builder withSeconds(int seconds) {
            this.seconds = seconds;
            return this;
        }

        public Builder withEvents(List<LiveFeedItemModel> events) {
            this.events = events;
            return this;
        }

        public LiveFeedModel build() {
            return new LiveFeedModel(uuid,
                    hostTeamScore,
                    visitingTeamScore,
                    minutes,
                    seconds,
                    events);
        }
    }

    private LiveFeedModel(final String uuid,
                          final String hostTeamScore,
                          final String visitingTeamScore,
                          final int minutes,
                          final int seconds,
                          final List<LiveFeedItemModel> events) {
        //super(uuid);
        this.hostTeamScore = hostTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.minutes = minutes;
        this.seconds = seconds;
        this.events = events;
    }
}
