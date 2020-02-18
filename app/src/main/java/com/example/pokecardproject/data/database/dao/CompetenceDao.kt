package com.example.pokecardproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.User

@Dao
interface CompetenceDao {

    @Query("SELECT * FROM competence")
    fun getAll(): List<Competence>

    @Insert
    fun insert(competence: Competence): Long

    @Query("SELECT COUNT(*) from competence")
    fun getCount(): Int
}