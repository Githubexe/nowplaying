package com.music.amazon.mypoldi.model;

/**
 * A pojo for game event
 */
public final class MatchEventModel {

    public String leftEventTime;

    public String leftEventDescription;

    public int leftEventIconResId;

    public int leftMarkerIconResId;

    public String rightEventTime;

    public String rightEventDescription;

    public int rightEventIconResId;

    public int rightMarkerIconResId;

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String leftEventTime;

        private String leftEventDescription;

        private int leftEventIconResId;

        private int leftMarkerIconResId;

        private String rightEventTime;

        private String rightEventDescription;

        private int rightEventIconResId;

        private int righttMarkerIconResId;

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withLeftEventTime(String eventTime) {
            this.leftEventTime = eventTime;
            return this;
        }

        public Builder withLeftEventDescritpion(String eventDescritpion) {
            this.leftEventDescription = eventDescritpion;
            return this;
        }

        public Builder withLeftEventIconResId(int eventIconResId) {
            this.leftEventIconResId = eventIconResId;
            return this;
        }

        public Builder withLeftMarkerIconResId(int markerIconResId) {
            this.leftMarkerIconResId = markerIconResId;
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

        public Builder withRightEventIconResId(int eventIconResId) {
            this.rightEventIconResId = eventIconResId;
            return this;
        }

        public Builder withRightMarkerIconResId(int markerIconResId) {
            this.righttMarkerIconResId = markerIconResId;
            return this;
        }

        public MatchEventModel build() {
            return new MatchEventModel(uuid,
                    leftEventTime,
                    leftEventDescription,
                    leftEventIconResId,
                    leftMarkerIconResId,
                    rightEventTime,
                    rightEventDescription,
                    rightEventIconResId,
                    righttMarkerIconResId
            );

        }
    }

    private MatchEventModel(final String uuid,
                            final String leftEventTime,
                            final String leftEventDescription,
                            final int leftEventIconResId,
                            final int leftMarkerIconResId,
                            final String rightEventTime,
                            final String rightEventDescription,
                            final int rightEventIconResId,
                            final int rightMarkerIconResId) {
        //super(uuid);
        this.leftEventTime = leftEventTime;
        this.leftEventDescription = leftEventDescription;
        this.leftEventIconResId = leftEventIconResId;
        this.leftMarkerIconResId = leftMarkerIconResId;
        this.rightEventTime = rightEventTime;
        this.rightEventDescription = rightEventDescription;
        this.rightEventIconResId = rightEventIconResId;
        this.rightMarkerIconResId = rightMarkerIconResId;
    }
}