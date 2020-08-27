package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemRawTodoBinding;
import com.example.myapplication.model.ToDo;
import com.example.myapplication.ui.fragment.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.ToDoViewHolder> {

    private List<ToDo> toDos = new ArrayList<>();
    private List<ToDo> toDoList;
    private MainViewModel viewModel;

    public void getToDos(List<ToDo> toDos, MainViewModel viewModel) {
        this.toDos = toDos;
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }

    public void getToDosFiltering(List<ToDo> toDoList, MainViewModel viewModel) {
        this.toDoList = toDoList;
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        this.toDos.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRawTodoBinding itemRawTodoBinding = ItemRawTodoBinding.inflate(layoutInflater, parent, false);
        return new ToDoViewHolder(itemRawTodoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo toDo = toDos.get(position);
        holder.bindData(toDo);
    }

    @Override
    public int getItemCount() {
        return toDos != null ? toDos.size() : 0;
    }

    public ToDo deleteToDo(int position) {
        return this.toDos.get(position);
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        private ItemRawTodoBinding binding;

        public ToDoViewHolder(ItemRawTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindData(ToDo toDo) {
            binding.cbItemIsCompleted.setOnCheckedChangeListener(null);
            binding.setTodo(toDo);
            binding.executePendingBindings();
            binding.setViewModel(viewModel);
            binding.cbItemIsCompleted.setOnCheckedChangeListener((compoundButton, b) -> {
                toDo.setCompleted(b);
                viewModel.onCheckedUpdate(toDo);
            });

        }
    }
}



















































/*public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.ViewHolderToDo> {
    private List<ToDo> toDos = new ArrayList<>();
    private OnClickItemToDo onClickItemToDo;


    public void getToDos(List<ToDo> toDos) {
        this.toDos = toDos;
        notifyDataSetChanged();
    }

    public void getSearchToDos(List<ToDo> toDos) {
        this.toDos = toDos;
        notifyDataSetChanged();
    }

    public void onClickToDo(OnClickItemToDo onClickItemToDo) {
        this.onClickItemToDo = onClickItemToDo;
    }

    @NonNull
    @Override
    public ViewHolderToDo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderToDo(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_raw_todo, parent, false), onClickItemToDo);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderToDo holder, int position) {
        holder.bindToDo(toDos.get(position));
    }

    @Override
    public int getItemCount() {
        return toDos.size();
    }

    public ToDo deleteToDo(int position) {
        return this.toDos.get(position);
    }

    public class ViewHolderToDo extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;
        private CheckBox completed;
        private CardView cvRaw;
        public View vLineColor;

        private OnClickItemToDo onClickItemToDo;

        public ViewHolderToDo(@NonNull View itemView, OnClickItemToDo onClickItemToDo) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_itemTodo_title);
            tvDescription = itemView.findViewById(R.id.tv_itemTodo_Description);
            tvPriority = itemView.findViewById(R.id.tv_itemTodo_priority);
            completed = itemView.findViewById(R.id.cb_item_isCompleted);
            vLineColor = itemView.findViewById(R.id.view_itemRaw);
            cvRaw = itemView.findViewById(R.id.cv_itemRaw_todo);
            this.onClickItemToDo = onClickItemToDo;
        }

        private void bindToDo(final ToDo toDo) {
            completed.setOnCheckedChangeListener(null);
            tvTitle.setText(toDo.getTitle());
            tvDescription.setText(toDo.getDescription());
            tvPriority.setText(String.valueOf(toDo.getPriority()));
            completed.setChecked(toDo.isCompleted());
            itemView.setOnLongClickListener(view -> {
                onClickItemToDo.setOnClickListenerItemToDo(toDo);
                return false;
            });
            completed.setOnCheckedChangeListener((compoundButton, b) -> {
                toDo.setCompleted(b);
                onClickItemToDo.setOnCheckChangeCompleted(toDo);
            });

            if (toDo.isCompleted() == true) {
                setColorView(ContextCompat.getDrawable(itemView.getContext(), R.drawable.background_view_accent));
            } else {
                setColorView(ContextCompat.getDrawable(itemView.getContext(), R.drawable.background_view));
            }
        }

        private void setColorView(Drawable color) {
            vLineColor.setBackground(color);
        }
    }


}*/


