package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class NowPlayingTimelineModel {
    public String score;

    public String time;

    public static final Builder builder(final String uuid) {
        return new Builder(
                uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String score;

        private String time;

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

        public NowPlayingTimelineModel build() {
            return new NowPlayingTimelineModel(uuid,
                    score,
                    time);
        }
    }

    private NowPlayingTimelineModel(final String uuid,
                                final String score,
                                final String time) {
        //super(uuid);
        this.score = score;
        this.time = time;
    }
}
