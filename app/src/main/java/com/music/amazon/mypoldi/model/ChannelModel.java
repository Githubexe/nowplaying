package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelModel {
    public String channelId;

    public String startTimeUTC;

    public boolean live;

    public ChannelModel(final String channelId,
                        final String startTimeUTC,
                        final boolean live) {
        this.channelId = channelId;
        this.startTimeUTC = startTimeUTC;
        this.live = live;
    }

    public static final class Builder {
        private String channelId;

        private String startTimeUTC;

        private boolean live;

        private Builder(final String channelId, final boolean live) {
            this.channelId = channelId;
            this.live = live;
        }

        public Builder withStartTimeUTO(final String startTimeUTC) {
            this.startTimeUTC = startTimeUTC;
            return this;
        }

        public ChannelModel build() {
            return new ChannelModel(channelId,
                    startTimeUTC,
                    live);
        }
    }
}
