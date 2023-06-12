package com.techfortyone.roomlibraryv1.data.userrepository

import androidx.lifecycle.LiveData
import com.techfortyone.roomlibraryv1.data.model.User

interface UserRepository {

    fun readAllData(): LiveData<List<User>>

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

}