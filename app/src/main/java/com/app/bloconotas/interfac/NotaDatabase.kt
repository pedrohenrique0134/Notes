package com.app.bloconotas.interfac
import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.bloconotas.models.Notass


@Database(entities = [Notass::class], version = 1)

abstract class NotaDatabase: RoomDatabase() {
    abstract fun notaDao(): NotaDao
}
