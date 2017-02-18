package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.GameModel;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * DEMO DATA ONLY
 */
public final class DemoLiveFeedData {

    public static int counter = -1;

    public static Set<GameModel> getLiveGames() {
        Set<GameModel> games = new HashSet<>();
        games.add(new GameModel("game1", "2017-02-21 10:30:00", true));
        games.add(new GameModel("game2", "2017-02-26 12:00:00", true));
        games.add(new GameModel("game3", "2017-03-13 18:15:00", true));
        return games;
    }

    public static Object generateLiveFeedItemData() {
        counter++;
        final int minute = Calendar.getInstance().get(Calendar.MINUTE);

        final LeftLiveFeedItemModel.Builder leftItemBuilder = LeftLiveFeedItemModel.builder();
        final RightLiveFeedItemModel.Builder rightItemBuilder = RightLiveFeedItemModel.builder();

        if (counter % 7 == 0 || counter % 11 == 0 || counter % 13 == 0) {
            leftItemBuilder.withDescritpion("Home - " + counter);
            if (counter % 7 == 0) {
                leftItemBuilder.withTime(minute + "\'")
                        .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                        .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return leftItemBuilder.build();
        } else {
            rightItemBuilder.withDescritpion("Away - " + counter);
            if (counter % 5 == 0) {
                rightItemBuilder.withTime(minute + "\'")
                        .withSmallImage("https://amazon.music.poldi/yellow_card_icon.png")
                        .withLargeImage("https://amazon.music.poldi/yellow_card_marker.png");
            }
            return rightItemBuilder.build();
        }
    }

    public static LiveFeedModel createLiveFeedModel(String gameId) {
        return LiveFeedModel.builder(
                "test-uuid").
                withTime("53 : 29").
                withScore(3, 2).
                build();
    }

    public static LiveFeedBackgroundModel getLiveFeedBackgroundModel(String gameId) {
        return LiveFeedBackgroundModel.builder(
                "test-main-uuid",
                "https://amazon.music.poldi/background.png", //background
                "FC BAYERN MÜNCHEN",
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "BORUSSIA DORTMUND",
                "https://amazon.music.poldi/visitingteam.png") //visiting team logo url
                .build();
    }

}
