package com.example.myapplication.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface ToDoDao {

    @Insert
    Completable addToDo(ToDo toDo);

    @Delete
    Completable deleteTodo(ToDo toDo);

    @Update
    Completable updateToDo(ToDo toDo);

    @Query("DELETE FROM toDoTable")
    Completable deleteAllToDo();

    @Query("SELECT * FROM TODOTABLE WHERE title LIKE '%'|| :query ||'%'")
    Observable<List<ToDo>> getSearchToDo(String query);

    @Query("SELECT * FROM TODOTABLE ORDER BY priority DESC")
    Observable<List<ToDo>> getToDo();

}
