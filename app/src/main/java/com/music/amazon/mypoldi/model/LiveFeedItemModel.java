package com.music.amazon.mypoldi.model;

/**
 * A pojo for game event
 */
public final class LiveFeedItemModel {

    public String time;

    public String description;

    public String smallImage;

    public String largeImage;

    public boolean isHome;

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String time;

        private String description;

        private String smallImage;

        private String largeImage;

        public Builder withTime(String time) {
            this.time = time;
            return this;
        }

        public Builder withDescritpion(String description) {
            this.description = description;
            return this;
        }

        public Builder withSmallImage(String smallImage) {
            this.smallImage = smallImage;
            return this;
        }

        public Builder withLargeImage(String largeImage) {
            this.largeImage = largeImage;
            return this;
        }

        public LiveFeedItemModel buildHomeEvent() {
            return new LiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage,
                    true
            );
        }

        public LiveFeedItemModel buildAwayEvent() {
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