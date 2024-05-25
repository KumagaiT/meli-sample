package com.kumagai.melisample.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kumagai.melisample.R
import com.kumagai.melisample.domain.model.SearchResultItem
import com.kumagai.melisample.presentation.pdp.ProductPageActivity
import com.kumagai.melisample.presentation.pdp.ProductPageActivity.Companion.ITEM_EXTRA_KEY

class SearchResultFragment : Fragment(R.layout.search_result_fragment) {

    private lateinit var rvSearchResult: RecyclerView
    private lateinit var tvHeaderTitle: TextView
    private lateinit var ivHeaderButton: ImageView

    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSearchResult = view.findViewById(R.id.rv_search_result)
        tvHeaderTitle = view.findViewById(R.id.tv_header_title)
        ivHeaderButton = view.findViewById(R.id.iv_header_back)

        setupView()
    }

    private fun setupView() {
        val searchState = searchViewModel.searchLiveData.value

        if (searchState is SearchState.Success) {
            tvHeaderTitle.text = searchState.query
            setRecycleView(searchState.data.toTypedArray())
        } else {
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        ivHeaderButton.setOnClickListener {  activity?.supportFragmentManager?.popBackStackImmediate() }
    }

    private fun setRecycleView(dataSet: Array<SearchResultItem>) {
        val dividerItemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val adapter = SearchAdapter(dataSet, this.requireContext(), getOnItemClickListener())

        rvSearchResult.run {
            this.addItemDecoration(dividerItemDecoration)
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

    private fun getOnItemClickListener(): SearchItemClickListener =
        object : SearchItemClickListener {
            override fun onItemClick(item: SearchResultItem) {
                val intent = Intent(requireContext(), ProductPageActivity::class.java)

                intent.putExtra(ITEM_EXTRA_KEY, item)

                startActivity(intent)
            }
        }
}


