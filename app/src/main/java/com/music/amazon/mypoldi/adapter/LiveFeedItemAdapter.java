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
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/25/17.
 *
 * Can use UniversalBinder once it can take the dependency
 */
public class LiveFeedItemAdapter extends
        RecyclerView.Adapter<LiveFeedItemAdapter.ViewHolder> {

    private Context context;

    private List<LiveFeedItemModel> list = new ArrayList<>();

    public LiveFeedItemAdapter(final List<LiveFeedItemModel> list,
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
    public LiveFeedItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View eventView = LayoutInflater.from( parent.getContext()).
                inflate(R.layout.live_feed_item_view, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(LiveFeedItemAdapter.ViewHolder viewHolder, int position) {
        LiveFeedItemModel liveFeedItemModel = list.get(position);

        final TextView leftEventTimeTextView = viewHolder.leftEventTimeTextView;
        leftEventTimeTextView.setText(liveFeedItemModel.leftEventTime);

        final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
        leftEventDescriptionTextView.setText(liveFeedItemModel.leftEventDescription);
        leftEventDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(context);
        if (StringUtils.isEmpty(liveFeedItemModel.leftEventIcon) == false) {
            picasso.load(liveFeedItemModel.leftEventIcon)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.leftEventImageView);
        }

        if (StringUtils.isEmpty(liveFeedItemModel.leftMarkerImage) == false) {
            picasso.load(liveFeedItemModel.leftMarkerImage)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.leftMarkerImageView);

        }

        final TextView rightEventTimeTextView = viewHolder.rightEventTimeTextView;
        rightEventTimeTextView.setText(liveFeedItemModel.rightEventTime);

        final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
        rightEventDescriptionTextView.setText(liveFeedItemModel.rightEventDescription);
        rightEventDescriptionTextView.bringToFront();

        if (StringUtils.isEmpty(liveFeedItemModel.rightEventIcon) == false) {
            picasso.load(liveFeedItemModel.rightEventIcon)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.rightEventImageView);
        }

        if (StringUtils.isEmpty(liveFeedItemModel.rightMarkerImage) == false) {
            picasso.load(liveFeedItemModel.rightMarkerImage)
                    .placeholder(R.drawable.yellow_card)
                    .error(R.drawable.yellow_card)
                    .into(viewHolder.rightMarkerImageView);
        }

        if (getItemCount() > 6) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.live_feed_item_up_from_bottom);
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