package com.example.roomdbproject.repository

import androidx.lifecycle.LiveData
import com.example.roomdbproject.data.UserDao
import com.example.roomdbproject.model.User

class UserRepository(private val userDao: UserDao)  {

  val readAllData: LiveData<List<User>> = userDao.readAllData()

  suspend fun addUser(user: User){
    userDao.addUser(user)
  }

  suspend fun updateUser(user: User){
    userDao.updateUser(user)
  }

  suspend fun deleteUser(user: User){
    userDao.deleteUser(user)
  }

  suspend fun deleteAllUsers(){
    userDao.deleteAllUsers()
  }


}
