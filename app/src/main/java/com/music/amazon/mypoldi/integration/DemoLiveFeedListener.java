package com.music.amazon.mypoldi.integration;

/**
 * Created by yoyosu on 2/17/17.
 */

import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

/** package **/ interface DemoLiveFeedListener {
    void onUpdateLiveFeed(LiveFeedModel liveFeed);
    void onUpdateLeftLiveItem(LeftLiveFeedItemModel leftItem);
    void onUpdateRightLiveItem(RightLiveFeedItemModel rightItem);
}
