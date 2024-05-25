package com.kumagai.melisample.presentation

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.kumagai.melisample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(R.layout.search_activity) {

    private val fragmentContainer: FrameLayout by lazy { findViewById(R.id.fl_fragment_container) }
    private val lavLoading: LottieAnimationView by lazy { findViewById(R.id.lav_loading) }

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel.searchLiveData.observe(this) {
            handleLoadingVisibility(it == SearchState.Loading)
            when (it) {
                SearchState.Empty -> {

                }

                SearchState.Error -> {

                }
                SearchState.Loading -> { }
                is SearchState.Success -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_fragment_container, SearchResultFragment()).addToBackStack(null).commit()
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container, SearchFragment()).commit()
    }

    private fun handleLoadingVisibility(isVisible: Boolean) {
        if (isVisible) {
            lavLoading.visibility = View.VISIBLE
            lavLoading.playAnimation()
        } else {
            lavLoading.visibility = View.GONE
            lavLoading.cancelAnimation()
        }
    }
}