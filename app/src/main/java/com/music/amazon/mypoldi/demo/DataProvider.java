package com.music.amazon.mypoldi.demo;

import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DataProvider {

    private static int counter = -1;

    public static LiveFeedItemModel createLiveFeedItemModel() {
        final LiveFeedItemModel.Builder builder = LiveFeedItemModel.builder();

        counter++;
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        if (counter % 7 == 0 || counter % 13 == 0 || counter % 17 == 0) {
            builder.withDescritpion("Home - " + counter);
            if (counter % 7 == 0 || counter % 17 == 0) {
//                builder.withTime(minute + "\'")
//                        .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
//                        .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return builder.buildHomeEvent();
        } else {
            builder.withDescritpion("Away - " + counter);
//            if (counter % 6 == 0 || counter % 11 == 0) {
//                builder.withTime(minute + "\'")
//                        .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
//                        .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
//            }
            return builder.buildAwayEvent();
        }
    }

    public static LiveFeedModel createLiveFeedModel() {
        return LiveFeedModel.builder(
                "test-uuid").
                withTime("53 : 29").
                withScore(3, 2).
                withEvents(new ArrayList<LiveFeedItemModel>()).
                build();
    }

    public static LiveFeedBackgroundModel createLiveFeedBackgroundModel(int childId) {
        return LiveFeedBackgroundModel.builder(
                "test-main-uuid",
                "https://amazon.music.poldi/background.png", //background
                "FC BAYERN MÜNCHEN",
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "BORUSSIA DORTMUND",
                "https://amazon.music.poldi/visitingteam.png", //visiting team logo url
                System.currentTimeMillis()).build();
    }

}
