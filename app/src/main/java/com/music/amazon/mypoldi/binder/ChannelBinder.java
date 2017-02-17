package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.ChannelModel;
import com.music.amazon.mypoldi.view.ChannelView;
/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelBinder implements
        UniversalBinder<ChannelView, ChannelModel> {

    @Override
    public Class<ChannelModel> getModelClass() {
        return ChannelModel.class;
    }

    @Override
    public ChannelView createView(Context context) {
        return new ChannelView(context);
    }

    @Override
    public void bind(ChannelView view, ChannelModel channelModel) {

    }
}
