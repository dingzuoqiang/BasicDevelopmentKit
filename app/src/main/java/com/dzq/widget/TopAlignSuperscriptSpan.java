package com.dzq.widget;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.SuperscriptSpan;

/**
 * Created by dipingye on 15-6-9.
 */
public class TopAlignSuperscriptSpan extends SuperscriptSpan {
    //divide superscript by this number
    protected float mFontScale = 0.5f;

    //shift value, 0 to 1.0
    protected float mShiftPercentage = 0;

    //doesn't shift
    public TopAlignSuperscriptSpan() {
    }

    //sets the shift percentage
    public TopAlignSuperscriptSpan(float fontScale, float shiftPercentage) {
        if (shiftPercentage > 0.0 && shiftPercentage < 1.0)
            this.mShiftPercentage = shiftPercentage;

        if (fontScale > 0.0 && fontScale < 1.0)
            this.mFontScale = fontScale;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint tp) {
        //original ascent
        float ascent = tp.ascent();

        //scale down the font
        tp.setTextSize(tp.getTextSize() * mFontScale);

        //get the new font ascent
        float newAscent = tp.getFontMetrics().ascent;

        //move baseline to top of old font, then move down size of new font
        //adjust for errors with shift percentage
        tp.baselineShift += (ascent - ascent * mShiftPercentage)
                - (newAscent - newAscent * mShiftPercentage);
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint tp) {
        updateDrawState(tp);
    }
}
