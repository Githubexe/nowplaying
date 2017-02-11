package com.music.amazon.mypoldi.binder;

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
public class LiveFeedItemBinder extends
        RecyclerView.Adapter<LiveFeedItemBinder.ViewHolder> {

    private Context context;

    private List<LiveFeedItemModel> list = new ArrayList<>();

    public LiveFeedItemBinder(final List<LiveFeedItemModel> list,
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

            leftEventTimeTextView = (TextView) itemView.findViewById(R.id.left_time_text_view);
            leftEventDescriptionTextView = (TextView) itemView.findViewById(R.id.left_description_text_view);
            leftEventImageView = (ImageView) itemView.findViewById(R.id.left_small_image_view);
            leftMarkerImageView = (ImageView) itemView.findViewById(R.id.left_large_image_view);

            rightEventTimeTextView = (TextView) itemView.findViewById(R.id.right_time_text_view);
            rightEventDescriptionTextView = (TextView) itemView.findViewById(R.id.right_description_text_view);
            rightEventImageView = (ImageView) itemView.findViewById(R.id.right_event_image_view);
            rightMarkerImageView = (ImageView) itemView.findViewById(R.id.right_large_image_view);
        }
    }

    @Override
    public LiveFeedItemBinder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View eventView = LayoutInflater.from( parent.getContext()).
                inflate(R.layout.live_feed_item_view, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(LiveFeedItemBinder.ViewHolder viewHolder, int position) {
        LiveFeedItemModel liveFeedItemModel = list.get(position);
        final Picasso picasso = Picasso.with(context);

        if (liveFeedItemModel.isHome) {
            final TextView leftEventTimeTextView = viewHolder.leftEventTimeTextView;
            leftEventTimeTextView.setText(liveFeedItemModel.time);

            final TextView leftEventDescriptionTextView = viewHolder.leftEventDescriptionTextView;
            leftEventDescriptionTextView.setText(liveFeedItemModel.description);
            leftEventDescriptionTextView.bringToFront();


            if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
                picasso.load(liveFeedItemModel.smallImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(viewHolder.leftEventImageView);
            }

            if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
                picasso.load(liveFeedItemModel.largeImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(viewHolder.leftMarkerImageView);

            }
        } else {
            final TextView rightEventTimeTextView = viewHolder.rightEventTimeTextView;
            rightEventTimeTextView.setText(liveFeedItemModel.time);

            final TextView rightEventDescriptionTextView = viewHolder.rightEventDescriptionTextView;
            rightEventDescriptionTextView.setText(liveFeedItemModel.description);
            rightEventDescriptionTextView.bringToFront();

            if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
                picasso.load(liveFeedItemModel.smallImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(viewHolder.rightEventImageView);
            }

            if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
                picasso.load(liveFeedItemModel.largeImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(viewHolder.rightMarkerImageView);
            }
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