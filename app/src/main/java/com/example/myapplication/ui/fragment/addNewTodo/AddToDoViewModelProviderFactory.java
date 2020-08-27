package com.example.myapplication.ui.fragment.addNewTodo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.repository.RepositoryManger;

public class AddToDoViewModelProviderFactory implements ViewModelProvider.Factory {

    private RepositoryManger repositoryManger;

    public AddToDoViewModelProviderFactory(RepositoryManger repositoryManger) {
        this.repositoryManger = repositoryManger;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddViewModel.class)) {
            return (T) new AddViewModel(repositoryManger);
        } else {
            throw new IllegalThreadStateException("error");
        }
    }
}
