package com.github.erlonprifsp.photos.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.erlonprifsp.photos.databinding.TilePhotoImageBinding

class PhotoImageAdapter(
    val activityContext: Context,
    val photoImageList: MutableList<Bitmap>)
    : RecyclerView.Adapter<PhotoImageAdapter.PhotoImageViewHolder>() {

        inner class PhotoImageViewHolder(tpib: TilePhotoImageBinding): RecyclerView.ViewHolder(tpib.photoImageIv) {
            val photoImageIv: ImageView = tpib.photoImageIv
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoImageViewHolder =
        PhotoImageViewHolder(TilePhotoImageBinding.inflate(LayoutInflater.from(activityContext), parent, false))

    override fun onBindViewHolder(holder: PhotoImageViewHolder, position: Int) =
        holder.photoImageIv.setImageBitmap(photoImageList[position])


    override fun getItemCount(): Int = photoImageList.size

}