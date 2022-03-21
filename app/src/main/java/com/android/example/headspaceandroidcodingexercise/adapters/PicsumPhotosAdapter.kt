package com.android.example.headspaceandroidcodingexercise.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.headspaceandroidcodingexercise.R
import com.android.example.headspaceandroidcodingexercise.databinding.PhotosListItemsBinding
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem
import com.bumptech.glide.Glide
import javax.inject.Inject

class PicsumPhotosAdapter @Inject constructor(private val iOpenImage: IOpenImage) :
    RecyclerView.Adapter<PicsumPhotosAdapter.PicsumPhotosViewHolder>() {

    private var picsumPhotosList: MutableList<PicsumPhotosItem> = mutableListOf()

    fun updatePicsumPhotosList(photos: List<PicsumPhotosItem>) {
        picsumPhotosList.clear()
        picsumPhotosList.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumPhotosViewHolder {
        return PicsumPhotosViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PicsumPhotosViewHolder, position: Int) {
        val picsumPhotoListItem = picsumPhotosList[position]
        holder.onBind(picsumPhotoListItem)

        holder.itemView.findViewById<ImageView>(R.id.image_src_id).setOnClickListener {
            //Pass a reference of the interface with the url string
            openImage(picsumPhotoListItem)
        }
        holder.itemView.findViewById<TextView>(R.id.url_id).setOnClickListener {
            //Pass a reference of the interface with the url string
            openImage(picsumPhotoListItem)
        }
    }

    private fun openImage(picsumPhotoListItem: PicsumPhotosItem) {
        //Loading the URL in the ImageView using Glide
        Log.d("tag", picsumPhotoListItem.downloadUrl)
        iOpenImage.openImage(picsumPhotoListItem.downloadUrl)
    }

    override fun getItemCount(): Int = picsumPhotosList.size


    class PicsumPhotosViewHolder(private val binding: PhotosListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(picsumPhotosItem: PicsumPhotosItem) {
            val uriPic = Uri.parse(picsumPhotosItem.downloadUrl)
            Glide.with(binding.imageSrcId)
                .load(uriPic)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.imageSrcId)
            binding.imageSrcId.visibility = View.VISIBLE
            binding.imageAuthorId.text = "Author: ${picsumPhotosItem.author}"
            binding.imageWidthId.text = "Width: ${picsumPhotosItem.width}"
            binding.imageHeightId.text = "Height: ${picsumPhotosItem.height}"
            binding.urlId.text = "Image URL: ${picsumPhotosItem.downloadUrl}"
        }

        companion object {
            fun from(parent: ViewGroup) =
                PicsumPhotosViewHolder(
                    PhotosListItemsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }

    }
}

