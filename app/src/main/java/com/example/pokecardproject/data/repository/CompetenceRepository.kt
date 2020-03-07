package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.database.DatabaseManager
import com.example.pokecardproject.data.database.dao.CompetenceDao
import com.example.pokecardproject.data.database.dao.PokemonCompetenceJoinDao
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonCompetenceJoin
import com.example.pokecardproject.data.model.PokemonDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetenceRepositoryImpl(
    private val competenceDao: CompetenceDao,
    private val pokemonCompetenceJoinDao: PokemonCompetenceJoinDao
) : CompetenceRepository {

    override suspend fun getAll(): List<Competence>? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext competenceDao.getAll()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    override suspend fun insertCompetence(competence: Competence): Long {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext competenceDao.insert(competence)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext -1 as Long
            }
        }
    }

    override suspend fun initTable() {

        return withContext(Dispatchers.IO) {

            try {
                if (competenceDao.getCount() < 1) {
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
                        competenceDao.insert(Competence(0, it, false))
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
                return@withContext competenceDao.getCount()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext -1
            }
        }
    }

    override suspend fun insertPokemonCompetenceJoin(pokemonCompetenceJoin: PokemonCompetenceJoin): Long {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext pokemonCompetenceJoinDao.insert(pokemonCompetenceJoin)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext -1 as Long
            }
        }
    }

    override suspend fun getCompetencesWithPokemonDbId(id: Long): List<Competence>? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext pokemonCompetenceJoinDao.getCompetencesWithPokemonDbId(id)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface CompetenceRepository {

    suspend fun getAll(): List<Competence>?

    suspend fun insertCompetence(competence: Competence): Long

    suspend fun initTable()

    suspend fun getCount(): Int

    suspend fun insertPokemonCompetenceJoin(pokemonCompetenceJoin: PokemonCompetenceJoin): Long

    suspend fun getCompetencesWithPokemonDbId(id: Long): List<Competence>?

    companion object {

        val instance: CompetenceRepository by lazy {
            CompetenceRepositoryImpl(
                DatabaseManager.getInstance().database.competenceDao,
                DatabaseManager.getInstance().database.pokemonCompetenceJoinDBDao
                )
        }
    }
}