

package com.narek.flickrtest.home.model

import com.google.gson.annotations.SerializedName

data class PhotoItem(
        @SerializedName("id") var id: String,
        @SerializedName("server") var server: String,
        @SerializedName("secret") var secret: String
)

fun PhotoItem.url() : String {
        return "https://live.staticflickr.com/"+server+"/"+id+"_"+secret+".jpg"
}