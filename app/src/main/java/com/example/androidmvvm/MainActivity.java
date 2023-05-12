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
import com.example.androidmvvm.model.User;
import com.example.androidmvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.UserClickListener {

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
        // Charge le layout activity_main.xml
        setContentView(R.layout.activity_main);
        // Récupère une instance de UserViewModel à partir de ViewModelProvider
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        User userData = new User(null,null);
        userViewModel.setUserData(userData);
        recyclerView = findViewById(R.id.user_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //    Initialise les champs EditTexts et le bouton
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        createButton = findViewById(R.id.create_button);

        recyclerView.setAdapter(userAdapter);

        // Ajoute un Observer pour écouter les modifications de la liste des utilisateurs
        userViewModel.getUserList().observe(this, userList -> {
            if (userList != null) {
                userAdapter.userList = userList;
                userAdapter.notifyDataSetChanged();
            }
        });

    }


    // Implémentation de l'interface UserAdapter.UserClickListener pour gérer les clics sur un utilisateur
    @Override
    public void onUserClicked(User user) {
        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
    }
}