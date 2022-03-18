package com.android.example.headspaceandroidcodingexercise.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PicsumPhotosDao {

    @Query("select * from picsumphotositem")
    fun getPicsumPhotosFromDb(): Flowable<List<PicsumPhotosItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicsumPhotosToDb(picsumPhotos: List<PicsumPhotosItem>): Completable
}
