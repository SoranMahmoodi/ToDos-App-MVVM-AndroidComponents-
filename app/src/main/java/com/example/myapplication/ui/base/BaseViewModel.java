package com.example.myapplication.ui.base;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.repository.RepositoryManger;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {
    private RepositoryManger repositoryManger;
    private CompositeDisposable compositeDisposable;

    public BaseViewModel(RepositoryManger repositoryManger) {
        this.repositoryManger = repositoryManger;
        compositeDisposable = new CompositeDisposable();
    }

    public RepositoryManger getRepositoryManger() {
        return repositoryManger;
    }

    @Override
    public void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
