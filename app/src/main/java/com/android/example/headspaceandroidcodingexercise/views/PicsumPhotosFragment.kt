package com.android.example.headspaceandroidcodingexercise.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.headspaceandroidcodingexercise.PicsumPhotosApp
import com.android.example.headspaceandroidcodingexercise.adapters.IOpenImage
import com.android.example.headspaceandroidcodingexercise.adapters.PicsumPhotosAdapter
import com.android.example.headspaceandroidcodingexercise.databinding.FragmentPicsumPhotosBinding
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem
import com.android.example.headspaceandroidcodingexercise.presenters.IPicsumPhotosView
import com.android.example.headspaceandroidcodingexercise.presenters.PicsumPhotosPresenter

class PicsumPhotosFragment : Fragment(), IPicsumPhotosView, IOpenImage {

    lateinit var binding: FragmentPicsumPhotosBinding

    private lateinit var picsumPhotosAdapter: PicsumPhotosAdapter

    private lateinit var presenter: PicsumPhotosPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PicsumPhotosApp.picsumPhotosComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("tag", "PicsumPhotosFragment onCreate")
        super.onCreate(savedInstanceState)
        presenter = PicsumPhotosPresenter(requireActivity().application)
        picsumPhotosAdapter = PicsumPhotosAdapter(this)
        presenter.initPicsumPhotosPresenter(this)
        presenter.checkNetworkState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPicsumPhotosBinding.inflate(inflater, container, false)

        binding.picsumPhotosRecyclerViewId.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = picsumPhotosAdapter
        }

        presenter.getPicsumPhotosFromServer()

        return binding.root
    }

    //Method updates the classics into the recycler view
    //and saves the classics into the db
    override fun onSuccessData(picsumPhotos: List<PicsumPhotosItem>) {
        // Here you add the logic to update the recycler view
        if (picsumPhotos.isNotEmpty()) {

            picsumPhotosAdapter.updatePicsumPhotosList(picsumPhotos)
            presenter.savePhotosToDb(picsumPhotos)
            Log.d("success", "photos not null saving data")
        }
    }

    //Here you add logic for showing the error to the user
    override fun onErrorData(error: Throwable) {
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("error", "onErrorData error getting photos, msg: " + error.stackTraceToString())
        presenter.getPhotosFromDb()

    }

    override fun onErrorNetwork() {
        Toast.makeText(requireContext(), "Please check your network connection", Toast.LENGTH_LONG)
            .show()
        Log.e("onErrorNetwork", "No network available")
        presenter.getPhotosFromDb()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //This method will clear the disposable from the presenter
        presenter.destroyPresenter()
    }

    override fun openImage(imageUrl: String) {
        Log.e("tag", "$imageUrl")
        val openImageIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            setDataAndType(Uri.parse(imageUrl), "image/*")
        }
        try {
            startActivity(openImageIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Couldn't find an app to open the image", Toast.LENGTH_LONG)
                .show()
            Log.e(
                "fragment",
                "Error in PicsumPhotosFragment: Couldn't find app to open image, msg: $e"
            )
        }
    }

    companion object {
        fun newInstance() = PicsumPhotosFragment()
    }
}