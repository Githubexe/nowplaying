package com.music.amazon.mypoldi.model;
import com.music.amazon.mypoldi.dmtv.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class LiveFeedModel {
    private String homeScore;

    private String awayScore;

    private Optional<String> gameClock;

    private Optional<String> matchDate;

    private Optional<String> matchTime;

    private List<LiveFeedItemModel> items = new ArrayList<>();

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(final String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public List<LiveFeedItemModel> getLiveFeedItems() {
        return items;
    }

    public void setAwayScore(final String awayScore) {
        this.awayScore = awayScore;
    }

    public Optional<String> getGameClock() {
        return gameClock;
    }

    public Optional<String> getMatchDate() {
        return matchDate;
    }

    public Optional<String> getMatchTime() {
        return matchTime;
    }

    public void setGameClock(final String gameClock) {
        this.gameClock = Optional.ofNullable(gameClock);
    }

    public void setMatchDate(final String matchDate) {
        this.matchDate = Optional.ofNullable(matchDate);
    }

    public void setMatchTime(final String matchTime) {
        this.matchTime = Optional.ofNullable(matchTime);
    }

    public void addItem(final LiveFeedItemModel model) {
        items.add(model);
    }

    public void addItems(final List<LiveFeedItemModel> models) {
        items.addAll(models);
    }

    public static final class Builder {

        private final String uuid;

        private String homeScore;

        private String awayScore;

        private String gameClock;

        private String matchDate;

        private String matchTime;

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withScore(
                final String homeScore,
                final String awayScore) {
            this.homeScore = homeScore;
            this.awayScore = awayScore;
            return this;
        }

        public Builder withGameClock(final String gameClock) {
            this.gameClock = gameClock;
            return this;
        }

        public Builder withMatchDate(final String matchDate) {
            this.matchDate = matchDate;
            return this;
        }

        public Builder withMatchTime(final String matchTime) {
            this.matchTime = matchTime;
            return this;
        }

        public LiveFeedModel build() {
            return new LiveFeedModel(
                    homeScore,
                    awayScore,
                    gameClock,
                    matchDate,
                    matchTime);
        }
    }

    private LiveFeedModel(
                          final String homeScore,
                          final String awayScore,
                          final String gameClock,
                          final String matchDate,
                          final String matchTime) {
        this.setHomeScore(homeScore);
        this.setAwayScore(awayScore);
        this.setGameClock(gameClock);
        this.setMatchDate(matchDate);
        this.setMatchTime(matchTime);
    }
}
