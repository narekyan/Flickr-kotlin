package com.narek.flickrtest.home

import com.narek.flickrtest.home.model.PhotoHomeInteractor
import com.narek.flickrtest.home.model.PhotoItem


interface ContractInterface {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setPhotoData(arrPhotoUpdates: List<PhotoItem>)
        fun getDataFailed(strError: String)
    }

    interface PresenterToView {
        fun getPhotoData(searchText: String)
        fun onDestroy()
    }

    interface ModelToPresenter {
        fun onResultSuccess(arrPhotoUpdates: List<PhotoItem>)
        fun onResultFail(strError: String)
    }

    interface Model {
        fun requestPhotoDataAPI(searchText: String, presenter: ModelToPresenter)
    }

}