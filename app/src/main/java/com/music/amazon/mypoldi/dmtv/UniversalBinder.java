package com.music.amazon.mypoldi.dmtv;

import android.content.Context;

/**
 * Created by baptiste on 5/4/16.
 */
public interface UniversalBinder<View extends android.view.View, Model> {

    Class<Model> getModelClass();

    View createView(Context context);

    void bind(View view, Model model);
}
