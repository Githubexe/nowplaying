package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.NowPlayingMatchModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchView;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMatchBinder {

    public NowPlayingMatchView createView(Context context) {
        return new NowPlayingMatchView(context);
    }

    public void bind(final NowPlayingMatchView view,
                      final NowPlayingMatchModel model){
        final Picasso picasso = Picasso.with(view.getContext());
        picasso.load(model.backgroundImage)
                .placeholder(R.drawable.now_playing_background)
                .error(R.drawable.now_playing_background)
                .into(view.backgroundImageView);

        picasso.load(model.homeTeamLogo)
                .placeholder(R.drawable.home_team_logo)
                .error(R.drawable.home_team_logo)
                .into(view.homeTeamLogoImageView);
        view.homeTeamNameTextView.setText(model.homeTeamName);

        picasso.load(model.visitingTeamLogo)
                .placeholder(R.drawable.visiting_team_logo)
                .error(R.drawable.visiting_team_logo)
                .into(view.visitingTeamLogoImageView);
        view.visitingTeamNameTextView.setText(model.visitingTeamName);
    }
}
