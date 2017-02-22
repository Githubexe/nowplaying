package com.music.amazon.mypoldi.dmtv;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

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
public class UniversalAdapter
        extends RecyclerView.Adapter<UniversalViewHolder> {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Map<Class, UniversalBinder> BINDERS = new HashMap<>();

    private final List<Class> modelClasses = new ArrayList<>();

    private final List<Object> items = new ArrayList<>();

    private final List<OnFocusChangedListener> focusListeners =
            new ArrayList<>();

    private int lastPosition = -1;

    private LinearInterpolator interpolator = new LinearInterpolator();

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
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, final boolean hasFocus) {
                for (OnFocusChangedListener listener: focusListeners) {
                    listener.onFocusChanged(view, model, hasFocus);
                }
            }
        });

        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition > lastPosition) {
            for (Animator anim : getAnimators(holder.itemView)) {
                anim.setDuration(200).start();
                anim.setInterpolator(interpolator);
            }
            lastPosition = adapterPosition;
        } else {
            clear(holder.itemView);
        }
    }

    private Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", 100, 0)
        };
    }

    private void clear(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
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
    public void onViewDetachedFromWindow(final UniversalViewHolder holder)
    {
        holder.itemView.clearAnimation();
    }

}