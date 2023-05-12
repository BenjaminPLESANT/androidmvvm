package com.example.androidmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmvvm.model.User;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.MutablePropertyReference0Impl;

public class UserViewModel extends AndroidViewModel {

    // MutableLiveData permet de mettre à jour les données encapsulées dans le ViewModel
    private MutableLiveData<List<User>> userListLiveData;
    private MutableLiveData<User> userData = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<User>> getUserList() {
        return userListLiveData;
    }

    public MutableLiveData<User> getUserData() {
        return userData;
    }

    public void setUserData(User user) {
        userData.setValue(user);
    }

    public void create() {
        User newUser = new User(getUserData().getValue().getName(), getUserData().getValue().getEmail());

        List<User> userList = userListLiveData.getValue();
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(newUser);
        userListLiveData.setValue(userList);
    }
}