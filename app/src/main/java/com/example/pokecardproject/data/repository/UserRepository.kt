package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.database.DatabaseManager
import com.example.pokecardproject.data.database.dao.UserDao
import com.example.pokecardproject.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository {

    override suspend fun insertUser(user: User): Long {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.insert(user)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext -1 as Long
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

    // Returns object user if login and password match a user, else returns null
    override suspend fun credentialsOk(login: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            try {
                if(dao.credentialsOk(login, password) > 0) {
                    return@withContext dao.getUserWithCredentials(login, password)
                } else {
                    return@withContext null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    override suspend fun getUserById(userId: Long): User? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getUserById(userId)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface UserRepository {

    suspend fun getUserById(userId: Long): User?

    suspend fun insertUser(user: User): Long

    suspend fun loginExist(login: String): Boolean?

    suspend fun credentialsOk(login: String, password: String): User?

    companion object {

        val instance: UserRepository by lazy {
            UserRepositoryImpl(DatabaseManager.getInstance().database.userDao)
        }
    }
}