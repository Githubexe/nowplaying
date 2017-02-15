package com.music.amazon.mypoldi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.music.amazon.mypoldi.binder.CustomLinearLayoutManager;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.TestOnly.Test_RV_View;
import com.music.amazon.mypoldi.TestOnly.Test_RV_Binder;
import com.music.amazon.mypoldi.TestOnly.Test_RV_Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.test_recycler_view);
        final UniversalAdapter adapter = new UniversalAdapter(new Test_RV_Binder());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));
        final List<Test_RV_Model> items = new ArrayList<>();
        items.add(new Test_RV_Model("a", "b"));
        items.add(new Test_RV_Model("c", "d"));
        adapter.addItems(items);


        Button button = (Button)findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        LiveFeedMainActivity.class);
                startActivity(intent);
            }
        });



    }
}
