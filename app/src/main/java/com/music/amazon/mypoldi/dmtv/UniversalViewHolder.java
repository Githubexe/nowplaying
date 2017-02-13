package com.music.amazon.mypoldi.dmtv;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by baptiste on 5/5/16.
 */
public final class UniversalViewHolder extends RecyclerView.ViewHolder {
    private Object model;

    public UniversalViewHolder(final View itemView) {
        super(itemView);
    }

    /* package */ void setModel(final Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
