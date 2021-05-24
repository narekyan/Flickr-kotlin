

package com.narek.flickrtest.home.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.narek.flickrtest.R
import com.narek.flickrtest.home.ContractInterface
import com.narek.flickrtest.home.model.PhotoHomeInteractor
import com.narek.flickrtest.home.model.PhotoItem
import com.narek.flickrtest.home.presenter.PhotoHomePresenter
import kotlinx.android.synthetic.main.activity_photo_home.*

class PhotoHomeActivity : AppCompatActivity(), ContractInterface.View {
    private lateinit var photoHomePresenter: ContractInterface.PresenterToView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_home)

        photoHomePresenter = PhotoHomePresenter(this, PhotoHomeInteractor())

        setupUi()
    }

    private fun setupUi() {
        hideProgress()
        grid_view.adapter = PhotoListAdapter(context = this)
        grid_view.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int, visibleItemCount: Int,
                totalItemCount: Int
            ) {
                val lastItem = firstVisibleItem + visibleItemCount
                if (lastItem == totalItemCount && totalItemCount > 0) {
                    photoHomePresenter.getPhotoData(edit_search_text.text.toString())
                }
            }

            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
            }
        })

        edit_search_text.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                photoHomePresenter.getPhotoData(edit_search_text.text.toString())

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(edit_search_text.windowToken, 0)

                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false
        }
    }


    override fun showProgress() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressbar.visibility = View.GONE
    }

    override fun setPhotoData(arrPhotoUpdates: List<PhotoItem>) {
        (grid_view.adapter as PhotoListAdapter).addItems(arrPhotoUpdates)
    }

    override fun getDataFailed(strError: String) {
        Toast.makeText(this, strError, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        photoHomePresenter.onDestroy()
        super.onDestroy()
    }


}
