package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;

import com.music.amazon.mypoldi.binder.NowPlayingBinder;
import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;

/**
 * Created by yoyosu on 1/17/17.
 */
public final class NowPlayingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_activity);

        NowPlayingMainModel model = createNowPlayingMainModel();

        NowPlayingMainView view = (NowPlayingMainView)findViewById(R.id.now_playing_main_view);
        new NowPlayingBinder().bind(view, model);

    }

    private NowPlayingMainModel createNowPlayingMainModel() {
        return NowPlayingMainModel.builder(
                "test-uuid",
                R.drawable.blurred,
                "HOME TEAM",
                R.drawable.host,
                "VISITING TEAM",
                R.drawable.visiting).
                withScore("2 - 1").
                withTime("58 : 22").build();
    }
}
