package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 2/23/17.
 */
public abstract class LiveFeedItemModel {

    public final String time;

    public final String description;

    public final String smallImage;

    public final String largeImage;


    public static final class LeftLiveFeedItemModel extends LiveFeedItemModel {
        private LeftLiveFeedItemModel(final String time,
                                      final String description,
                                      final String smallImage,
                                      final String largeImage) {
            super(time, description, smallImage, largeImage);
        }
    }

    public static final class RightLiveFeedItemModel extends LiveFeedItemModel {
        private RightLiveFeedItemModel(final String time,
                                       final String description,
                                       final String smallImage,
                                       final String largeImage) {
            super(time, description, smallImage, largeImage);
        }
    }

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

        public LeftLiveFeedItemModel buildHome() {
            return new LeftLiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage
            );
        }

        public RightLiveFeedItemModel buildAway() {
            return new RightLiveFeedItemModel(
                    time,
                    description,
                    smallImage,
                    largeImage
            );
        }
    }

    private LiveFeedItemModel(final String time,
                              final String description,
                              final String smallImage,
                              final String largeImage) {

        this.time = time;
        this.description = description;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
    }
}
