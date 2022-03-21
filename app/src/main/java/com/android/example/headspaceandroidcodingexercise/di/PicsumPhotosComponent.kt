package com.android.example.headspaceandroidcodingexercise.di

import com.android.example.headspaceandroidcodingexercise.MainActivity
import com.android.example.headspaceandroidcodingexercise.views.PicsumPhotosFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface PicsumPhotosComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(picsumPhotosFragment: PicsumPhotosFragment)
}