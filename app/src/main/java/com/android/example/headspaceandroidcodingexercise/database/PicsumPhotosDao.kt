package com.android.example.headspaceandroidcodingexercise.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotos
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PicsumPhotosDao {

    @Query("select * from picsumphotositem")
    fun getPicsumPhotosFromDb(): Flowable<PicsumPhotos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicsumPhotosToDb(picsumPhotos: PicsumPhotos): Completable
}
