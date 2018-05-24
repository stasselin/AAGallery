package com.duskrunner.aagallery.model

import com.google.gson.annotations.SerializedName

data class Collection(val id: Int,
                      val description: String,
                      val title: String,
                      @SerializedName("cover_photo")
                      val coverPhoto: CollectionCoverPhoto,
                      val previewPhotos: List<CollectionPreviewPhoto>)