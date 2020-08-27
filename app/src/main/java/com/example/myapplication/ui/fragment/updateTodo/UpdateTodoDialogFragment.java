package com.example.myapplication.ui.fragment.updateTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTodoDialogUpdateBinding;
import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryMangerImpl;
import com.example.myapplication.repository.dataBase.ToDoHelperImpl;
import com.sardari.daterangepicker.customviews.DateRangeCalendarView;
import com.sardari.daterangepicker.dialog.DatePickerDialog;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpdateTodoDialogFragment extends DialogFragment {
    private EditText edtTitle;
    private EditText edtDescription;
    private NumberPicker npPriority;
    private Button btnUpdate;
    private ToDo toDo;
    private UpdateViewModel mainViewModel;
    private TextView tvDatePicker;
    private DatePickerDialog pickerDialog;
    private String title;
    private String description;
    private String datePicker;
    private int priority;
    private int id;
    private FragmentTodoDialogUpdateBinding fragmentTodoDialogUpdateBinding;
    private UpdateViewModelProviderFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        factory = new UpdateViewModelProviderFactory(new RepositoryMangerImpl(new ToDoHelperImpl(getContext())));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toDo = UpdateTodoDialogFragmentArgs.fromBundle(getArguments()).getUpdateToDo();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(requireActivity(), factory).get(UpdateViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        fragmentTodoDialogUpdateBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_todo_dialog_update, null, false);
        builder.setView(fragmentTodoDialogUpdateBinding.getRoot());
        fragmentTodoDialogUpdateBinding.setLifecycleOwner(this);
        fragmentTodoDialogUpdateBinding.setTodo(toDo);
        setupViews();
        setDatePicker();
        setOnClick();
        return builder.create();
    }

    private void setOnClick() {
        btnUpdate = fragmentTodoDialogUpdateBinding.btnFragmentDialogUpdateAdd;
        btnUpdate.setOnClickListener(view1 -> {
            setUpdateToDo();
            ToDo toDos = new ToDo(title, description, datePicker, priority);
            toDos.setId(id);
            if (toDo.isCompleted() == false) {
                toDos.setCompleted(false);
            } else {
                toDos.setCompleted(true);
            }
            mainViewModel.updateToDos(toDos)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


        });
    }


    private void setUpdateToDo() {
        title = edtTitle.getText().toString();
        description = edtDescription.getText().toString();
        priority = npPriority.getValue();
        datePicker = tvDatePicker.getText().toString();
        id = toDo.getId();
    }


    private void setupViews() {
        edtTitle = fragmentTodoDialogUpdateBinding.edtTaskDialogUpdateTitle;
        edtDescription = fragmentTodoDialogUpdateBinding.edtTaskDialogUpdateDescription;
        npPriority = fragmentTodoDialogUpdateBinding.npUpdateToDoPriority;
        btnUpdate = fragmentTodoDialogUpdateBinding.btnFragmentDialogUpdateAdd;
        tvDatePicker = fragmentTodoDialogUpdateBinding.tvFragmentDialogUpdateDataPicker;
        npPriority.setMinValue(0);
        npPriority.setMaxValue(10);
    }

    private void setDatePicker() {
        tvDatePicker.setOnClickListener((view1 -> {
            pickerDialog = new DatePickerDialog(getContext());
            pickerDialog.setSelectionMode(DateRangeCalendarView.SelectionMode.Single);
            pickerDialog.setTextSizeTitle(10.0f);
            pickerDialog.setTextSizeWeek(12.0f);
            pickerDialog.setTextSizeDate(14.0f);
            pickerDialog.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary));
            pickerDialog.setAcceptButtonColor(getResources().getColor(R.color.colorAccent));
            pickerDialog.setCanceledOnTouchOutside(true);
            pickerDialog.setOnSingleDateSelectedListener(date -> {
                tvDatePicker.setText(date.getPersianLongDate());
            });
            pickerDialog.showDialog();
        }));
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);

    }


}
