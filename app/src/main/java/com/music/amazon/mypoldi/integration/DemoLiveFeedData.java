package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.ChannelModel;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedUpdateModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DemoLiveFeedData {

    public static int counter = -1;

    public static LiveFeedBackgroundModel generateLiveFeedBackgroundModel(String gameId) {
        final LiveFeedBackgroundModel.Builder builder;
        if ("game1".equalsIgnoreCase(gameId)) {
            builder = LiveFeedBackgroundModel.builder(
                    "test-main-uuid-1",
                    "https://amazon.music.poldi/background.png", //background
                    "FC BAYERN MÜNCHEN",
                    "https://amazon.music.poldi/hostteam.png", //host team logo url
                    "BORUSSIA DORTMUND",
                    "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        } else if ("game2".equalsIgnoreCase(gameId)) {
            builder = LiveFeedBackgroundModel.builder(
                    "test-main-uuid-2",
                    "https://amazon.music.poldi/background.png", //background
                    "GAME2-HOME",
                    "https://amazon.music.poldi/hostteam.png", //host team logo url
                    "GAME2-AWAY",
                    "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        } else {
            builder = LiveFeedBackgroundModel.builder(
                    "test-main-uuid-2",
                    "https://amazon.music.poldi/background.png", //background
                    "GAME3-HOME",
                    "https://amazon.music.poldi/hostteam.png", //host team logo url
                    "GAME3-AWAY",
                    "https://amazon.music.poldi/visitingteam.png"); //visiting team logo url
        }
        return builder.build();
    }

    public static List<LiveFeedBackgroundModel> getLiveChannels() {
        List<LiveFeedBackgroundModel> channels = new ArrayList<>();
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

    public static Object generateLiveFeedItemData() {
        counter++;

        final LeftLiveFeedItemModel.Builder leftItemBuilder = LeftLiveFeedItemModel.builder();
        final RightLiveFeedItemModel.Builder rightItemBuilder = RightLiveFeedItemModel.builder();

        if (counter % 7 == 0 || counter % 11 == 0 || counter % 13 == 0) {
            leftItemBuilder.withDescritpion("Home - " + counter);
            if (counter % 7 == 0) {
                //leftItemBuilder.withTime(minute + "\'");
                        //.withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                        //.withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return leftItemBuilder.build();
        } else {
            rightItemBuilder.withDescritpion("Away - " + counter);
            if (counter % 5 == 0) {
                //rightItemBuilder.withTime(minute + "\'");
                        //.withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                        //.withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return rightItemBuilder.build();
        }
    }

    public static LiveFeedUpdateModel generateLiveFeedHeaderModel(int channelIndex) {
        LiveFeedUpdateModel.Builder builder = LiveFeedUpdateModel.builder(
                "test-uuid");
        switch (channelIndex) {
            case 0:
                builder = builder.withTime("34 : 23").withScore(0, 2);
                break;
            case 1 :
                builder = builder.withTime("53 : 29").withScore(3, 2);
                break;
            case 2:
                builder = builder.withTime("49 : 26").withScore(1, 1);
                break;
        }
        return builder.build();
    }



}
