package com.example.myapplication.ui.fragment.addNewTodo;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryManger;
import com.example.myapplication.ui.base.BaseViewModel;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddViewModel extends BaseViewModel {
    public static final String TAG = "MainViewModel";
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<String> datePicker = new MutableLiveData<>();
    public MutableLiveData<Integer> priority = new MutableLiveData<>();
    public ObservableField<String> errorTitle = new ObservableField<>();
    public ObservableField<String> errorDescription = new ObservableField<>();
    private MutableLiveData<ToDo> toDoMutableLiveData;

    public AddViewModel(RepositoryManger repositoryManger) {
        super(repositoryManger);
    }

    public LiveData<ToDo> getTodo() {
        if (toDoMutableLiveData == null) {
            toDoMutableLiveData = new MutableLiveData<>();
        }
        return toDoMutableLiveData;
    }


    private boolean isEmptyTitle() {
        return !TextUtils.isEmpty(title.getValue());
    }

    private boolean isEmptyDescription() {
        return !TextUtils.isEmpty(title.getValue());
    }

    private boolean isEqualsDatePicker() {
        return !datePicker.equals("لظفا یک تاریخ را انتخاب کنید");
    }

    public void onClickSave() {
        ToDo toDo = new ToDo(title.getValue(), description.getValue(), datePicker.getValue(), priority.getValue());
        if (isEmptyTitle()) {
            if (isEmptyDescription()) {
                if (isEqualsDatePicker()) {
                    addToDo(toDo);
                } else {
                    Log.e(TAG, "onClickSave: ");
                }
            } else {
                errorDescription.set("یادداشت خالی است");
            }
        } else {
            errorTitle.set("عنوان خالی است");
        }
    }

    public void addToDo(ToDo toDo) {
        getRepositoryManger().addToDo(toDo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                        toDoMutableLiveData.setValue(toDo);
                        Log.i(TAG, "onComplete: " + "success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                    }
                });
    }

}
