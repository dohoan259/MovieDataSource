package com.example.moviedatasource.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedatasource.data.local.entity.Actor
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActor(actor: Actor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActors(vararg actor: Actor)

    @Query("SELECT * FROM actors WHERE actor_id = :actorId")
    fun getActorById(actorId: Int): Flow<Actor>
}