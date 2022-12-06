package com.example.scalioapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.scalioapp.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM RemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM RemoteKeys")
    suspend fun deleteAllRemoteKeys()

}