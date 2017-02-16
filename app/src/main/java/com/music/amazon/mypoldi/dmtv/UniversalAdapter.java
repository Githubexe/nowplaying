package com.music.amazon.mypoldi.dmtv;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.music.amazon.mypoldi.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Adapter capable of creating and binding any type of Views.
 * This adapter automatically notifies the view on data changed.
 *
 * Created by baptiste on 5/5/16.
 */
public final class UniversalAdapter
        extends RecyclerView.Adapter<UniversalViewHolder> {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Map<Class, UniversalBinder> BINDERS = new HashMap<>();

    private final List<Class> modelClasses = new ArrayList<>();

    private final List<Object> items = new ArrayList<>();

    private final List<OnFocusChangedListener> focusListeners =
            new ArrayList<>();

    public UniversalAdapter(final UniversalBinder... binders) {
        buildBinders(Arrays.asList(binders));
    }

    public void addItems(final List<?> items) {
        if (items != null && items.size() > 0) {
            this.items.addAll(items);
            notifyDataSetChangedOnUiThread();
        }
    }

    public void addOnItemFocusedListener(
            final OnFocusChangedListener listener) {
        if (listener != null) {
            focusListeners.add(listener);
        }
    }

    public void clearOnItemFocusedListener() {
        if (focusListeners != null) {
            focusListeners.clear();
        }
    }

    @Override
    public int getItemViewType(final int position) {
        final Object model = items.get(position);

        for (int i = 0; i < modelClasses.size(); ++i) {
            if (modelClasses.get(i).equals(model.getClass())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public UniversalViewHolder onCreateViewHolder(final ViewGroup parent,
                                                                                final int viewType) {
        final Class modelClass = modelClasses.get(viewType);

        final UniversalBinder binder = BINDERS.get(modelClass);
        if (binder != null) {
            final View view = binder.createView(parent.getContext());
            return new UniversalViewHolder(view);
        } else {
            throw new RuntimeException("Adapter doesn't know how to inflate " +
                    "and bind " + modelClass +
                    ". Register a UniversalBinder.");
        }
    }

    @Override
    public void onBindViewHolder(final UniversalViewHolder holder,
                                 final int position) {
        final Object model = items.get(position);
        final UniversalBinder binder = BINDERS.get(model.getClass());

        // If the object is already bound, don't rebind
        if (model == holder.getModel()) {
            return;
        }

        holder.setModel(model);
        binder.bind(holder.itemView, model);
        if (getItemCount() > 6) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),
                    R.anim.live_feed_item_up_from_bottom);
            holder.itemView.startAnimation(animation);
        }
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, final boolean hasFocus) {
                for (OnFocusChangedListener listener: focusListeners) {
                    listener.onFocusChanged(view, model, hasFocus);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void buildBinders(final List<UniversalBinder> binders) {
        for (UniversalBinder binder: binders) {
            final Class clazz = binder.getModelClass();

            modelClasses.add(clazz);
            BINDERS.put(clazz, binder);
        }
    }

    private void notifyDataSetChangedOnUiThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(UniversalViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
