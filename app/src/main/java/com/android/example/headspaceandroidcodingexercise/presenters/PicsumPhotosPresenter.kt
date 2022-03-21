package com.android.example.headspaceandroidcodingexercise.presenters

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.room.Room
import com.android.example.headspaceandroidcodingexercise.database.PicsumPhotosDatabase
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem
import com.android.example.headspaceandroidcodingexercise.rest.PicsumPhotosApi
import com.android.example.headspaceandroidcodingexercise.rest.PicsumPhotosRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Class will be the presenter for the Picsum photos and will handle the
 * network call data retrieval and updating the view portion
 */
class PicsumPhotosPresenter(private val application: Application) : IPicsumPhotosPresenter {

    var picsumPhotosApi: PicsumPhotosApi = PicsumPhotosRetrofit.picsumPhotosApi

    var connectivityManager: ConnectivityManager = application
        .applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    var picsumPhotosDatabase: PicsumPhotosDatabase = Room
        .databaseBuilder(
            application.applicationContext,
            PicsumPhotosDatabase::class.java,
            "PicsumPhotos-DB"
        )
        .build()

    //Nullable variable to hold the view contract
    private var iPicsumPhotosViewContract: IPicsumPhotosView? = null

    private var isNetworkAvailable = false

    //Creating the disposable to dispose of the observers
    private val disposable by lazy {
        CompositeDisposable()
    }

    //This method helps us initializing the view contract
    override fun initPicsumPhotosPresenter(viewContract: IPicsumPhotosView) {
        //Assigning the view contract to the local variable
        iPicsumPhotosViewContract = viewContract
        Log.e("pics","getPicsumPhotosFromServer initPicsumPhotosPresenter")
    }

    //This method get the data from the server overriding the view contract method
    override fun getPicsumPhotosFromServer() {
        Log.e("pics","PicsumPhotosPresenter getPicsumPhotosFromServer invoked")
        /**
         * We retrieve the photos from the server, then switch to a worker thread
         * aside from the main thread, observing the response on the main thread,
         * finally subscribing to retrieve the response and get the data.
         */
        if (isNetworkAvailable) {
            Log.e("pics","getPicsumPhotosFromServer in isNetworkAvailable")
            val picsumPhotosDisposable = picsumPhotosApi

                .getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    //Updated the view when the success occurs
                    {
                        Log.e("pics","getPicsumPhotosFromServer success $it")
                        iPicsumPhotosViewContract?.onSuccessData(it)
                    },
                    //Updated the view when the error occurs
                    {
                        Log.e("pics","getPicsumPhotosFromServer error $it")
                        iPicsumPhotosViewContract?.onErrorData(it)
                    }
                )
            disposable.add(picsumPhotosDisposable)
        } else {
            Log.e("pics","getPicsumPhotosFromServer error network")
            iPicsumPhotosViewContract?.onErrorNetwork()
        }
    }

    //Checks for the network capabilities and determine if the network is available
    override fun checkNetworkState() {
        isNetworkAvailable = getActiveNetworkCapabbilities()?.let {
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
    }

    //This method will destroy the presenter, resetting the view contract and clear the disposables
    override fun destroyPresenter() {
        disposable.clear()
        iPicsumPhotosViewContract = null
    }

    override fun savePhotosToDb(picsumPhotos: List<PicsumPhotosItem>) {
        val picsumPhotosDatabaseDisposable = picsumPhotosDatabase
            .getPicsumPhotosDao()
            .insertPicsumPhotosToDb(picsumPhotos)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("success", "savePhotosToDb() successful!") },
                { Log.d("error", "savePhotosToDb() error: ${it.localizedMessage}") }
            )
        disposable.add(picsumPhotosDatabaseDisposable)
    }

    override fun getPhotosFromDb() {
        val picsumPhotosDatabaseDisposable = picsumPhotosDatabase
            .getPicsumPhotosDao()
            .getPicsumPhotosFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    iPicsumPhotosViewContract?.onSuccessData(it)
                    Log.d("success", "getPhotosFromDb() successful! updating view") },
                {
                    Log.d("error", "getPhotosFromDb() error: ${it.localizedMessage}") }
            )
        disposable.add(picsumPhotosDatabaseDisposable)
    }

    //Get the active network capabilities from the connectivity manager
    private fun getActiveNetworkCapabbilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
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
    fun savePhotosToDb(picsumPhotos: List<PicsumPhotosItem>)

    //This method get the data from the database
    fun getPhotosFromDb()
}

interface IPicsumPhotosView {
    //Method returns the success response to the view
    fun onSuccessData(picsumPhotos: List<PicsumPhotosItem>)

    //Method returns the error response to the view
    fun onErrorData(error: Throwable)

    //Method returns the network error response to the view
    fun onErrorNetwork()
}