package com.example.myapplication.peserta.section;

import android.text.InputFilter;
import android.text.Spanned;

public class MinFilter implements InputFilter {

    private int min;

    public MinFilter(int min) {
        this.min = min;
    }

    public MinFilter(String min, String max) {
        this.min = Integer.parseInt(min);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int c) {
        return a > c ? c >= a  : c <= a;
    }
}
