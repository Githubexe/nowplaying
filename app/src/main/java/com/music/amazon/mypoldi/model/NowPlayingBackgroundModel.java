package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingBackgroundModel {

    public final int backgroundResId;

    public final String homeTeamName;

    public final int iconHomeTeamLogoResId;

    public final String visitingTeamName;

    public final int iconVisitingTeamLogoResId;

    public final long kickoffTime;

    public static final Builder builder(final String uuid,
                                        final int backgroundResId,
                                        final String homeTeamName,
                                        final int iconHomeTeamLogoResId,
                                        final String visitingTeamName,
                                        final int iconVisitingTeamLogoResId,
                                        final long kickoffTime) {
        return new Builder(
                uuid,
                backgroundResId,
                homeTeamName,
                iconHomeTeamLogoResId,
                visitingTeamName,
                iconVisitingTeamLogoResId,
                kickoffTime);
    }

    public static final class Builder {

        private final String uuid;

        private final int backgroundResId;

        private final String homeTeamName;

        private final int iconHomeTeamLogoResId;

        private final String visitingTeamName;

        private final int iconVisitingTeamLogoResId;

        private final long kickoffTime;

        private Builder(final String uuid,
                        final int backgroundResId,
                        final String homeTeamName,
                        final int iconHomeTeamLogoResId,
                        final String visitingTeamName,
                        final int iconVisitingTeamLogoResId,
                        final long kickoffTime) {
            this.uuid = uuid;
            this.backgroundResId = backgroundResId;
            this.homeTeamName = homeTeamName;
            this.iconHomeTeamLogoResId = iconHomeTeamLogoResId;
            this.visitingTeamName = visitingTeamName;
            this.iconVisitingTeamLogoResId = iconVisitingTeamLogoResId;
            this.kickoffTime = kickoffTime;
        }

        public NowPlayingBackgroundModel build() {
            return new NowPlayingBackgroundModel(uuid,
                    backgroundResId,
                    homeTeamName,
                    iconHomeTeamLogoResId,
                    visitingTeamName,
                    iconVisitingTeamLogoResId,
                    kickoffTime);
        }
    }

    private NowPlayingBackgroundModel(final String uuid,
                                      final int backgroundResId,
                                      final String homeTeamName,
                                      final int iconHomeTeamLogoResId,
                                      final String visitingTeamName,
                                      final int iconVisitingTeamLogoResId,
                                      final long kickoffTime) {
        //super(uuid);
        this.backgroundResId = backgroundResId;
        this.homeTeamName = homeTeamName;
        this.iconHomeTeamLogoResId = iconHomeTeamLogoResId;
        this.visitingTeamName = visitingTeamName;
        this.iconVisitingTeamLogoResId = iconVisitingTeamLogoResId;
        this.kickoffTime = kickoffTime;
    }
}



