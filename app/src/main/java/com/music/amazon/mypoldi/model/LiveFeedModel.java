package com.music.amazon.mypoldi.model;

import com.music.amazon.mypoldi.dmtv.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedModel {
    private int homeScore;

    private int awayScore;

    private Optional<String> elapsedTime;

    private List<LiveFeedItemModel> items = new ArrayList<>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(final int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(final int awayScore) {
        this.awayScore = awayScore;
    }

    public Optional<String> getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(final String elapsedTime) {
        this.elapsedTime = Optional.ofNullable(elapsedTime);
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

        public LiveFeedModel build() {
            return new LiveFeedModel(uuid,
                    homeScore,
                    awayScore,
                    elapsedTime);
        }
    }

    private LiveFeedModel(final String uuid,
                          final int homeScore,
                          final int awayScore,
                          final String elapsedTime) {
        //super(uuid);
        this.setHomeScore(homeScore);
        this.setAwayScore(awayScore);
        this.setElapsedTime(elapsedTime);
    }
}
