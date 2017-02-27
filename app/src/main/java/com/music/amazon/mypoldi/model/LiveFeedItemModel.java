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
     * The icon next to the player name
     */
    public final Optional<String> smallImage;

    /**
     * The icon on the timeline bar
     */
    public final Optional<String> largeImage;

    /**
     * Used for home team live feed updates
     */
    public static final class HomeLiveFeedItemModel extends LiveFeedItemModel {
        private HomeLiveFeedItemModel(
                final String uuid,
                final String time,
                final String comment,
                final String smallImage,
                final String largeImage) {
            super(uuid, time, comment, smallImage, largeImage);
        }
    }

    /**
     * used for away team feed updates
     */
    public static final class AwayLiveFeedItemModel extends LiveFeedItemModel {
        private AwayLiveFeedItemModel(
                final String uuid,
                final String time,
                final String comment,
                final String smallImage,
                final String largeImage) {
            super(uuid, time, comment, smallImage, largeImage);
        }
    }

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private String uuid;

        private String time;

        private String comment;

        private String smallImage;

        private String largeImage;

        private Builder (String uuid) {
            this.uuid = uuid;
        }

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

        public HomeLiveFeedItemModel buildHome() {
            return new HomeLiveFeedItemModel(
                    uuid,
                    time,
                    comment,
                    smallImage,
                    largeImage
            );
        }

        public AwayLiveFeedItemModel buildAway() {
            return new AwayLiveFeedItemModel(
                    uuid,
                    time,
                    comment,
                    smallImage,
                    largeImage
            );
        }
    }

    private LiveFeedItemModel(
            final String uuid,
            final String time,
            final String comment,
            final String smallImage,
            final String largeImage) {
        //super(uuid);
        this.time = Optional.ofNullable(time);
        this.comment = Optional.ofNullable(comment);
        this.smallImage = Optional.ofNullable(smallImage);
        this.largeImage = Optional.ofNullable(largeImage);
    }
}
