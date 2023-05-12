package com.example.androidmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmvvm.adapter.UserAdapter;
import com.example.androidmvvm.databinding.ActivityMainBinding;
import com.example.androidmvvm.model.User;
import com.example.androidmvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter = new UserAdapter(new ArrayList<>());
    ;
    private EditText nameEditText;
    private EditText emailEditText;
    private Button createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.setViewModel(userViewModel);
        binding.setLifecycleOwner(this);

        User userData = new User(null, null);
        userViewModel.setUserData(userData);

        recyclerView = binding.userRecyclerview;
       // recyclerView = findViewById(R.id.user_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(userAdapter);

        // Ajoute un Observer pour écouter les modifications de la liste des utilisateurs
        userViewModel.getUserList().observe(this, userList -> {
            if (userList != null) {
                userAdapter.userList = userList;
                userAdapter.notifyDataSetChanged();
            }
        });

    }
}