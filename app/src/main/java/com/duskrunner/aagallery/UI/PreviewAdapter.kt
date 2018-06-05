package com.duskrunner.aagallery.UI

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.duskrunner.aagallery.R
import com.duskrunner.aagallery.model.CollectionPreviewPhoto
import com.squareup.picasso.Picasso

class PreviewAdapter(picasso: Picasso, photos: List<CollectionPreviewPhoto>): RecyclerView.Adapter<PreviewAdapter.PhotoViewHolder>() {

    private lateinit var picasso: Picasso
    private lateinit var photos: List<CollectionPreviewPhoto>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_fragment_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.count()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        picasso.load(photo.urls.small).into(holder.image)
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView as ImageView
    }
}