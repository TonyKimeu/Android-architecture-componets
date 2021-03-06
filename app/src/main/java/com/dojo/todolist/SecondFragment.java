package com.dojo.todolist;

import static com.dojo.todolist.Utils.Constants.EDITING_KEY;
import static com.dojo.todolist.Utils.Constants.NOTE_ID_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.databinding.FragmentSecondBinding;
import com.dojo.todolist.viewmodel.EditorViewModel;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private boolean mNewNote, mEditing;
    private EditorViewModel mViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }
        initViewModel(SecondFragmentArgs.fromBundle(getArguments()).getId());

        binding.buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeAndReturn();
            }
        });
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndReturn();
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteNote();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void initViewModel(int todoId) {
        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveTodo.observe(getActivity(), new Observer<TodoEntity>() {
            @Override
            public void onChanged(@Nullable TodoEntity noteEntity) {
                if (noteEntity != null && !mEditing) {
                    binding.noteText.setText(noteEntity.getTitle());
                    if(noteEntity.getCompleted()){
                        binding.buttonComplete.setImageResource(R.drawable.ic_check_box_empty);
                    } else {
                        binding.buttonComplete.setImageResource(R.drawable.ic_check_box);
                    }
                }
            }
        });

        if (todoId == 0) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_note);
            mNewNote = true;
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.edit_note);
            int noteId = todoId;
            mViewModel.loadData(noteId);
        }
    }
    private void completeAndReturn() {
        mViewModel.completeTodo();
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }

    private void saveAndReturn() {
        mViewModel.saveNote(binding.noteText.getText().toString());
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }



}