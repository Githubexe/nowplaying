package com.music.amazon.mypoldi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedUpdateModel {
    private int hostScore;

    private int awayScore;

    private String elapsedTime;

    private List<LiveFeedItemModel> items = new ArrayList<LiveFeedItemModel>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public int getHostScore() {
        return hostScore;
    }

    public void setHostScore(final int hostScore) {
        this.hostScore = hostScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(final int awayScore) {
        this.awayScore = awayScore;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(final String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void addItem(final LiveFeedItemModel model) {
        items.add(model);
    }

    public void addItems(final List<LiveFeedItemModel> models) {
        items.addAll(models);
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

        public LiveFeedUpdateModel build() {
            return new LiveFeedUpdateModel(uuid,
                    homeScore,
                    awayScore,
                    elapsedTime);
        }
    }

    private LiveFeedUpdateModel(final String uuid,
                                final int hostScore,
                                final int awayScore,
                                final String elapsedTime) {
        //super(uuid);
        this.setHostScore(hostScore);
        this.setAwayScore(awayScore);
        this.setElapsedTime(elapsedTime);
    }
}
