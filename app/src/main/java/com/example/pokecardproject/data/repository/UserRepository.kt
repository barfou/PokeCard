package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.database.DatabaseManager
import com.example.pokecardproject.data.database.dao.UserDao
import com.example.pokecardproject.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository {

    override suspend fun insertUser(user: User): Boolean {

        return withContext(Dispatchers.IO) {
            try {
                dao.insert(user)
                return@withContext true
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext false
            }
        }
    }

    override suspend fun loginExist(login: String): Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.loginExist(login) > 0
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

    override suspend fun credentialsOk(login: String, password: String): Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.credentialsOk(login, password) > 0
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface UserRepository {

    suspend fun insertUser(user: User): Boolean

    suspend fun loginExist(login: String): Boolean?

    suspend fun credentialsOk(login: String, password: String): Boolean?

    companion object {

        val instance: UserRepository by lazy {
            UserRepositoryImpl(DatabaseManager.getInstance().database.userDao)
        }
    }
}