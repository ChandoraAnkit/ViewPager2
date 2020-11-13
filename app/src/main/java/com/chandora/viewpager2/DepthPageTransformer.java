package com.chandora.viewpager2;

import android.view.View;

public class DepthPageTransformer extends BaseTransformer {

    private static final float MIN_SCALE = 0.90f;

    @Override
    protected void onTransform(View view, float position) {
        view.setAlpha(1 - position);

        if (position <= 0f) {
//            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        } else if (position <= 1f) {
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));

//            float yPosition = position * view.getHeight();
//            view.setTranslationY(-position * yPosition);

//            float yPosition = position * view.getHeight();
//            view.setTranslationY(0.5f * -yPosition);

//            view.setPivotY(0.5f * view.getHeight());
//            view.setTranslationX(view.getWidth() * -position);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }

    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }

    private static final float min(float val, float min) {
        return val < min ? min : val;
    }

}