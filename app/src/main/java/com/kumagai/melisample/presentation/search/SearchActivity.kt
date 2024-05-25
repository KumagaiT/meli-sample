package com.kumagai.melisample.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.kumagai.melisample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(R.layout.search_activity) {

    private val lavLoading: LottieAnimationView by lazy { findViewById(R.id.lav_loading) }
    private val gSearchFeedback: Group by lazy { findViewById(R.id.g_empty_feedback) }
    private val tvFeedbackTitle: TextView by lazy { findViewById(R.id.tv_search_feedback_result_title) }
    private val tvFeedbackSubtitle: TextView by lazy { findViewById(R.id.tv_search_feedback_result_subtitle) }

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel.searchLiveData.observe(this) {
            handleLoadingVisibility(it == SearchState.Loading)
            gSearchFeedback.visibility = View.GONE
            when (it) {
                is SearchState.Error -> {
                    handleSearchFeedback(
                        it.errorTitleId,
                        it.errorSubtitleId,
                        it.errorTitleColorId
                    )
                }

                SearchState.Loading -> {}
                is SearchState.Success -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_fragment_container, SearchResultFragment())
                        .addToBackStack(null).commit()
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

    private fun handleSearchFeedback(
        @StringRes title: Int,
        @StringRes subTitle: Int,
        @ColorRes titleColorInt: Int?
    ) {
        gSearchFeedback.visibility = View.VISIBLE
        tvFeedbackTitle.text = getString(title)

        tvFeedbackTitle.setTextColor(ContextCompat.getColor(this, titleColorInt ?: R.color.black))

        tvFeedbackSubtitle.text = getString(subTitle)
    }
}