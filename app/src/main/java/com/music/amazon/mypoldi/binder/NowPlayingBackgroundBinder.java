package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.content.res.Resources;

import com.music.amazon.mypoldi.model.NowPlayingBackgroundModel;
import com.music.amazon.mypoldi.view.NowPlayingBackgroundView;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class NowPlayingBackgroundBinder {

    public NowPlayingBackgroundView createView(Context context) {
        return new NowPlayingBackgroundView(context);
    }

    public void bind(final NowPlayingBackgroundView view,
                      final NowPlayingBackgroundModel model){

        final Resources resources = view.getResources();
        view.backgroundImageView.setImageDrawable(resources.getDrawable(model.backgroundResId));

        view.homeTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconHomeTeamLogoResId));
        view.homeTeamNameTextView.setText(model.homeTeamName);

        view.visitingTeamLogoImageView.setImageDrawable(resources.getDrawable(model.iconVisitingTeamLogoResId));
        view.visitingTeamNameTextView.setText(model.visitingTeamName);
    }
}
