package com.example.uas_paw.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSensor")
data class SensorTanaman(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val locsensor : String,
    val statustanaman : String
)
