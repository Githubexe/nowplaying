package com.music.amazon.mypoldi;

import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

import java.util.Calendar;
import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DataProvider {

    public static LiveFeedItemModel createLiveGameEvent() {
        final LiveFeedItemModel.Builder builder = LiveFeedItemModel.builder();

        final Calendar now = Calendar.getInstance();
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        builder.withLeftEventDescritpion("Left event: " + minute + "-" + second)
                .withRightEventDescritpion("Righ event: " + minute  + "-" + second)
                .build();

        if (second % 5 == 0 || second % 13 ==0) {
            builder.withLeftEventTime(minute + "\'" + second + "\"")
                    .withLeftEventIcon("https://amazon.music.poldi/yellow_card_icon.png")
                    .withLeftMarkerImage("https://amazon.music.poldi/yellow_card_marker.png");
        } else if (second % 7 == 0 || second % 11 == 0) {
            builder.withRightEventTime(minute + "\'" + second + "\"")
                    .withRightEventIcon("https://amazon.music.poldi/yellow_card_icon.png")
                    .withRightMarkerImage("https://amazon.music.poldi/yellow_card_marker.png");
        }

       return builder.build();
    }

    public static LiveFeedModel createNowPlayingTimelineModel(final List<LiveFeedItemModel> events) {
        LiveFeedItemModel eventModel = createLiveGameEvent();
        events.add(eventModel);

        return LiveFeedModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withMinutes(0).
                withSeconds(0).
                withEvents(events).build();
    }

    public static LiveFeedBackgroundModel createNowPlayingBackgroundModel(int childId) {
        return LiveFeedBackgroundModel.builder(
                "test-main-uuid",
                "https://amazon.music.poldi/background.png", //background
                "Host Team #" + childId,
                "https://amazon.music.poldi/hostteam.png", //host team logo url
                "Visiting Team #" + childId,
                "https://amazon.music.poldi/visitingteam.png", //visiting team logo url
                System.currentTimeMillis()).build();
    }

}
