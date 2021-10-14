package com.dojo.todolist.ui;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dojo.todolist.FirstFragment;
import com.dojo.todolist.FirstFragmentDirections;
import com.dojo.todolist.R;
import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.databinding.TodoListItemBinding;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    private final List<TodoEntity> mTodos;
    private final Context mContext;
    private TodoListItemBinding binding;

    public TodoAdapter(List<TodoEntity> mTodos, Context mContext) {
        this.mTodos = mTodos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = TodoListItemBinding.inflate(inflater);
        return new TodoAdapter.ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        final TodoEntity todo = mTodos.get(position);
        holder.todoListItemBinding.noteText.setText(todo.getTitle());

        holder.todoListItemBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Called", Toast.LENGTH_SHORT).show();
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment();
                action.setId(todo.getId());
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TodoListItemBinding todoListItemBinding;

        public ViewHolder(@NonNull TodoListItemBinding todoListItemBinding) {
            super(todoListItemBinding.getRoot());
            this.todoListItemBinding = todoListItemBinding;

        }
    }
}
