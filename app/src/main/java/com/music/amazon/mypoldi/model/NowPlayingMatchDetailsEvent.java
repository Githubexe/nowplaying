package com.music.amazon.mypoldi.model;

/**
 * A pojo for game event
 */
public final class NowPlayingMatchDetailsEvent {

    public String leftEventTime;

    public String leftEventDescription;

    public String leftEventIcon;

    public String leftMarkerImage;

    public String rightEventTime;

    public String rightEventDescription;

    public String rightEventIcon;

    public String rightMarkerImage;

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String leftEventTime;

        private String leftEventDescription;

        private String leftEventIcon;

        private String leftMarkerImage;

        private String rightEventTime;

        private String rightEventDescription;

        private String rightEventIcon;

        private String righttMarkerImage;

        public Builder withLeftEventTime(String eventTime) {
            this.leftEventTime = eventTime;
            return this;
        }

        public Builder withLeftEventDescritpion(String eventDescritpion) {
            this.leftEventDescription = eventDescritpion;
            return this;
        }

        public Builder withLeftEventIcon(String eventIcon) {
            this.leftEventIcon = eventIcon;
            return this;
        }

        public Builder withLeftMarkerImage(String leftMarkerImage) {
            this.leftMarkerImage = leftMarkerImage;
            return this;
        }

        public Builder withRightEventTime(String eventTime) {
            this.rightEventTime = eventTime;
            return this;
        }

        public Builder withRightEventDescritpion(String eventDescritpion) {
            this.rightEventDescription = eventDescritpion;
            return this;
        }

        public Builder withRightEventIcon(String eventIcon) {
            this.rightEventIcon = eventIcon;
            return this;
        }

        public Builder withRightMarkerImage(String markerImage) {
            this.righttMarkerImage = markerImage;
            return this;
        }

        public NowPlayingMatchDetailsEvent build() {
            return new NowPlayingMatchDetailsEvent(
                    leftEventTime,
                    leftEventDescription,
                    leftEventIcon,
                    leftMarkerImage,
                    rightEventTime,
                    rightEventDescription,
                    rightEventIcon,
                    righttMarkerImage
            );

        }
    }

    private NowPlayingMatchDetailsEvent(final String leftEventTime,
                                        final String leftEventDescription,
                                        final String leftEventIcon,
                                        final String leftMarkerImage,
                                        final String rightEventTime,
                                        final String rightEventDescription,
                                        final String rightEventIcon,
                                        final String righttMarkerImage) {
        this.leftEventTime = leftEventTime;
        this.leftEventDescription = leftEventDescription;
        this.leftEventIcon = leftEventIcon;
        this.leftMarkerImage = leftMarkerImage;
        this.rightEventTime = rightEventTime;
        this.rightEventDescription = rightEventDescription;
        this.rightEventIcon = rightEventIcon;
        this.rightMarkerImage = righttMarkerImage;
    }
}