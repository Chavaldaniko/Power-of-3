package com.example.powerof3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAORecords {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordEntity)

    @Query("SELECT * FROM record")
    suspend fun getAllRecords(): List<RecordEntity>

}