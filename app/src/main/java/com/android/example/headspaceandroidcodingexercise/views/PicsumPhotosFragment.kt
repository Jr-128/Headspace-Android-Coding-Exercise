package com.android.example.headspaceandroidcodingexercise.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.example.headspaceandroidcodingexercise.adapters.PicsumPhotosAdapter
import com.android.example.headspaceandroidcodingexercise.databinding.FragmentPicsumPhotosBinding
import com.android.example.headspaceandroidcodingexercise.presenters.IPicsumPhotosView
import com.android.example.headspaceandroidcodingexercise.utils.UIState

class PicsumPhotosFragment : Fragment(), IPicsumPhotosView {

    lateinit var binding : FragmentPicsumPhotosBinding

    lateinit var picsumPhotosAdapter : PicsumPhotosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPicsumPhotosBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onSuccessData(uiState: UIState) {
        TODO("Not yet implemented")
    }

    override fun onErrorData(uiState: UIState) {
        TODO("Not yet implemented")
    }

    override fun onErrorNetwork() {
        TODO("Not yet implemented")
    }

    companion object {

        fun newInstance() = PicsumPhotosFragment()
    }
}