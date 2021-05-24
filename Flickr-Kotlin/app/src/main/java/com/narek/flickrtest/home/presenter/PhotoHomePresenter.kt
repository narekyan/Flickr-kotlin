
package com.narek.flickrtest.home.presenter

import com.narek.flickrtest.home.ContractInterface
import com.narek.flickrtest.home.model.PhotoHomeInteractor
import com.narek.flickrtest.home.model.PhotoItem


class PhotoHomePresenter(private var photoHomeView: ContractInterface.View?, private val photoHomeInteractor: ContractInterface.Model)
    : ContractInterface.PresenterToView, ContractInterface.ModelToPresenter {


    override fun getPhotoData(searchText: String) {
        photoHomeView?.showProgress()
        photoHomeInteractor.requestPhotoDataAPI(searchText,this)
    }

    override fun onDestroy() {
        photoHomeView = null
    }

    override fun onResultSuccess(arrPhotoUpdates: List<PhotoItem>) {
        photoHomeView?.hideProgress()
        photoHomeView?.setPhotoData(arrPhotoUpdates)
    }

    override fun onResultFail(strError: String) {
        photoHomeView?.hideProgress()
        photoHomeView?.getDataFailed(strError)
    }

}