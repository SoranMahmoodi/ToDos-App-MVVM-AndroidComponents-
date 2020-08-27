package com.example.myapplication.ui.fragment.main;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryManger;
import com.example.myapplication.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {
    private static final String TAG = "MainViewModel";
    private MutableLiveData<List<ToDo>> toDoLiveData;
    private MainNavigator mainNavigator;

    public MainViewModel(RepositoryManger repositoryManger) {
        super(repositoryManger);
    }

    public LiveData<List<ToDo>> getListToDo() {
        if (toDoLiveData == null)
            return toDoLiveData = new MutableLiveData<>();
        else
            return toDoLiveData;
    }

    public void getToDosList() {
        if (toDoLiveData.getValue() == null) {
            getRepositoryManger().getToDos()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<ToDo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(List<ToDo> toDos) {
                            toDoLiveData.setValue(toDos);
                            Log.i(TAG, "onNext: " + toDos.size());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.toString());
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onComplete: ");
                        }
                    });
        } else {
            toDoLiveData.setValue(toDoLiveData.getValue());
        }

    }

    public void setCheckedCompleted(MainNavigator mainNavigator) {
        this.mainNavigator = mainNavigator;
    }

    public void deleteToDo(ToDo toDo) {
        getRepositoryManger().deleteToDo(toDo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                    }
                });
    }

    public void UpdateToDo(ToDo toDo) {
        getRepositoryManger().updateToDo(toDo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                    }
                });
    }

    public void onClickDeleteAllItemTodo() {
        getRepositoryManger().deleteAllToDo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void onCheckedUpdate(ToDo toDo) {
        mainNavigator.setOnCheckChangeCompleted(toDo);
    }

    public boolean onClickItemToDo(View view, ToDo toDo) {
        return mainNavigator.setOnClickListenerItemToDo(view, toDo);
    }


}
