package com.music.amazon.mypoldi;

import com.music.amazon.mypoldi.model.LiveGameEventModel;
import com.music.amazon.mypoldi.model.NowPlayingBackgroundModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;

import java.util.List;

/**
 * DEMO DATA ONLY
 */
public final class DataProvider {

    public static NowPlayingTimelineModel createNowPlayingTimelineModel(final long kickoffTime,
                                                                        final List<LiveGameEventModel> events) {
        final LiveGameEventModel.Builder builder = LiveGameEventModel.builder("demo-only");

        final long current = System.currentTimeMillis();
        final long seconds = (current - kickoffTime) / 1000 % 60;
        final long minutes = (current - kickoffTime) / 1000 / 60;

        builder.withLeftEventDescritpion("Left: " + minutes*60 + seconds)
                .withRightEventDescritpion("Right: " + minutes*60 + seconds)
                .build();

        if (seconds % 5 == 0 || seconds % 13 ==0) {
            builder.withLeftEventTime(minutes + seconds + "\"")
                    .withLeftEventIconResId(R.drawable.yellow_card)
                    .withLeftMarkerIconResId(R.drawable.yellow_marker);
        } else if (seconds % 7 == 0 || seconds % 11 == 0) {
            builder.withRightEventTime(minutes + seconds + "\"")
                    .withRightEventIconResId(R.drawable.yellow_card)
                    .withRightMarkerIconResId(R.drawable.yellow_marker);
        }

        events.add(builder.build());

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withMinutes(Long.toString(minutes)).
                withSeconds(Long.toString(seconds)).
                withEvents(events).build();
    }

    public static NowPlayingBackgroundModel createNowPlayingMainModel(int childId) {
        return NowPlayingBackgroundModel.builder(
                "test-main-uuid",
                R.drawable.now_playing_background,
                "Host Team #" + childId,
                R.drawable.host,
                "Visiting Team #" + childId,
                R.drawable.visiting).build();
    }

}
