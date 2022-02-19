package io.lojong.com.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.lojong.com.model.Fact
import io.lojong.com.model.Result
import io.lojong.com.ui.adapter.FactAdapter
import io.lojong.com.ui.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import lojong.R


/**
 * Shows list of movie/show
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Fact>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var moviesAdapter: FactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Lojong)
        setContentView(R.layout.activity_main)
        forceScrollDown()
       // init()
      //  subscribeUi()
    }

//    private fun init() {
//        title = "Trending Movies"
//        val layoutManager = LinearLayoutManager(this)
//        rvMovies.layoutManager = layoutManager
//
//        val dividerItemDecoration = DividerItemDecoration(
//                rvMovies.context,
//                layoutManager.orientation
//        )
//
//        rvMovies.addItemDecoration(dividerItemDecoration)
//        moviesAdapter = FactAdapter(this, list)
//        rvMovies.adapter = moviesAdapter
//    }
//
//    private fun subscribeUi() {
//        viewModel.movieList.observe(this, Observer { result ->
//
//            when (result.status) {
//                Result.Status.SUCCESS -> {
//                    result.data?.results?.let { list ->
//                        moviesAdapter.updateData(list)
//                    }
//                    loading.visibility = View.GONE
//                }
//
//                Result.Status.ERROR -> {
//                    result.message?.let {
//                        showError(it)
//                    }
//                    loading.visibility = View.GONE
//                }
//
//                Result.Status.LOADING -> {
//                    loading.visibility = View.VISIBLE
//                }
//            }
//
//        })
//    }



    private fun forceScrollDown() {
        vScroll.post {
            vScroll.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun showError(msg: String) {
        Snackbar.make(vScroll, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }
}