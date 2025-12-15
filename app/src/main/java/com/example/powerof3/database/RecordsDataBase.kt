package com.example.powerof3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecordEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun recordsDAO(): DAORecords

}