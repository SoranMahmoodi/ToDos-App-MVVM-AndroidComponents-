package com.example.myapplication.utiliti;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.BindingAdapter;

import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.ui.fragment.searchToDo.SearchToDoViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class DataBindingUtil {

    @BindingAdapter("changedListener")
    public static <V extends BaseViewModel> void editTextChanged(TextInputEditText editText, V viewModel) {
        if (viewModel instanceof SearchToDoViewModel) {
            SearchToDoViewModel searchToDoViewModel = (SearchToDoViewModel) viewModel;
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    searchToDoViewModel.getQuery.postValue(editable.toString());

                }
            });
        }

    }

}
