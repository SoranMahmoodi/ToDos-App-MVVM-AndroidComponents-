package com.example.myapplication.repository.dataBase;

import com.example.myapplication.model.ToDo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ToDoHelper {

    Completable addToDo(ToDo toDo);

    Completable updateToDo(ToDo toDo);

    Completable deleteToDo(ToDo toDo);

    Completable deleteAllToDo();

    Observable<List<ToDo>> getSearchToDo(String query);

    Observable<List<ToDo>> getToDos();

}
