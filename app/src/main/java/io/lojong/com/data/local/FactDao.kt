package io.lojong.com.data.local

import androidx.room.*
import io.lojong.com.model.Fact

@Dao
interface FactDao {

    @Query("SELECT * FROM fact")
    fun getAll(): List<Fact>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(facts: List<Fact>)

    @Delete
    fun delete(fact: Fact)

    @Delete
    fun deleteAll(fact: List<Fact>)
}