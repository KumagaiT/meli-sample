package com.kumagai.melisample.presentation.search

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kumagai.melisample.R
import com.kumagai.melisample.domain.model.SearchResultItem

class SearchAdapter(
    private val dataSet: Array<SearchResultItem>,
    private val context: Context,
    private val listener: SearchItemClickListener
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemTitle: TextView = view.findViewById(R.id.tv_item_title)
        val ivThumbnail: ImageView = view.findViewById(R.id.iv_item_thumb)
        val tvItemPrice: TextView = view.findViewById(R.id.tv_item_price)
        val tvItemOriginalPrice: TextView = view.findViewById(R.id.tv_item_original_price)
        val clRoot: ConstraintLayout = view.findViewById(R.id.cl_item)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.tvItemTitle.text = item.title
        viewHolder.tvItemPrice.text = item.price

        item.originalPrice?.let {
            viewHolder.tvItemOriginalPrice.run {
                this.visibility = View.VISIBLE
                this.text = it
                this.paintFlags = (this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
            }
        }

        Glide
            .with(context)
            .load(item.thumbnailUrl)
            .into(viewHolder.ivThumbnail)

        viewHolder.clRoot.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount() = dataSet.size
}

interface SearchItemClickListener {
    fun onItemClick(item: SearchResultItem)
}
