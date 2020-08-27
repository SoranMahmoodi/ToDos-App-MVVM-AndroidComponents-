package com.example.myapplication.utiliti;

import android.widget.CompoundButton;

import androidx.databinding.BindingAdapter;

public interface CheckedIsCompleted {
    void onUserChangeChecked(CompoundButton cb, boolean isChecked);

}

