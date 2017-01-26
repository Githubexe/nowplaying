package com.music.amazon.mypoldi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 */
public class GameEventAdapter extends
        RecyclerView.Adapter<GameEventAdapter.ViewHolder> {

    private Context context;

    private List<GameEvent> list = new ArrayList<>();

    public GameEventAdapter(Context context) {
        this.context = context;
        init(list);
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView leftEventTimeTextView;

        public TextView leftEventDescriptionTextView;

        public ImageView leftEventImageView;

        public TextView rightEventTimeTextView;

        public TextView rightEventDescriptionTextView;

        public ImageView rightEventImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            leftEventDescriptionTextView = (TextView) itemView.findViewById(R.id.leftEventDescriptionText);
            leftEventImageView = (ImageView) itemView.findViewById(R.id.leftEventImage);

            rightEventDescriptionTextView = (TextView) itemView.findViewById(R.id.rightEventDescriptionText);
            rightEventImageView = (ImageView) itemView.findViewById(R.id.rightEventImage);
        }
    }

    @Override
    public GameEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.now_playing_game_event_row_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(GameEventAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        GameEvent gameEvent = list.get(position);

        // Set item views based on your views and data model
        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(gameEvent.leftEventDescription);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(gameEvent.rightEventDescription);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return list.size();
    }

    private void init(final List<GameEvent> list) {
        list.add(new GameEvent("left event A", "right event A"));
        list.add(new GameEvent("left event B", "right event B"));
    }

    public class GameEvent {
        public String leftEventDescription;
        public String rightEventDescription;

        public GameEvent(final String leftEventDescription, final String rightEventDescription) {
            this.leftEventDescription = leftEventDescription;
            this.rightEventDescription = rightEventDescription;
        }
    }
}
