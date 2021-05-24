

package com.narek.flickrtest.home.model

import android.text.TextUtils
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.narek.flickrtest.BuildConfig
import com.narek.flickrtest.home.ContractInterface

class PhotoHomeInteractor : ContractInterface.Model {

    private var page = 0
    private var searchText = ""
    private val flickrUrl =
        "https://www.flickr.com/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1"

    override fun requestPhotoDataAPI(
        searchText: String,
        presenter: ContractInterface.ModelToPresenter
    ) {

        if (this.searchText != searchText) {
            page = 0;
            this.searchText = searchText
        }

        val url = flickrUrl +
                "&api_key=${BuildConfig.FLICKR_APIKEY}" +
                "&text=$searchText" +
                "&page=$page"

        AndroidNetworking.get(url)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(
                PhotoResponseNullable::class.java,
                object : ParsedRequestListener<PhotoResponseNullable> {
                    override fun onResponse(photoResponseNullable: PhotoResponseNullable) {
                        val articlesItemList = photoResponseNullable.articles?.articles

                        if (articlesItemList?.size ?: 0 > 0) {
                            page += 1;
                        }

                        if (articlesItemList != null && articlesItemList.isNotEmpty()) {
                            presenter.onResultSuccess(articlesItemList)
                        } else {
                            presenter.onResultFail("Nothing to show")
                        }
                    }

                    override fun onError(anError: ANError) {
                        // handle error
                        presenter.onResultFail("Something went wrong")
                    }
                })
    }
}