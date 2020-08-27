package com.example.myapplication.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    public View rootView;
    public T mDataBinding;
    public V mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        rootView = mDataBinding.getRoot();
        setupViews();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataBinding.setVariable(getDataBindingVariable(), mViewModel);
        mDataBinding.setLifecycleOwner(this);
        mDataBinding.executePendingBindings();
    }

    public abstract int getDataBindingVariable();

    public abstract void setupViews();

    public abstract int getLayout();

    public abstract V getViewModel();

    public V getMViewModel() {
        return mViewModel;
    }

    public T getDataBinding() {
        return mDataBinding;
    }

    public View getRootView() {
        return rootView;
    }


}
