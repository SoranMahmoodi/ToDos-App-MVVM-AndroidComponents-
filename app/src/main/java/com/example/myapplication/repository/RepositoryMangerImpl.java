package com.example.myapplication.repository;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.dataBase.ToDoHelperImpl;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class RepositoryMangerImpl implements RepositoryManger {
    private ToDoHelperImpl toDoHelper;

    public RepositoryMangerImpl(ToDoHelperImpl toDoHelper) {
        this.toDoHelper = toDoHelper;
    }

    @Override
    public Completable addToDo(ToDo toDo) {
        return toDoHelper.addToDo(toDo);
    }

    @Override
    public Completable updateToDo(ToDo toDo) {
        return toDoHelper.updateToDo(toDo);
    }

    @Override
    public Completable deleteToDo(ToDo toDo) {
        return toDoHelper.deleteToDo(toDo);
    }

    @Override
    public Completable deleteAllToDo() {
        return toDoHelper.deleteAllToDo();
    }

    @Override
    public Observable<List<ToDo>> getSearchToDo(String query) {
        return toDoHelper.getSearchToDo(query);
    }

    @Override
    public Observable<List<ToDo>> getToDos() {
        return toDoHelper.getToDos();
    }


}
