package com.android.example.headspaceandroidcodingexercise.rest

import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem
import io.reactivex.Flowable
import retrofit2.http.GET

interface PicsumPhotosApi {

    @GET(PHOTOS_LIST)
    fun getPhotos(): Flowable<List<PicsumPhotosItem>>

    companion object {
        const val BASE_URL = "https://picsum.photos/"
        const val PHOTOS_LIST = "v2/list"
    }
}