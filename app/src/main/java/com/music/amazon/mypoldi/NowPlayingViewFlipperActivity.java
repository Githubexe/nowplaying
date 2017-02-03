package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

/**
 * Created by yoyosu on 2/2/17.
 */
public class NowPlayingViewFlipperActivity extends Activity {
    private ViewFlipper viewFlipper;
    private float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_view_flipper);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (viewFlipper.getDisplayedChild() > 0) {
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    viewFlipper.showPrevious();
                    event.startTracking();
                    return true;
                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (viewFlipper.getDisplayedChild() < 2) {
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    viewFlipper.showNext();
                    event.startTracking();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
