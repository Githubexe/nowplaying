package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DemoLiveFeedData {

    public static int counter = -1;

    public static List<Object> getLiveChannels() {
        List<Object> channels = new ArrayList<>();
        LiveFeedBackgroundModel.Builder builder = LiveFeedBackgroundModel.builder(
                "test-main-uuid-1",
                "https://amazon.music.poldi/background.png", //background
                "FC BAYERN MÜNCHEN",
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "BORUSSIA DORTMUND",
                "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        channels.add(0, builder.build());

        builder = LiveFeedBackgroundModel.builder(
                "test-main-uuid-2",
                "https://amazon.music.poldi/background.png", //background
                "GAME2-HOME",
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "GAME2-AWAY",
                "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        channels.add(1, builder.build());

        builder = LiveFeedBackgroundModel.builder(
                "test-main-uuid-2",
                "https://amazon.music.poldi/background.png", //background
                "GAME3-HOME",
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "GAME3-AWAY",
                "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        channels.add(2, builder.build());

        return channels;
    }

    public static LiveFeedItemModel generateLiveFeedItemData() {
        counter++;

        final LiveFeedItemModel.Builder builder = LiveFeedItemModel.builder("");

        final int minute = Calendar.getInstance().get(Calendar.MINUTE);

        if (counter % 7 == 0 || counter % 11 == 0 || counter % 13 == 0) {
            builder.withMainText("Home - " + counter);
            if (counter % 7 == 0) {
                builder.withSubText(minute + "\'")
                .withImage1("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return builder.buildHome();
        } else {
            builder.withMainText("Away - " + counter);
            if (counter % 5 == 0) {
                builder.withSubText(minute + "\'")
                        .withImage1("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return builder.buildAway();
        }
    }

    public static LiveFeedModel generateLiveFeedModel(int channelIndex) {
        LiveFeedModel.Builder builder = LiveFeedModel.builder(
                "test-uuid");
        switch (channelIndex) {
            case 0:
                builder = builder.withGameClock("34").withScore("0", "2");
                break;
            case 1 :
                builder = builder.withGameClock("53 : 29").withScore("3", "2");
                break;
            case 2:
                builder = builder.withGameClock("49 : 26").withScore("1", "1");
                break;
        }
        return builder.build();
    }



}
