package com.example.myapplication.ui.fragment.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;

import com.example.myapplication.repository.RepositoryManger;

public class MainViewModelProviderFactory extends AbstractSavedStateViewModelFactory {
    private RepositoryManger repositoryManger;

    /**
     * Constructs this factory.
     *
     * @param owner {@link SavedStateRegistryOwner} that will provide restored state for created
     *              {@link ViewModel ViewModels}
     */
    public MainViewModelProviderFactory(@NonNull SavedStateRegistryOwner owner, RepositoryManger repositoryManger) {
        super(owner, null);
        this.repositoryManger = repositoryManger;


    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        return (T) new MainViewModel(repositoryManger);
    }
}
