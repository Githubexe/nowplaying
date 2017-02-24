package com.music.amazon.mypoldi.model;

import com.music.amazon.mypoldi.dmtv.Optional;

/**
 * Created by yoyosu on 2/23/17.
 *
 * A pojo for live feed update
 */
public abstract class LiveFeedItemModel {

    public final Optional<String> time;

    public final Optional<String> comment;

    /**
     * The icon next to the playername
     */
    public final Optional<String> smallImage;

    /**
     * The icon on the timeline bar
     */
    public final Optional<String> largeImage;

    /**
     * Used for home team live feed updates
     */
    public static final class LeftLiveFeedItemModel extends LiveFeedItemModel {
        private LeftLiveFeedItemModel(final String time,
                                      final String comment,
                                      final String smallImage,
                                      final String largeImage) {
            super(time, comment, smallImage, largeImage);
        }
    }

    /**
     * used for away team feed updates
     */
    public static final class RightLiveFeedItemModel extends LiveFeedItemModel {
        private RightLiveFeedItemModel(final String time,
                                       final String comment,
                                       final String smallImage,
                                       final String largeImage) {
            super(time, comment, smallImage, largeImage);
        }
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String time;

        private String comment;

        private String smallImage;

        private String largeImage;

        public Builder withTime(final String time) {
            this.time = time;
            return this;
        }

        public Builder withComment(final String comment) {
            this.comment = comment;
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
                    comment,
                    smallImage,
                    largeImage
            );
        }

        public RightLiveFeedItemModel buildAway() {
            return new RightLiveFeedItemModel(
                    time,
                    comment,
                    smallImage,
                    largeImage
            );
        }
    }

    private LiveFeedItemModel(final String time,
                              final String comment,
                              final String smallImage,
                              final String largeImage) {

        this.time = Optional.ofNullable(time);
        this.comment = Optional.ofNullable(comment);
        this.smallImage = Optional.ofNullable(smallImage);
        this.largeImage = Optional.ofNullable(largeImage);
    }
}
