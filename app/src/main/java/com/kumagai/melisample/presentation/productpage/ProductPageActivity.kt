package com.kumagai.melisample.presentation.productpage

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kumagai.melisample.R
import com.kumagai.melisample.domain.model.SearchResultItem

class ProductPageActivity : AppCompatActivity(R.layout.product_page_activity) {

    private val tvProductTitle: TextView by lazy { findViewById(R.id.tv_product_title) }
    private val tvProductImage: ImageView by lazy { findViewById(R.id.iv_product_image) }
    private val tvProductOriginalPrice: TextView by lazy { findViewById(R.id.tv_product_original_price) }
    private val tvProductPrice: TextView by lazy { findViewById(R.id.tv_product_price) }
    private val btnBuy: Button by lazy { findViewById(R.id.btn_buy_now) }
    private val rvProductAttribute: RecyclerView by lazy { findViewById(R.id.rv_product_attributes) }
    private val tvProductAttributeTitle: TextView by lazy { findViewById(R.id.tv_product_attributes_title) }
    private val btnHeader: ImageView by lazy { findViewById(R.id.iv_product_header_back) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProductPageItem()

        btnHeader.setOnClickListener { finish() }
    }

    private fun showProductPageItem() {
        intent.extras?.getParcelable<SearchResultItem>(ITEM_EXTRA_KEY)?.let { item ->
            tvProductTitle.text = item.title

            Glide
                .with(this)
                .load(item.thumbnailUrl)
                .into(tvProductImage)

            item.originalPrice?.let {
                tvProductOriginalPrice.run {
                    text = it
                    visibility = View.VISIBLE
                    paintFlags = (this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
                }
            }

            tvProductPrice.text = item.price

            if (item.attributeList.isEmpty()) {
                tvProductAttributeTitle.visibility = View.GONE
            } else {
                rvProductAttribute.run {
                    this.isNestedScrollingEnabled = false
                    this.layoutManager =
                        LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                    this.adapter = ProductAttributeAdapter(item.attributeList)
                }
            }

            btnBuy.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.permalink))
                startActivity(intent)
            }

        } ?: finish()
    }

    companion object {
        const val ITEM_EXTRA_KEY = "itemExtraKey"
    }
}