package com.android.example.headspaceandroidcodingexercise.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.headspaceandroidcodingexercise.models.PicsumPhotos

class PicsumPhotosAdapter : RecyclerView.Adapter<PicsumPhotosViewHolder>(){

    var picsumPhotosList : PicsumPhotos = PicsumPhotos()

    fun updatePicsumPhotosList(photos: PicsumPhotos){
        picsumPhotosList.clear()
        picsumPhotosList.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumPhotosViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PicsumPhotosViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = picsumPhotosList.size

}

class PicsumPhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}
