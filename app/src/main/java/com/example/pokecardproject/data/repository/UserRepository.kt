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
                val v = -1
                return@withContext v.toLong()
            }
        }
    }

    override suspend fun loginExist(login: String): Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.loginExist(login) > 0
            } catch (e: Exception) {
                e.printStackTrace()
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

    override suspend fun updateUser(login: String, mail: String, password: String, id: Long): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                dao.updateUser(login, mail, password, id)
                return@withContext true
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext false
            }
        }
    }
}

interface UserRepository {

    suspend fun getUserById(userId: Long): User?

    suspend fun insertUser(user: User): Long

    suspend fun loginExist(login: String): Boolean?

    suspend fun credentialsOk(login: String, password: String): User?

    suspend fun updateUser(login: String, mail: String, password: String, id: Long): Boolean

    companion object {

        val instance: UserRepository by lazy {
            UserRepositoryImpl(DatabaseManager.getInstance().database.userDao)
        }
    }
}