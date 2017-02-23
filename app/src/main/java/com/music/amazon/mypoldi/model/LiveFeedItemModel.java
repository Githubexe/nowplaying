package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 2/23/17.
 */
public final class LiveFeedItemModel {
    public final String time;

    public final String description;

    public final String smallImage;

    public final String largeImage;

    public final boolean isHome;

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String time;

        private String description;

        private String smallImage;

        private String largeImage;

        public Builder withTime(final String time) {
            this.time = time;
            return this;
        }

        public Builder withDescritpion(final String description) {
            this.description = description;
            return this;
        }

        public Builder withSmallImage(final String smallImage) {
            this.smallImage = smallImage;
            return this;
        }

        public Builder withLargeImage(final String largeImage) {
            this.largeImage = largeImage;
            return this;
        }

        public LiveFeedItemModel buildHome() {
            return new LiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage,
                    true
            );
        }

        public LiveFeedItemModel buildAway() {
            return new LiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage,
                    false
            );
        }
    }

    private LiveFeedItemModel(final String time,
                              final String description,
                              final String smallImage,
                              final String largeImage,
                              final boolean isHome) {

        this.time = time;
        this.description = description;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.isHome = isHome;
    }
}
