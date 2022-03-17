package com.android.example.headspaceandroidcodingexercise.presenters

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.android.example.headspaceandroidcodingexercise.database.PicsumPhotosDatabase
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotos
import com.android.example.headspaceandroidcodingexercise.rest.PicsumPhotosApi
import com.android.example.headspaceandroidcodingexercise.rest.PicsumPhotosRetrofit
import io.reactivex.disposables.CompositeDisposable

/**
 * Class will be the presenter for the Picsum photos and will handle the
 * network call data retrieval and updating the view portion
 */
class PicsumPhotosPresenter : IPicsumPhotosPresenter {

    var picsumPhotosApi: PicsumPhotosApi = PicsumPhotosRetrofit.picsumPhotosApi

    var connectivityManager: ConnectivityManager = Application()
        .applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //todo
    var picsumPhotosDatabase = PicsumPhotosDatabase

    //Nullable variable to hold the view contract
    private var iPicsumPhotosViewContract: IPicsumPhotosView? = null

    private var isNetworkAvailable = false

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun initPicsumPhotosPresenter(viewContract: IPicsumPhotosView) {
        iPicsumPhotosViewContract = viewContract
    }

    override fun getPicsumPhotosFromServer() {
        TODO("Not yet implemented")
    }

    override fun checkNetworkState() {
        TODO("Not yet implemented")
    }

    override fun destroyPresenter() {
        TODO("Not yet implemented")
    }

    override fun savePhotosToDb(picsumPhotos: PicsumPhotos) {
        TODO("Not yet implemented")
    }

    override fun getPhotosFromDb() {
        TODO("Not yet implemented")
    }

}

interface IPicsumPhotosPresenter {
    //This method will initialize the presenter
    fun initPicsumPhotosPresenter(viewContract: IPicsumPhotosView)

    //This method gets the data from the server
    fun getPicsumPhotosFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()

    //This method saves the data to the database
    fun savePhotosToDb(picsumPhotos: PicsumPhotos)

    //This method get the data from the database
    fun getPhotosFromDb()
}

interface IPicsumPhotosView {
    //Method returns the success response to the view
    fun onSuccessData(picsumPhotos: PicsumPhotos)

    //Method returns the error response to the view
    fun onErrorData(error: Throwable)

    //Method returns the network error response to the view
    fun onErrorNetwork()
}