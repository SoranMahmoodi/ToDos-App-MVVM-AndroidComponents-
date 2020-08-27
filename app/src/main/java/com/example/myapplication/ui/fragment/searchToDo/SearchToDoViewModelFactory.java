package com.example.myapplication.ui.fragment.searchToDo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.repository.RepositoryManger;

public class SearchToDoViewModelFactory implements ViewModelProvider.Factory {
    private RepositoryManger repositoryManger;

    public SearchToDoViewModelFactory(RepositoryManger repositoryManger) {
        this.repositoryManger = repositoryManger;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchToDoViewModel.class)) {
            return (T) new SearchToDoViewModel(repositoryManger);
        } else {
            throw new IllegalThreadStateException("error");
        }
    }
}
