package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.database.DatabaseManager
import com.example.pokecardproject.data.database.dao.CompetenceDao
import com.example.pokecardproject.data.model.Competence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetenceRepositoryImpl(
    private val dao: CompetenceDao
) : CompetenceRepository {

    override suspend fun getAll(): List<Competence>? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getAll()
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

    override suspend fun insertCompetence(competence: Competence): Long {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.insert(competence)
            } catch (e: Exception) {
                return@withContext -1 as Long
            }
        }
    }

    override suspend fun initTable() {

        return withContext(Dispatchers.IO) {

            try {
                if (dao.getCount() < 1) {
                    val listAbilities = listOf(
                        "solar-power",
                        "blaze",
                        "sheer-force",
                        "rivalry",
                        "poison-point",
                        "damp",
                        "dry-skin",
                        "effect-spore",
                        "steadfast",
                        "no guard",
                        "guts",
                        "regenerator"
                    )
                    listAbilities.forEach {
                        dao.insert(Competence(0, it, false))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getCount(): Int {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getCount()
            } catch (e: Exception) {
                return@withContext -1
            }
        }
    }
}

interface CompetenceRepository {

    suspend fun getAll(): List<Competence>?

    suspend fun insertCompetence(competence: Competence): Long

    suspend fun initTable()

    suspend fun getCount(): Int

    companion object {

        val instance: CompetenceRepository by lazy {
            CompetenceRepositoryImpl(DatabaseManager.getInstance().database.competenceDao)
        }
    }
}