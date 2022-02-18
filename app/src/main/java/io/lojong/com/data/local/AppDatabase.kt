package io.lojong.com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.lojong.com.model.Fact

@Database(entities = [Fact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): FactDao
}