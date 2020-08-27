package com.example.myapplication.ui.fragment.main;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.adapter.AdapterToDo;
import com.example.myapplication.databinding.FragmentMainTodoBinding;
import com.example.myapplication.model.ToDo;
import com.example.myapplication.repository.RepositoryMangerImpl;
import com.example.myapplication.repository.dataBase.ToDoHelperImpl;
import com.example.myapplication.ui.base.BaseFragment;

public class MainToDosFragment extends BaseFragment<FragmentMainTodoBinding, MainViewModel> implements MainNavigator {
    private static final String TAG = "MainToDosFragment";
    private AdapterToDo adapterToDo;
    private MediaPlayer mpCompleted;
    private MainViewModelProviderFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        factory = new MainViewModelProviderFactory(requireActivity(), new
                RepositoryMangerImpl(new ToDoHelperImpl(getContext())));
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "onCreate:main ");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMViewModel().setCheckedCompleted(this);
        setListToDo();


    }

    @Override
    public int getDataBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void setupViews() {
        getDataBinding().rvMainToDo.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapterToDo = new AdapterToDo();
        getDataBinding().rvMainToDo.setAdapter(adapterToDo);
        getEdtSearch();
        swipeDeleteItem();
        setScrollFab();
        setFabMainAdd();
    }

    @Override
    public boolean setOnClickListenerItemToDo(View view, ToDo toDo) {
        Navigation.findNavController(rootView).navigate(MainToDosFragmentDirections
                .actionMainToDosFragmentToUpdateTodoDialogFragment().setUpdateToDo(toDo));
        return true;
    }

    @Override
    public void setOnCheckChangeCompleted(ToDo toDo) {
        getMViewModel().UpdateToDo(toDo);
        if (toDo.isCompleted()) {
            soundClickCompleted();
        }

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_todo;
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel = new ViewModelProvider(requireActivity(), factory).get(MainViewModel.class);

    }

    private void getEdtSearch() {
        getDataBinding().edtFragmentHomeSearch.setOnClickListener(view ->
                Navigation.findNavController(rootView).navigate(R.id.action_mainToDosFragment_to_searchToDoToDoFragment));
    }

    private void setFabMainAdd() {
        getDataBinding().fabMainAddToDo.setOnClickListener(view ->
                Navigation.findNavController(rootView).navigate(R.id.action_mainToDosFragment_to_addTodoFragment));
    }

    private void setListToDo() {
        getMViewModel().getListToDo().observe(getViewLifecycleOwner(), toDos -> {
            adapterToDo.getToDos(toDos, getViewModel());
            if (toDos.isEmpty() || adapterToDo == null) {
                getDataBinding().animFragmentTodoEmpty.setVisibility(View.VISIBLE);
            } else {
                getDataBinding().animFragmentTodoEmpty.setVisibility(View.GONE);

            }
        });
        getMViewModel().getToDosList();
    }

    private void swipeDeleteItem() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                getViewModel().deleteToDo(adapterToDo.deleteToDo(viewHolder.getAdapterPosition()));
                getDataBinding().fabMainAddToDo.show();
            }
        }).attachToRecyclerView(getDataBinding().rvMainToDo);
    }

    private void setScrollFab() {
        getDataBinding().rvMainToDo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && getDataBinding().fabMainAddToDo.isShown()) {
                    getDataBinding().fabMainAddToDo.hide();
                } else if (dy < 0 && !getDataBinding().fabMainAddToDo.isShown()) {
                    getDataBinding().fabMainAddToDo.show();
                }
            }
        });
    }

    private void soundClickCompleted() {
        mpCompleted = MediaPlayer.create(getContext(), R.raw.wl_completion_sound);
        try {
            if (mpCompleted.isPlaying()) {
                mpCompleted.stop();
                mpCompleted.release();
                mpCompleted = MediaPlayer.create(getContext(), R.raw.wl_completion_sound);
            } else {
                mpCompleted.start();
            }
        } catch (Exception e) {
            Log.e(TAG, "soundClickCompleted: " + e.toString());
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        hideKeyboard();
    }
}
