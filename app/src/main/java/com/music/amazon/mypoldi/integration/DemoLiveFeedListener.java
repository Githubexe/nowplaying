package com.music.amazon.mypoldi.integration;

/**
 * Created by yoyosu on 2/17/17.
 */

import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedUpdateModel;

public interface DemoLiveFeedListener {
    void onUpdateLiveFeedHeader(LiveFeedUpdateModel liveFeed);
    void onUpdateLiveItem(LiveFeedItemModel leftItem);
}
