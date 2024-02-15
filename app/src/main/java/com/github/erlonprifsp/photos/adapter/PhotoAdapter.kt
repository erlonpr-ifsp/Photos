package com.github.erlonprifsp.photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.github.erlonprifsp.photos.model.PhotoListItem

class PhotoAdapter(
    private val activityContext: Context,
    private val photoList: MutableList<PhotoListItem>

) : ArrayAdapter<PhotoListItem>(activityContext, android.R.layout.simple_list_item_1, photoList) {

    private data class PhotoHolder(val photoTitleTv: TextView) // implementa ViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // criação ou reaproveitamento da célula (view)
        val photoView = convertView ?: LayoutInflater.from(activityContext)
            .inflate(android.R.layout.simple_list_item_1, parent, false).apply {
                tag =
                    PhotoHolder(findViewById(android.R.id.text1)) // tag recebe o text1 que é TextView do simple_list_item_1
            }

        (photoView.tag as PhotoHolder).photoTitleTv.text = photoList[position].title

        return photoView
    }


}