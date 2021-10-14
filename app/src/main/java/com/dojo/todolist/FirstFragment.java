package com.dojo.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.databinding.FragmentFirstBinding;
import com.dojo.todolist.ui.TodoAdapter;
import com.dojo.todolist.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private List<TodoEntity> todoData = new ArrayList<>();
    private TodoAdapter mAdapter;
    private MainViewModel mViewModel;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initViewModel();
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViewModel() {

        final Observer<List<TodoEntity>> notesObserver =
                new Observer<List<TodoEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<TodoEntity> noteEntities) {
                        todoData.clear();
                        todoData.addAll(noteEntities);

                        if (mAdapter == null || binding != null) {
                            mAdapter = new TodoAdapter(todoData,
                                    getActivity());
                            binding.recyclerView.setAdapter(mAdapter);
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                };

        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mViewModel.mTodos.observe(requireActivity(), notesObserver);
    }



    private void initRecyclerView() {

        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        DividerItemDecoration divider = new DividerItemDecoration(
                binding.recyclerView.getContext(), layoutManager.getOrientation());
        binding.recyclerView.addItemDecoration(divider);

    }


}