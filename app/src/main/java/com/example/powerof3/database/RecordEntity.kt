package com.example.powerof3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class RecordEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "fieldSize") val fieldSize: Int
)