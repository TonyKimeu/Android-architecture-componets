package com.dojo.todolist;

import android.os.Bundle;

import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.ui.TodoAdapter;
import com.dojo.todolist.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dojo.todolist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MainViewModel mViewModel;
    private List<TodoEntity> todoData = new ArrayList<>();
    private TodoAdapter mAdapter;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        initViewModel();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void initViewModel() {

        final Observer<List<TodoEntity>> notesObserver =
                new Observer<List<TodoEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<TodoEntity> noteEntities) {
                        todoData.clear();
                        todoData.addAll(noteEntities);

                        if (mAdapter == null) {
                            Toast.makeText(MainActivity.this, "initViewModel MainActivity", Toast.LENGTH_SHORT).show();
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                };

        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mViewModel.mTodos.observe(this, notesObserver);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if (id == R.id.action_delete_all){
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addSampleData() {
        mViewModel.addSampleData();
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(MainActivity.this, "onSupportNavigateUp MainActivity", Toast.LENGTH_SHORT).show();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText(MainActivity.this, "OnBackPressed MainActivity", Toast.LENGTH_SHORT).show();

    }
}