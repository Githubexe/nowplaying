package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class NowPlayingTimelineModel {
    public String hostTeamScore;

    public String visitingTeamScore;

    public String leftTimeStamp;

    public String rightTimeStamp;

    public List<GameEvent> events = new ArrayList<GameEvent>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String hostTeamScore;

        private String visitingTeamScore;

        private String leftTimeStamp;

        private String rightTimeStamp;

        private List<GameEvent> events = new ArrayList<GameEvent>();

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

        public Builder withLeftTimeStamp(String timeStamp) {
            this.leftTimeStamp = timeStamp;
            return this;
        }

        public Builder withRightTimeStamp(String timeStamp) {
            this.rightTimeStamp = timeStamp;
            return this;
        }

        public Builder withEvents(List<GameEvent> events) {
            this.events = events;
            return this;
        }

        public NowPlayingTimelineModel build() {
            return new NowPlayingTimelineModel(uuid,
                    hostTeamScore,
                    visitingTeamScore,
                    leftTimeStamp,
                    rightTimeStamp,
                    events);
        }
    }

    private NowPlayingTimelineModel(final String uuid,
                                    final String hostTeamScore,
                                    final String visitingTeamScore,
                                    final String leftTimeStamp,
                                    final String rightTimeStamp,
                                    final List<GameEvent> events) {
        //super(uuid);
        this.hostTeamScore = hostTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.leftTimeStamp = leftTimeStamp;
        this.rightTimeStamp = rightTimeStamp;
        this.events = events;
    }
}
