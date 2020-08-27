package com.example.myapplication.ui.fragment.addNewTodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddTodoBinding;
import com.example.myapplication.repository.RepositoryMangerImpl;
import com.example.myapplication.repository.dataBase.ToDoHelperImpl;
import com.example.myapplication.ui.base.BaseFragment;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;


public class AddTodoFragment extends BaseFragment<FragmentAddTodoBinding, AddViewModel> {
    private static final String TAG = "AddTodoFragment";

    private AddViewModel mainViewModel;
    private DatePickerDialog datePickerDialog;
    private AddToDoViewModelProviderFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        factory = new AddToDoViewModelProviderFactory(new RepositoryMangerImpl(new ToDoHelperImpl(requireContext())));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNewTodo();
    }

    @Override
    public int getDataBindingVariable() {
        return BR.viewModel;
    }


    @Override
    public void setupViews() {
        getDataBinding().btnAddNewTodoBack.setOnClickListener(view ->
                Navigation.findNavController(rootView).navigate(R.id.action_addTodoFragment_to_mainToDosFragment));
        getDataBinding().npAddNewTodoPriority.setMinValue(0);
        getDataBinding().npAddNewTodoPriority.setMaxValue(10);
        setData();
    }

    private void addNewTodo() {
        mainViewModel.getTodo().observe(getViewLifecycleOwner(), toDo -> {
            Log.i(TAG, "addNewTodo: " + toDo.getTitle());
            Navigation.findNavController(rootView).navigate(R.id.action_addTodoFragment_to_mainToDosFragment);
        });

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_todo;
    }

    @Override
    public AddViewModel getViewModel() {
        mainViewModel = new ViewModelProvider(this, factory).get(AddViewModel.class);
        return mainViewModel;
    }

    private void setData() {
        getDataBinding().tvFragmentAddDataPicker.setOnClickListener((view) ->
        {
            datePickerDialog = new DatePickerDialog(getContext());
            datePickerDialog.setSelectionMode(DateRangeCalendarView.SelectionMode.Single);
            datePickerDialog.setTextSizeTitle(10.0f);
            datePickerDialog.setTextSizeWeek(12.0f);
            datePickerDialog.setTextSizeDate(14.0f);
            datePickerDialog.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary));
            datePickerDialog.setAcceptButtonColor(getResources().getColor(R.color.colorAccent));
            datePickerDialog.setCanceledOnTouchOutside(true);
            datePickerDialog.setOnSingleDateSelectedListener(date -> {
                getDataBinding().tvFragmentAddDataPicker.setText(date.getPersianLongDate());
            });
            datePickerDialog.showDialog();
        });
    }
}
