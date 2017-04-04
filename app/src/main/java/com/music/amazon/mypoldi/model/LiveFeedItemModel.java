package com.music.amazon.mypoldi.model;

import com.music.amazon.mypoldi.dmtv.Optional;

/**
 * A pojo for live feed update
 */
public abstract class LiveFeedItemModel{
    public enum ICON_TYPE {
        RED_CARD,
        YELLOW_CARD,
        YELLOW_RED_CARD,
        GOAL,
        SUBSTITUTION
    }

    public final Optional<String> mainText;

    public final Optional<String> subText;

    public final Optional<ICON_TYPE> imageType1;

    /**
     * The icon next to the player name
     */
    public final Optional<String> image1;

    /**
     * The icon on the timeline bar
     */
    public final Optional<String> image2;

    /**
     * Used for home team live feed updates
     */
    public static final class HomeLiveFeedItemModel extends LiveFeedItemModel {
        private HomeLiveFeedItemModel(
                final String uuid,
                final String mainText,
                final String subText,
                final String image1,
                final String image2,
                final ICON_TYPE imageType1) {
            super(uuid, mainText, subText, image1, image2, imageType1);
        }
    }

    /**
     * used for away team feed updates
     */
    public static final class AwayLiveFeedItemModel extends LiveFeedItemModel {
        private AwayLiveFeedItemModel(
                final String uuid,
                final String mainText,
                final String subText,
                final String image1,
                final String image2,
                final ICON_TYPE imageType1) {
            super(uuid, mainText, subText, image1, image2, imageType1);
        }
    }

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private String uuid;

        private String mainText;

        private String subText;

        private String image1;

        private String image2;

        private ICON_TYPE imageType1;

        private Builder (String uuid) {
            this.uuid = uuid;
        }

        public Builder withMainText(final String mainText) {
            this.mainText = mainText;
            return this;
        }

        public Builder withSubText(final String subText) {
            this.subText = subText;
            return this;
        }

        public Builder withImage1(final String image1) {
            this.image1 = image1;
            return this;
        }

        public Builder withImage2(final String image2) {
            this.image2 = image2;
            return this;
        }

        public Builder withImageType1(final ICON_TYPE imageType1) {
            this.imageType1 = imageType1;
            return this;
        }

        public HomeLiveFeedItemModel buildHome() {
            return new HomeLiveFeedItemModel(
                    uuid,
                    mainText,
                    subText,
                    image1,
                    image2,
                    imageType1
            );
        }

        public AwayLiveFeedItemModel buildAway() {
            return new AwayLiveFeedItemModel(
                    uuid,
                    mainText,
                    subText,
                    image1,
                    image2,
                    imageType1
            );
        }
    }

    private LiveFeedItemModel(
            final String uuid,
            final String mainText,
            final String subText,
            final String image1,
            final String image2,
            final ICON_TYPE imageType1) {
        this.mainText = Optional.ofNullable(mainText);
        this.subText = Optional.ofNullable(subText);
        this.image1 = Optional.ofNullable(image1);
        this.image2 = Optional.ofNullable(image2);
        this.imageType1 = Optional.ofNullable(imageType1);
    }
}
