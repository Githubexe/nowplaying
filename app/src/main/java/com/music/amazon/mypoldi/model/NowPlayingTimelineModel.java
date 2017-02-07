package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class NowPlayingTimelineModel {
    public String hostTeamScore;

    public String visitingTeamScore;

    public String minutes;

    public String seconds;

    public List<LiveGameEventModel> events = new ArrayList<LiveGameEventModel>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String hostTeamScore;

        private String visitingTeamScore;

        private String minutes;

        private String seconds;

        private List<LiveGameEventModel> events = new ArrayList<LiveGameEventModel>();

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

        public Builder withMinutes(String minutes) {
            this.minutes = minutes;
            return this;
        }

        public Builder withSeconds(String seconds) {
            this.seconds = seconds;
            return this;
        }

        public Builder withEvents(List<LiveGameEventModel> events) {
            this.events = events;
            return this;
        }

        public NowPlayingTimelineModel build() {
            return new NowPlayingTimelineModel(uuid,
                    hostTeamScore,
                    visitingTeamScore,
                    minutes,
                    seconds,
                    events);
        }
    }

    private NowPlayingTimelineModel(final String uuid,
                                    final String hostTeamScore,
                                    final String visitingTeamScore,
                                    final String minutes,
                                    final String seconds,
                                    final List<LiveGameEventModel> events) {
        //super(uuid);
        this.hostTeamScore = hostTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.minutes = minutes;
        this.seconds = seconds;
        this.events = events;
    }
}
