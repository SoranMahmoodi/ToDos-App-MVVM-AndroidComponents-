package com.example.myapplication.ui.fragment.searchToDo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AdapterToDoSearch;
import com.example.myapplication.databinding.FragmentSearchTodoBinding;
import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryMangerImpl;
import com.example.myapplication.repository.dataBase.ToDoHelperImpl;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.updateTodo.UpdateTodoDialogFragment;

public class SearchToDoToDoFragment extends BaseFragment<FragmentSearchTodoBinding, SearchToDoViewModel> implements SearchToDoNavigator {
    private static final String TAG = "SearchToDoToDoFragment";
    private SearchToDoViewModel searchToDoViewModel;
    private AdapterToDoSearch adapterToDoSearch;
    private SearchToDoViewModelFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        factory = new SearchToDoViewModelFactory(new RepositoryMangerImpl(new ToDoHelperImpl(getContext())));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchToDoViewModel.searchNavigator(this);
        getSearchTodo();

    }

    @Override
    public int getDataBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void setupViews() {
        getDataBinding().rvFragmentSearch.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        adapterToDoSearch = new AdapterToDoSearch();
        getDataBinding().rvFragmentSearch.setAdapter(adapterToDoSearch);
        getDataBinding().btnFragmentSearchBack.setOnClickListener(view ->
                Navigation.findNavController(rootView).navigate(R.id.action_searchToDoToDoFragment_to_mainToDosFragment));
        getSearchToDo();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search_todo;
    }

    @Override
    public SearchToDoViewModel getViewModel() {
        searchToDoViewModel = new ViewModelProvider(requireActivity(), factory).get(SearchToDoViewModel.class);
        return searchToDoViewModel;
    }

    @Override
    public void onClickUpdate(ToDo toDo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("update", toDo);
        UpdateTodoDialogFragment dialogFragment = new UpdateTodoDialogFragment();
        dialogFragment.show(getFragmentManager(), null);
        dialogFragment.setArguments(bundle);
    }

    private void getSearchToDo() {
        getDataBinding().edtFragmentSearchSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    getMViewModel().getSearch(charSequence.toString());
                } else {
                    adapterToDoSearch.clearDate();
                    getDataBinding().animFragmentTodoEmptySearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (getDataBinding().edtFragmentSearchSearch.getText().toString().isEmpty()) {
                    getDataBinding().animFragmentTodoEmptySearch.setVisibility(View.VISIBLE);
                } else {
                    getDataBinding().animFragmentTodoEmptySearch.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getSearchTodo() {
        getMViewModel().getListToDo()
                .observe(getViewLifecycleOwner(), toDos -> {
                    adapterToDoSearch.getSearchToDos(toDos, getMViewModel());
                    if (!toDos.isEmpty() && toDos != null) {
                        getDataBinding().animFragmentTodoEmptySearch.setVisibility(View.GONE);
                    } else {

                        getDataBinding().animFragmentTodoEmptySearch.setVisibility(View.VISIBLE);

                    }
                });
        getMViewModel().getQuery.getValue();
    }

    private void requestFocus() {
        getDataBinding().edtFragmentSearchSearch.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: search");
        requestFocus();
    }

    @Override
    public void onStop() {
        hideKeyboard();
        super.onStop();
        Log.i(TAG, "onStop: search");
    }


}
