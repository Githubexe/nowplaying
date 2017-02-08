package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.content.res.Resources;

import com.music.amazon.mypoldi.model.NowPlayingMatchModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchView;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMatchBinder {

    public NowPlayingMatchView createView(Context context) {
        return new NowPlayingMatchView(context);
    }

    public void bind(final NowPlayingMatchView view,
                      final NowPlayingMatchModel model){

        final Resources resources = view.getResources();
        view.backgroundImageView.setImageDrawable(resources.getDrawable(model.backgroundResId));

        view.homeTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconHomeTeamLogoResId));
        view.homeTeamNameTextView.setText(model.homeTeamName);

        view.visitingTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconVisitingTeamLogoResId));
        view.visitingTeamNameTextView.setText(model.visitingTeamName);
    }
}
