package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemRawTodoSaerchBinding;
import com.example.myapplication.model.ToDo;
import com.example.myapplication.ui.fragment.searchToDo.SearchToDoViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterToDoSearch extends RecyclerView.Adapter<AdapterToDoSearch.ViewHolderToDo> {
    private List<ToDo> toDos = new ArrayList<>();
    private SearchToDoViewModel viewModel;

    public void clearDate() {
        toDos.clear();
        notifyDataSetChanged();
    }

    public void getSearchToDos(List<ToDo> toDos, SearchToDoViewModel viewModel) {
        this.toDos = toDos;
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderToDo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRawTodoSaerchBinding itemRawTodoSaerchBinding = ItemRawTodoSaerchBinding.inflate(layoutInflater, parent, false);
        return new ViewHolderToDo(itemRawTodoSaerchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderToDo holder, int position) {
        holder.bindToDo(toDos.get(position));
    }

    @Override
    public int getItemCount() {
        return toDos != null ? toDos.size() : 0;
    }

    public ToDo deleteToDo(int position) {
        return this.toDos.get(position);
    }

    public class ViewHolderToDo extends RecyclerView.ViewHolder {

        private ItemRawTodoSaerchBinding binding;

        public ViewHolderToDo(@NonNull ItemRawTodoSaerchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindToDo(final ToDo toDo) {
            binding.setTodo(toDo);
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}


