package com.example.powerof3.repository

import com.example.powerof3.database.DAORecords
import com.example.powerof3.database.RecordEntity

class RecordsRepository(private val dao: DAORecords) {

    suspend fun saveGameResult(playerName: String, score: Int, fieldSize: Int) {
        val record = RecordEntity(
            id = System.currentTimeMillis().toInt(),
            name = playerName,
            score = score,
            fieldSize = fieldSize
        )
        dao.insertRecord(record)
    }

    suspend fun getAllRecords(): List<RecordEntity> {
        return dao.getAllRecords()
    }

    suspend fun deleteAllRecords() {
        dao.deleteAllRecords()
    }
}