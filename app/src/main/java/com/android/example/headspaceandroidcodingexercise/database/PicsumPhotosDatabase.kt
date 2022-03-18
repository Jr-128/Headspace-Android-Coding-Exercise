package com.android.example.headspaceandroidcodingexercise.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem

@Database(entities = [PicsumPhotosItem::class], version = 1)
abstract class PicsumPhotosDatabase : RoomDatabase() {
    abstract fun getPicsumPhotosDao(): PicsumPhotosDao
}