package com.techfortyone.roomlibraryv1.data.local

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.utils.DB_NAME

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

//    companion object {
//        @Volatile
//        private var INSTANCE : UserDatabase? = null
//
//        fun getDatabase(context: Context) : UserDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance =  Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    DB_NAME
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}