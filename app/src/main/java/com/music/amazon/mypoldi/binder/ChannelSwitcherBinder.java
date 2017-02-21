package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.view.View;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.ChannelSwitcherModel;
import com.music.amazon.mypoldi.view.ChannelSwitcherView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelSwitcherBinder implements
        UniversalBinder<ChannelSwitcherView, ChannelSwitcherModel> {

    private final Map<Object, UniversalBinder> binders = new HashMap<>();

    public ChannelSwitcherBinder(final UniversalBinder... binders) {
        for (UniversalBinder binder: binders) {
            this.binders.put(binder.getModelClass(), binder);
        }
    }

    @Override
    public Class<ChannelSwitcherModel> getModelClass() {
        return ChannelSwitcherModel.class;
    }

    @Override
    public ChannelSwitcherView createView(final Context context) {
        return new ChannelSwitcherView(context);
    }

    @Override
    public void bind(final ChannelSwitcherView view,
                     final ChannelSwitcherModel channelSwitcherModel) {
        for (Object model : channelSwitcherModel.models) {
            final UniversalBinder binder = binders.get(model);
            if (binder != null) {
                final View newView = binder.createView(view.getContext());
                view.viewFlipper.addView(newView);
            }
        }
    }
}
