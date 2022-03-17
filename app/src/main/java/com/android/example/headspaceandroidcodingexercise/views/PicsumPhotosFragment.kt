package com.android.example.headspaceandroidcodingexercise.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.headspaceandroidcodingexercise.R
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotos
import com.android.example.headspaceandroidcodingexercise.presenters.IPicsumPhotosView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PicsumPhotosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PicsumPhotosFragment : Fragment(), IPicsumPhotosView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picsum_photos, container, false)
    }


    override fun onSuccessData(picsumPhotos: PicsumPhotos) {
        TODO("Not yet implemented")
    }

    override fun onErrorData(error: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onErrorNetwork() {
        TODO("Not yet implemented")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PicsumPhotosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PicsumPhotosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}