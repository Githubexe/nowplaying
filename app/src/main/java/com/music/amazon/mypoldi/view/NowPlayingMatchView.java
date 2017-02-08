package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMatchView extends RelativeLayout {

    public ImageView backgroundImageView;

    public TextView homeTeamNameTextView;

    public ImageView homeTeamLogoImageView;

    public TextView visitingTeamNameTextView;

    public ImageView visitingTeamLogoImageView;

    public NowPlayingMatchDetailsView nowPlayingMatchDetailsView;

    public NowPlayingMatchView(Context context) {
        this(context, null);
    }

    public NowPlayingMatchView(Context context, AttributeSet attributeSet) {
       this(context, attributeSet, 0);
    }

    public NowPlayingMatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.now_playing_background_view, this);
        backgroundImageView = (ImageView)findViewById(R.id.now_playing_background_image);
        homeTeamNameTextView = (TextView)findViewById(R.id.now_playing_host_team_text);
        homeTeamLogoImageView = (ImageView)findViewById(R.id.now_playing_host_team_image);
        visitingTeamLogoImageView = (ImageView)findViewById(R.id.now_playing_visiting_team_image);
        visitingTeamNameTextView = (TextView)findViewById(R.id.now_playing_visiting_team_text);
        nowPlayingMatchDetailsView = (NowPlayingMatchDetailsView)findViewById(R.id.now_playing_timeline_view);
    }
}