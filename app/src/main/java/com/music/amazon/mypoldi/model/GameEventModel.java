package com.music.amazon.mypoldi.model;

/**
 * Created by yoyosu on 1/25/17.
 */
public final class GameEventModel {

    public String leftEventTime;

    public String leftEventDescription;

    public int leftEventIconResId;

    public String rightEventTime;

    public String rightEventDescription;

    public int rightEventIconResId;

    public static final Builder builder(final String uuid) {
        return new Builder(uuid);
    }

    public static final class Builder {

        private final String uuid;

        private String leftEventTime;

        private String leftEventDescription;

        private int leftEventIconResId;

        private String rightEventTime;

        private String rightEventDescription;

        private int rightEventIconResId;

        private Builder(final String uuid) {
            this.uuid = uuid;
        }

        public Builder withLeftEventTime(String eventTime) {
            this.leftEventTime = eventTime;
            return this;
        }

        public Builder withLeftEventIconResId(int eventIconResId) {
            this.leftEventIconResId = eventIconResId;
            return this;
        }

        public Builder withRightEventTime(String eventTime) {
            this.rightEventTime = eventTime;
            return this;
        }

        public Builder withRightEventIconResId(int eventIconResId) {
            this.rightEventIconResId = eventIconResId;
            return this;
        }

        public GameEventModel build() {
            return new GameEventModel(uuid);
        }
    }

    private GameEventModel(final String uuid) {
        //super(uuid);
    }
}
