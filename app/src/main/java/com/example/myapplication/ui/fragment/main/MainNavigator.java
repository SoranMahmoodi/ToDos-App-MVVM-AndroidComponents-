package com.example.myapplication.ui.fragment.main;

import android.view.View;

import com.example.myapplication.model.ToDo;

public interface MainNavigator {

    boolean setOnClickListenerItemToDo(View view, ToDo toDo);

    void setOnCheckChangeCompleted(ToDo toDo);
}
