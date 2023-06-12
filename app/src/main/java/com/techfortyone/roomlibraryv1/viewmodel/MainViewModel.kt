package com.techfortyone.roomlibraryv1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.data.userrepository.DefaultUserRepository
import com.techfortyone.roomlibraryv1.data.userrepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: DefaultUserRepository) :
    ViewModel() {

    private var _userData = MutableLiveData<List<User>>()

    val userData: LiveData<List<User>> = _userData

    init {
        _userData.postValue(userRepository.readAllData().value)
    }

    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUser(user)
    }

    fun readData() = userRepository.readAllData()

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUser(user)
    }
}