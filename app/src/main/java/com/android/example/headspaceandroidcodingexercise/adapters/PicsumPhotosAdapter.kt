package com.android.example.headspaceandroidcodingexercise.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.headspaceandroidcodingexercise.databinding.PhotosListItemsBinding
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotosItem

class PicsumPhotosAdapter : RecyclerView.Adapter<PicsumPhotosViewHolder>() {

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
        holder.onBind(picsumPhotosList[position])
    }

    override fun getItemCount(): Int = picsumPhotosList.size

}

class PicsumPhotosViewHolder(private val binding: PhotosListItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(picsumPhotosItem: PicsumPhotosItem) {
        binding.imageSrcId.setImageURI(Uri.parse(picsumPhotosItem.downloadUrl))
        binding.imageAuthorId.text = picsumPhotosItem.author
        binding.imageWidthId.text = picsumPhotosItem.width.toString()
        binding.imageHeightId.text = picsumPhotosItem.height.toString()
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