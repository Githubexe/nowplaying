package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.SmoothScrollLinearLayoutManager;
import com.music.amazon.mypoldi.binder.LeftLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.RightLiveFeedItemBinder;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class LiveFeedHeaderView extends RelativeLayout {

    public final RecyclerView liveFeedItemView;

    public final TextView hostTeamScoreTextView;

    public final TextView visitingTeamScoreTextView;

    public final TextView minutesTextView;

    public final TextView secondsTextView;

    public final TextView timeStampSeparator;

    public final TextView scoreSeparator;

    private final UniversalAdapter universalAdapter;

    public LiveFeedHeaderView(Context context) {
        this(context, null);
    }

    public LiveFeedHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_header_view, this);
        hostTeamScoreTextView = (TextView)findViewById(R.id.host_score_text_view);
        visitingTeamScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);
        secondsTextView = (TextView)findViewById(R.id.second_text_view);
        timeStampSeparator = (TextView)findViewById(R.id.minute_second_separator_text_view);

        liveFeedItemView = (RecyclerView) findViewById(R.id.live_feed_item_view);

        universalAdapter = new UniversalAdapter(
                new LeftLiveFeedItemBinder(),
                new RightLiveFeedItemBinder());
        liveFeedItemView.setAdapter(universalAdapter);
        final SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(context);
        smoothScrollLinearLayoutManager.setStackFromEnd(true);
        liveFeedItemView.setLayoutManager(smoothScrollLinearLayoutManager);
    }

    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel data) {
        List<LeftLiveFeedItemModel> added = new ArrayList<>();
        added.add(data);
        universalAdapter.addItems(added);
        liveFeedItemView.smoothScrollToPosition(
                universalAdapter.getItemCount() - 1);
    }

    public void onUpdateRightLiveItem(RightLiveFeedItemModel data) {
        List<RightLiveFeedItemModel> added = new ArrayList<>();
        added.add(data);
        universalAdapter.addItems(added);
        liveFeedItemView.smoothScrollToPosition(
                universalAdapter.getItemCount() - 1);
    }
}
