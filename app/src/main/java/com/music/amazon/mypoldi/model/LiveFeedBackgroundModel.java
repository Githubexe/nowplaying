package com.music.amazon.mypoldi.model;

import com.music.amazon.mypoldi.dmtv.Optional;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundModel{

    public final Optional<String> backgroundImage;

    public final Optional<String> homeName;

    public final Optional<String> homeLogo;

    public final Optional<String> awayName;

    public final Optional<String> awayLogo;

    public Optional<com.music.amazon.mypoldi.model.LiveFeedModel> liveFeedModel;

    public static final Builder builder(
                  final String uuid,
                  final String backgroundImage,
                  final String homeName,
                  final String homeLogo,
                  final String awayName,
                  final String awayLogo) {
        return new Builder(
                uuid,
                backgroundImage,
                homeName,
                homeLogo,
                awayName,
                awayLogo);
    }

    public static final class Builder {

        private final String uuid;

        private final String backgroundImage;

        private final String homeName;

        private final String homeLogo;

        private final String awayName;

        private final String awayLogo;

        private com.music.amazon.mypoldi.model.LiveFeedModel liveFeedModel;

        private Builder(final String uuid,
                        final String backgroundImage,
                        final String homeName,
                        final String homeLogo,
                        final String awayName,
                        final String awayLogo) {
            this.uuid = uuid;
            this.backgroundImage = backgroundImage;
            this.homeName = homeName;
            this.homeLogo = homeLogo;
            this.awayName = awayName;
            this.awayLogo = awayLogo;;
        }

        public LiveFeedBackgroundModel build() {
            return new LiveFeedBackgroundModel(
                    backgroundImage,
                    homeName,
                    homeLogo,
                    awayName,
                    awayLogo,
                    liveFeedModel);
        }

        public Builder withLiveFeed(final com.music.amazon.mypoldi.model.LiveFeedModel liveFeedModel) {
            this.liveFeedModel = liveFeedModel;
            return this;
        }
    }

    private LiveFeedBackgroundModel(final String backgroundImage,
                                    final String homeName,
                                    final String homeLogo,
                                    final String awayName,
                                    final String awayLogo,
                                    final com.music.amazon.mypoldi.model.LiveFeedModel liveFeedModel) {
        this.backgroundImage = Optional.ofNullable(backgroundImage);
        this.homeName = Optional.ofNullable(homeName);
        this.homeLogo = Optional.ofNullable(homeLogo);
        this.awayName = Optional.ofNullable(awayName);
        this.awayLogo = Optional.ofNullable(awayLogo);
        this.liveFeedModel = Optional.ofNullable(liveFeedModel);
    }
}