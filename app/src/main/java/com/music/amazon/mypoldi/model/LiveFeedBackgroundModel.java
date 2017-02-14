package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundModel {

    public final String backgroundImage;

    public final String homeName;

    public final String homeLogo;

    public final String awayName;

    public final String awayLogo;

    public final long kickoffTime;

    public static final Builder builder(final String uuid,
                                        final String backgroundImage,
                                        final String homeName,
                                        final String homeLogo,
                                        final String awayName,
                                        final String awayLogo,
                                        final long kickoffTime) {
        return new Builder(
                uuid,
                backgroundImage,
                homeName,
                homeLogo,
                awayName,
                awayLogo,
                kickoffTime);
    }

    public static final class Builder {

        private final String uuid;

        private final String backgroundImage;

        private final String homeName;

        private final String homeLogo;

        private final String awayName;

        private final String awayLogo;

        private final long kickoffTime;

        private Builder(final String uuid,
                        final String backgroundImage,
                        final String homeName,
                        final String homeLogo,
                        final String awayName,
                        final String awayLogo,
                        final long kickoffTime) {
            this.uuid = uuid;
            this.backgroundImage = backgroundImage;
            this.homeName = homeName;
            this.homeLogo = homeLogo;
            this.awayName = awayName;
            this.awayLogo = awayLogo;
            this.kickoffTime = kickoffTime;
        }

        public LiveFeedBackgroundModel build() {
            return new LiveFeedBackgroundModel(uuid,
                    backgroundImage,
                    homeName,
                    homeLogo,
                    awayName,
                    awayLogo,
                    kickoffTime);
        }
    }

    private LiveFeedBackgroundModel(final String uuid,
                                    final String backgroundImage,
                                    final String homeName,
                                    final String homeLogo,
                                    final String awayName,
                                    final String awayLogo,
                                    final long kickoffTime) {
        //super(uuid);
        this.backgroundImage = backgroundImage;
        this.homeName = homeName;
        this.homeLogo = homeLogo;
        this.awayName = awayName;
        this.awayLogo = awayLogo;
        this.kickoffTime = kickoffTime;
    }
}