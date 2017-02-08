package com.music.amazon.mypoldi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LiveGameEventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public class MatchLiveEventAdapter extends
        RecyclerView.Adapter<MatchLiveEventAdapter.ViewHolder> {

    private List<LiveGameEventModel> list = new ArrayList<>();

    public MatchLiveEventAdapter(List<LiveGameEventModel> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView leftEventTimeTextView;

        public TextView leftEventDescriptionTextView;

        public ImageView leftEventImageView;

        public ImageView leftMarkerImageView;

        public TextView rightEventTimeTextView;

        public TextView rightEventDescriptionTextView;

        public ImageView rightEventImageView;

        public ImageView rightMarkerImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            leftEventTimeTextView = (TextView) itemView.findViewById(R.id.leftEventTimeText);
            leftEventDescriptionTextView = (TextView) itemView.findViewById(R.id.leftEventDescriptionText);
            leftEventImageView = (ImageView) itemView.findViewById(R.id.leftEventImage);
            leftMarkerImageView = (ImageView) itemView.findViewById(R.id.leftMarkerImage);

            rightEventTimeTextView = (TextView) itemView.findViewById(R.id.rightEventTimeText);
            rightEventDescriptionTextView = (TextView) itemView.findViewById(R.id.rightEventDescriptionText);
            rightEventImageView = (ImageView) itemView.findViewById(R.id.rightEventImage);
            rightMarkerImageView = (ImageView) itemView.findViewById(R.id.rightMarkerImage);
        }
    }

    @Override
    public MatchLiveEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View eventView = LayoutInflater.from( parent.getContext()).
                inflate(R.layout.now_playing_game_event_row_view, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(MatchLiveEventAdapter.ViewHolder viewHolder, int position) {
        LiveGameEventModel liveGameEventModel = list.get(position);

        final TextView leftEventTimeTextView = viewHolder.leftEventTimeTextView;
        leftEventTimeTextView.setText(liveGameEventModel.leftEventTime);

        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(liveGameEventModel.leftEventDescription);
        leftEventDescriptionTextView.bringToFront();

        final ImageView leftEventImageView = viewHolder.leftEventImageView;
        leftEventImageView.setImageResource(liveGameEventModel.leftEventIconResId);

        final ImageView leftMarkerImageView = viewHolder.leftMarkerImageView;
        leftMarkerImageView.setImageResource(liveGameEventModel.leftMarkerIconResId);

        final TextView rightEventTimeTextView = viewHolder.rightEventTimeTextView;
        rightEventTimeTextView.setText(liveGameEventModel.rightEventTime);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(liveGameEventModel.rightEventDescription);
        rightEventDescriptionTextView.bringToFront();

        final ImageView rightEventImageView = viewHolder.rightEventImageView;
        rightEventImageView.setImageResource(liveGameEventModel.rightEventIconResId);

        final ImageView rightMarkerImageView = viewHolder.rightMarkerImageView;
        rightMarkerImageView.setImageResource(liveGameEventModel.rightMarkerIconResId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
