package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMatchModel {

    public final String backgroundImage;

    public final String homeTeamName;

    public final String homeTeamLogo;

    public final String visitingTeamName;

    public final String visitingTeamLogo;

    public final long kickoffTime;

    public static final Builder builder(final String uuid,
                                        final String backgroundImage,
                                        final String homeTeamName,
                                        final String homeTeamLogo,
                                        final String visitingTeamName,
                                        final String visitingTeamLogo,
                                        final long kickoffTime) {
        return new Builder(
                uuid,
                backgroundImage,
                homeTeamName,
                homeTeamLogo,
                visitingTeamName,
                visitingTeamLogo,
                kickoffTime);
    }

    public static final class Builder {

        private final String uuid;

        private final String backgroundImage;

        private final String homeTeamName;

        private final String homeTeamLogo;

        private final String visitingTeamName;

        private final String visitingTeamLogo;

        private final long kickoffTime;

        private Builder(final String uuid,
                        final String backgroundImage,
                        final String homeTeamName,
                        final String homeTeamLogo,
                        final String visitingTeamName,
                        final String visitingTeamLogo,
                        final long kickoffTime) {
            this.uuid = uuid;
            this.backgroundImage = backgroundImage;
            this.homeTeamName = homeTeamName;
            this.homeTeamLogo = homeTeamLogo;
            this.visitingTeamName = visitingTeamName;
            this.visitingTeamLogo = visitingTeamLogo;
            this.kickoffTime = kickoffTime;
        }

        public NowPlayingMatchModel build() {
            return new NowPlayingMatchModel(uuid,
                    backgroundImage,
                    homeTeamName,
                    homeTeamLogo,
                    visitingTeamName,
                    visitingTeamLogo,
                    kickoffTime);
        }
    }

    private NowPlayingMatchModel(final String uuid,
                                 final String backgroundImage,
                                 final String homeTeamName,
                                 final String homeTeamLogo,
                                 final String visitingTeamName,
                                 final String visitingTeamLogo,
                                 final long kickoffTime) {
        //super(uuid);
        this.backgroundImage = backgroundImage;
        this.homeTeamName = homeTeamName;
        this.homeTeamLogo = homeTeamLogo;
        this.visitingTeamName = visitingTeamName;
        this.visitingTeamLogo = visitingTeamLogo;
        this.kickoffTime = kickoffTime;
    }
}