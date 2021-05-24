

package com.narek.flickrtest.home.model

import com.google.gson.annotations.SerializedName

data class PhotoResponseNullable(
        @SerializedName("photos") val articles: PhotoPageResponseNullable? = null
)

data class PhotoPageResponseNullable(
        @SerializedName("photo") val articles: List<PhotoItem>? = null
)