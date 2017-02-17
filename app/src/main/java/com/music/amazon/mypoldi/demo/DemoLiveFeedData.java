package com.music.amazon.mypoldi.demo;

import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * DEMO DATA ONLY
 */
public final class DemoLiveFeedData {

    public static int counter = -1;

    public static LeftLiveFeedItemModel createLeftLiveFeedItemModel() {
        final int minute = Calendar.getInstance().get(Calendar.MINUTE);

        final LeftLiveFeedItemModel.Builder builder = LeftLiveFeedItemModel.builder();
        builder.withDescritpion("Home - " + counter);
        if (counter % 7 == 0) {
            builder.withTime(minute + "\'")
                    .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                    .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
        }
        return builder.build();
    }

    public static RightLiveFeedItemModel createRightLiveFeedItemModel() {
        final int minute = Calendar.getInstance().get(Calendar.MINUTE);


        final RightLiveFeedItemModel.Builder builder = RightLiveFeedItemModel.builder();
        builder.withDescritpion("Away - " + counter);
        if (counter % 10 == 0) {
            builder.withTime(minute + "\'")
                    .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                    .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
        }
        return builder.build();

    }

    public static LiveFeedModel createLiveFeedModel() {
        return LiveFeedModel.builder(
                "test-uuid").
                withTime("53 : 29").
                withScore(3, 2).
                withEvents(new ArrayList<LeftLiveFeedItemModel>()).
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
