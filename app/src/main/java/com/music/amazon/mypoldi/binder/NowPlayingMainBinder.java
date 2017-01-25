package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.content.res.Resources;

import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingMainBinder {

    public NowPlayingMainView createView(Context context) {
        return new NowPlayingMainView(context);
    }

    public void bind(final NowPlayingMainView view,
                      final NowPlayingMainModel model){

        final Resources resources = view.getResources();
        view.backgroundImageView.setImageDrawable(resources.getDrawable(model.backgroundResId));

        view.homeTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconHomeTeamLogoResId));
        view.homeTeamNameTextView.setText(model.homeTeamName);

        view.visitingTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconVisitingTeamLogoResId));
        view.visitingTeamNameTextView.setText(model.visitingTeamName);
    }
}
