package com.kumagai.melisample.presentation.productpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kumagai.melisample.R
import com.kumagai.melisample.domain.model.ItemAttribute

class ProductAttributeAdapter(
    private val dataSet: List<ItemAttribute>
) : RecyclerView.Adapter<ProductAttributeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductAttributeName: TextView = view.findViewById(R.id.tv_product_attribute_name)
        val tvProductAttributeValue: TextView = view.findViewById(R.id.tv_product_attribute_value)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_attribute, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.tvProductAttributeName.text = item.name
        viewHolder.tvProductAttributeValue.text = item.value
    }

    override fun getItemCount() = dataSet.size
}
