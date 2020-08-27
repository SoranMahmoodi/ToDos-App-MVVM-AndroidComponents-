package com.example.myapplication.ui.fragment.searchToDo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryManger;
import com.example.myapplication.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchToDoViewModel extends BaseViewModel {
    private static final String TAG = "MainViewModel";
    private MutableLiveData<List<ToDo>> toDoLiveData;
    private SearchToDoNavigator searchToDoNavigator;
    public MutableLiveData<String> getQuery = new MutableLiveData<>();

    public SearchToDoViewModel(RepositoryManger repositoryManger) {
        super(repositoryManger);
    }

    public void searchNavigator(SearchToDoNavigator searchToDoNavigator) {
        this.searchToDoNavigator = searchToDoNavigator;
    }

    public LiveData<List<ToDo>> getListToDo() {
        if (toDoLiveData == null) {
            toDoLiveData = new MutableLiveData<>();
        }
        return toDoLiveData;

    }


    public void getSearch(String query) {
        getRepositoryManger().getSearchToDo(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ToDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<ToDo> toDos) {
                        Log.i(TAG, "onNext: " + toDos);
                        toDoLiveData.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                })
        ;
    }

    public void onClickUpdate(ToDo todo) {
        searchToDoNavigator.onClickUpdate(todo);
    }

}
