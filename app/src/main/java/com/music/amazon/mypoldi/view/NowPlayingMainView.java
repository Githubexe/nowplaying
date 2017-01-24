package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMainView extends RelativeLayout {

    public ImageView backgroundImageView;

    public TextView homeTeamNameTextView;

    public ImageView homeTeamLogoImageView;

    public TextView visitingTeamNameTextView;

    public ImageView visitingTeamLogoImageView;

    public TextView scoreTextView;

    /**
     *  TBD:
     *  TimeOfMatchView  including game segment when appropriate
     *                   - Pre-game, Halftime (HT), Full Time (FT), Injury Time, Penalty Kicks (PK) and Post-game
     *
     *  ChannelChangeView User should be able to switch to concurrent Live matches, or Conference,
     *                    from within Now Playing Live screen - without having to navigate back out to EPG.
     */

    public NowPlayingMainView(Context context) {
        this(context, null);

    }

    public NowPlayingMainView(Context context, AttributeSet attributeSet) {
       this(context, attributeSet, 0);
    }

    public NowPlayingMainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.now_playing_main_view, this);
        backgroundImageView = (ImageView)findViewById(R.id.now_playing_background_image);
        homeTeamLogoImageView = (ImageView)findViewById(R.id.now_playing_host_team_image);
        visitingTeamLogoImageView = (ImageView)findViewById(R.id.now_playing_visiting_team_image);

    }
}