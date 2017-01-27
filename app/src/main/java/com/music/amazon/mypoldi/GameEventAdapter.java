package com.music.amazon.mypoldi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.model.GameEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public class GameEventAdapter extends
        RecyclerView.Adapter<GameEventAdapter.ViewHolder> {

    private Context context;

    private List<GameEvent> list = new ArrayList<>();

    public GameEventAdapter(Context context, List<GameEvent> list) {
        this.context = context;
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

            leftEventDescriptionTextView = (TextView) itemView.findViewById(R.id.leftEventDescriptionText);
            leftEventImageView = (ImageView) itemView.findViewById(R.id.leftEventImage);
            leftMarkerImageView = (ImageView) itemView.findViewById(R.id.leftMarkerImage);

            rightEventDescriptionTextView = (TextView) itemView.findViewById(R.id.rightEventDescriptionText);
            rightEventImageView = (ImageView) itemView.findViewById(R.id.rightEventImage);
            rightMarkerImageView = (ImageView) itemView.findViewById(R.id.rightMarkerImage);
        }
    }

    @Override
    public GameEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View eventView = inflater.inflate(R.layout.now_playing_game_event_row_view, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(GameEventAdapter.ViewHolder viewHolder, int position) {
        GameEvent gameEvent = list.get(position);

        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(gameEvent.leftEventDescription);

        final ImageView leftEventImageView = viewHolder.leftEventImageView;
        leftEventImageView.setImageResource(gameEvent.leftEventIconResId);

        final ImageView leftMarkerImageView = viewHolder.leftMarkerImageView;
        leftMarkerImageView.setImageResource(gameEvent.leftMarkerIconResId);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(gameEvent.rightEventDescription);

        final ImageView rightEventImageView = viewHolder.rightEventImageView;
        rightEventImageView.setImageResource(gameEvent.rightEventIconResId);

        final ImageView rightMarkerImageView = viewHolder.rightMarkerImageView;
        rightMarkerImageView.setImageResource(gameEvent.rightMarkerIconResId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
