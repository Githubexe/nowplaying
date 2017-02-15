package com.music.amazon.mypoldi.TestOnly;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.CustomLinearLayoutManager;

/**
 * Created by yoyosu on 2/14/17.
 */
public class Test_RV_View extends RelativeLayout{
    TextView t1;
    TextView t2;

    public Test_RV_View(Context context) {
        this(context, null);
    }

    public Test_RV_View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Test_RV_View(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        LinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(context);
//        setLayoutManager(linearLayoutManager);

        inflate(getContext(), R.layout.test_recycler_view, this);
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
    }

    public void bind(Test_RV_Model data) {
        t1.setText(data.t1);
        t2.setText(data.t2);
    }
}
