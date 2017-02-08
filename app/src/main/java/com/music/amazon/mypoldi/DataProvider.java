package com.music.amazon.mypoldi;

import com.music.amazon.mypoldi.model.LiveGameEventModel;
import com.music.amazon.mypoldi.model.NowPlayingBackgroundModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;

import java.util.Calendar;
import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DataProvider {

    public static LiveGameEventModel createLiveGameEvent() {
        final LiveGameEventModel.Builder builder = LiveGameEventModel.builder("demo-only");

        final Calendar now = Calendar.getInstance();
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        builder.withLeftEventDescritpion("Left event: " + minute + "-" + second)
                .withRightEventDescritpion("Righ event: " + minute  + "-" + second)
                .build();

        if (second % 5 == 0 || second % 13 ==0) {
            builder.withLeftEventTime(minute + "\'" + second + "\"")
                    .withLeftEventIconResId(R.drawable.yellow_card)
                    .withLeftMarkerIconResId(R.drawable.yellow_marker);
        } else if (second % 7 == 0 || second % 11 == 0) {
            builder.withRightEventTime(minute + "\'" + second + "\"")
                    .withRightEventIconResId(R.drawable.yellow_card)
                    .withRightMarkerIconResId(R.drawable.yellow_marker);
        }

       return builder.build();
    }

    public static NowPlayingTimelineModel createNowPlayingTimelineModel(final List<LiveGameEventModel> events) {
        LiveGameEventModel eventModel = createLiveGameEvent();
        events.add(eventModel);

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withMinutes(0).
                withSeconds(0).
                withEvents(events).build();
    }

    public static NowPlayingBackgroundModel createNowPlayingBackgroundModel(int childId) {
        return NowPlayingBackgroundModel.builder(
                "test-main-uuid",
                R.drawable.now_playing_background,
                "Host Team #" + childId,
                R.drawable.host,
                "Visiting Team #" + childId,
                R.drawable.visiting,
                System.currentTimeMillis()).build();
    }

}
