package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 2/16/17.
 */
public final class RightLiveFeedItemModel {
    public String time;

    public String description;

    public String smallImage;

    public String largeImage;

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

        public RightLiveFeedItemModel build() {
            return new RightLiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage
            );
        }
    }

    private RightLiveFeedItemModel(final String time,
                                  final String description,
                                  final String smallImage,
                                  final String largeImage) {

        this.time = time;
        this.description = description;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
    }
}
