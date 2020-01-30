package com.example.pokecardproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokecardproject.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun selectAll(): List<User>

    @Query("SELECT COUNT(*) from user")
    fun getCount(): Int

    @Insert
    fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: Long): User

    @Query("SELECT COUNT(*) FROM user WHERE login=:login")
    fun loginExist(login: String): Int

    @Query("SELECT COUNT(*) FROM user WHERE login=:login AND password=:password")
    fun credentialsOk(login: String, password: String): Int

    @Query("SELECT * FROM user WHERE login=:login AND password=:password")
    fun getUserWithCredentials(login: String, password: String): User
}