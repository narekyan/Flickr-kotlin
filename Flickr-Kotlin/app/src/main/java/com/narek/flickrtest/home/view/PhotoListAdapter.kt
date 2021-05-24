

package com.narek.flickrtest.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.narek.flickrtest.R
import com.narek.flickrtest.home.model.PhotoItem
import com.narek.flickrtest.home.model.url



class PhotoListAdapter(private val context: Context) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val arrPhotoUpdates = mutableListOf<PhotoItem>()


    fun addItems(arrPhotoUpdates: List<PhotoItem>) {
        this.arrPhotoUpdates.addAll(arrPhotoUpdates)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return arrPhotoUpdates.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_photo_list, null)
        }
        val imageView = convertView!!.findViewById<ImageView>(R.id.imageview)

        val item = arrPhotoUpdates[position]
        Glide.with(context).load(item.url()).into(imageView);

        return convertView
    }
}