package com.example.myapplication.ui.fragment.updateTodo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;

import com.example.myapplication.repository.RepositoryManger;

public class UpdateViewModelProviderFactory implements Factory {

    private RepositoryManger repositoryManger;

    public UpdateViewModelProviderFactory(RepositoryManger repositoryManger) {
        this.repositoryManger = repositoryManger;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UpdateViewModel.class)) {
            return (T) new UpdateViewModel(repositoryManger);
        } else {
            throw new IllegalThreadStateException("error");
        }
    }
}
