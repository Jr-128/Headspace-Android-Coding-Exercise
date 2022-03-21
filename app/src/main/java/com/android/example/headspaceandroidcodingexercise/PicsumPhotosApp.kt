package com.android.example.headspaceandroidcodingexercise

import android.app.Application
import com.android.example.headspaceandroidcodingexercise.di.AppModule
import com.android.example.headspaceandroidcodingexercise.di.DaggerPicsumPhotosComponent
import com.android.example.headspaceandroidcodingexercise.di.PicsumPhotosComponent

class PicsumPhotosApp : Application() {

    override fun onCreate() {
        super.onCreate()

        picsumPhotosComponent = DaggerPicsumPhotosComponent
            .builder()
            // I create the app module to be used
            .appModule(AppModule(this))
            // i build the dagger component
            .build()
    }

    companion object {
        //This property will be able to be accessed anywhere
        //in the app
        lateinit var picsumPhotosComponent: PicsumPhotosComponent
    }
}

