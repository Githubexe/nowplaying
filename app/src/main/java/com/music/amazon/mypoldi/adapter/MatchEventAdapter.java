package com.music.amazon.mypoldi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.MatchEventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 *
 * Can use UniversalBinder once it can take the dependency
 */
public class MatchEventAdapter extends
        RecyclerView.Adapter<MatchEventAdapter.ViewHolder> {

    private List<MatchEventModel> list = new ArrayList<>();

    public MatchEventAdapter(List<MatchEventModel> list) {
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
    public MatchEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View eventView = LayoutInflater.from( parent.getContext()).
                inflate(R.layout.now_playing_game_event_row_view, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(MatchEventAdapter.ViewHolder viewHolder, int position) {
        MatchEventModel matchEventModel = list.get(position);

        final TextView leftEventTimeTextView = viewHolder.leftEventTimeTextView;
        leftEventTimeTextView.setText(matchEventModel.leftEventTime);

        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(matchEventModel.leftEventDescription);
        leftEventDescriptionTextView.bringToFront();

        final ImageView leftEventImageView = viewHolder.leftEventImageView;
        leftEventImageView.setImageResource(matchEventModel.leftEventIconResId);

        final ImageView leftMarkerImageView = viewHolder.leftMarkerImageView;
        leftMarkerImageView.setImageResource(matchEventModel.leftMarkerIconResId);

        final TextView rightEventTimeTextView = viewHolder.rightEventTimeTextView;
        rightEventTimeTextView.setText(matchEventModel.rightEventTime);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(matchEventModel.rightEventDescription);
        rightEventDescriptionTextView.bringToFront();

        final ImageView rightEventImageView = viewHolder.rightEventImageView;
        rightEventImageView.setImageResource(matchEventModel.rightEventIconResId);

        final ImageView rightMarkerImageView = viewHolder.rightMarkerImageView;
        rightMarkerImageView.setImageResource(matchEventModel.rightMarkerIconResId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}