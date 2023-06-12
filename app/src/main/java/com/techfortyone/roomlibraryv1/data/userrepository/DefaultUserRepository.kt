package com.techfortyone.roomlibraryv1.data.userrepository

import androidx.lifecycle.LiveData
import com.techfortyone.roomlibraryv1.data.local.UserDao
import com.techfortyone.roomlibraryv1.data.model.User
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val userDao: UserDao) : UserRepository {

    override fun readAllData(): LiveData<List<User>> {
        return userDao.readAllData()
    }

    override suspend fun insertUser(user: User) {
        return userDao.addUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}