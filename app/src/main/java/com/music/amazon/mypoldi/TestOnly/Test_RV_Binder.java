package com.music.amazon.mypoldi.TestOnly;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
/**
 * Created by yoyosu on 2/14/17.
 */
public class Test_RV_Binder  implements
        UniversalBinder<Test_RV_View, Test_RV_Model> {
    @Override
    public Class<Test_RV_Model> getModelClass() {
        return Test_RV_Model.class;
    }

    @Override
    public Test_RV_View createView(Context context) {
        return new Test_RV_View(context);
    }

    @Override
    public void bind(Test_RV_View view, Test_RV_Model o) {
        view.t1.setText(o.t1);
        view.t2.setText(o.t2);
    }
}


