package com.music.amazon.mypoldi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.NowPlayingMatchDetailsEvent;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 *
 * Can use UniversalBinder once it can take the dependency
 */
public class MatchEventAdapter extends
        RecyclerView.Adapter<MatchEventAdapter.ViewHolder> {

    private Context context;

    private List<NowPlayingMatchDetailsEvent> list = new ArrayList<>();

    public MatchEventAdapter(final List<NowPlayingMatchDetailsEvent> list,
                             final Context context) {
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
        NowPlayingMatchDetailsEvent nowPlayingMatchDetailsEvent = list.get(position);

        final TextView leftEventTimeTextView = viewHolder.leftEventTimeTextView;
        leftEventTimeTextView.setText(nowPlayingMatchDetailsEvent.leftEventTime);

        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(nowPlayingMatchDetailsEvent.leftEventDescription);
        leftEventDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(context);
        if (StringUtils.isEmpty(nowPlayingMatchDetailsEvent.leftEventIcon) == false) {
            picasso.load(nowPlayingMatchDetailsEvent.leftEventIcon)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.leftEventImageView);
        }

        if (StringUtils.isEmpty(nowPlayingMatchDetailsEvent.leftMarkerImage) == false) {
            picasso.load(nowPlayingMatchDetailsEvent.leftMarkerImage)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.leftMarkerImageView);

        }

        final TextView rightEventTimeTextView = viewHolder.rightEventTimeTextView;
        rightEventTimeTextView.setText(nowPlayingMatchDetailsEvent.rightEventTime);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(nowPlayingMatchDetailsEvent.rightEventDescription);
        rightEventDescriptionTextView.bringToFront();

        if (StringUtils.isEmpty(nowPlayingMatchDetailsEvent.rightEventIcon) == false) {
            picasso.load(nowPlayingMatchDetailsEvent.rightEventIcon)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.rightEventImageView);
        }

        if (StringUtils.isEmpty(nowPlayingMatchDetailsEvent.rightMarkerImage) == false) {
            picasso.load(nowPlayingMatchDetailsEvent.rightMarkerImage)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.rightMarkerImageView);
        }

        if (getItemCount() > 6) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
            viewHolder.itemView.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }


}