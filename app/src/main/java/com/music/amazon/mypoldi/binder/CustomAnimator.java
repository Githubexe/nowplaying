package com.music.amazon.mypoldi.binder;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * Created by yoyosu on 2/27/17.
 */
public final class CustomAnimator {
    private static int lastPosition = -1;

    private static LinearInterpolator interpolator = new LinearInterpolator();

    public static void resetPosition() {
        lastPosition = - 1;
    }

    public static void animate(View view) {
        final RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) view.getTag();
        int adapterPosition = holder.getAdapterPosition();
        if(adapterPosition > lastPosition)      {
            for (Animator anim : getAnimators(holder.itemView)) {
                anim.setDuration(200).start();
                anim.setInterpolator(interpolator);
            }
            lastPosition = adapterPosition;
        } else {
            clear(holder.itemView);
        }
    }

    private static Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", 100, 0)
        };
    }

    private static void clear(View v) {
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

}