package com.example.myapplication.ui.fragment.updateTodo;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryManger;
import com.example.myapplication.ui.base.BaseViewModel;

import io.reactivex.Completable;

public class UpdateViewModel extends BaseViewModel {

    public UpdateViewModel(RepositoryManger repositoryManger) {
        super(repositoryManger);
    }


    public Completable updateToDos(ToDo toDo) {
        return getRepositoryManger().updateToDo(toDo);
    }


}
