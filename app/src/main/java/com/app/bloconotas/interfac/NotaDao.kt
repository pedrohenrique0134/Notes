package com.app.bloconotas.interfac

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.bloconotas.models.Notass

@Dao
interface NotaDao {
    @Query("SELECT * FROM text_table")
    fun getAll(): List<Notass>

    @Query("SELECT * FROM text_table WHERE texto LIKE :query")
    fun searchDataBase(query: String):List<Notass>

    @Insert
    suspend fun insert(vararg nota: Notass)

    @Update
    suspend fun update(nota: Notass)

    @Delete
    suspend fun delete(nota: Notass)

}