package com.app.bloconotas.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "text_table")
data class Notass(
     var texto: String? = null,
     var title: String? = null,
     var data: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @Ignore
    var isChecked = false
}
