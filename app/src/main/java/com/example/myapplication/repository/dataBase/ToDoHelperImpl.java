package com.example.myapplication.repository.dataBase;

import android.content.Context;

import com.example.myapplication.model.ToDo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class ToDoHelperImpl implements ToDoHelper {

    private ToDoDB toDoDB;

    public ToDoHelperImpl(Context context) {
        toDoDB = ToDoDB.getToDo(context);
    }

    @Override
    public Completable addToDo(ToDo toDo) {
        return toDoDB.getToDoDao().addToDo(toDo);
    }

    @Override
    public Completable updateToDo(ToDo toDo) {
        return toDoDB.getToDoDao().updateToDo(toDo);
    }

    @Override
    public Completable deleteToDo(ToDo toDo) {
        return toDoDB.getToDoDao().deleteTodo(toDo);
    }

    @Override
    public Completable deleteAllToDo() {
        return toDoDB.getToDoDao().deleteAllToDo();
    }

    @Override
    public Observable<List<ToDo>> getSearchToDo(String query) {
        return toDoDB.getToDoDao().getSearchToDo(query);
    }

    @Override
    public Observable<List<ToDo>> getToDos() {
        return toDoDB.getToDoDao().getToDo();
    }
}
