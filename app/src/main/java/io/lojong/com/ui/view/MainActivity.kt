package io.lojong.com.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.lojong.com.model.Fact
import io.lojong.com.model.Result
import io.lojong.com.ui.adapter.FactAdapter
import io.lojong.com.ui.viewmodel.ListingViewModel
import io.lojong.com.util.setGone
import io.lojong.com.util.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import lojong.R


/**
 * Shows list of movie/show
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var factsList = ArrayList<Fact>()
    private val viewModel by viewModels<ListingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Lojong)
        setContentView(R.layout.activity_main)
        forceScrollDown()
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.movieList.observe(this, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        factsList = list as ArrayList<Fact>
                        handleOnClickListener()
                    }
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                }

                Result.Status.LOADING -> {

                }
            }

        })
    }

    private fun handleOnClickListener() {
        buttonNumberOne.setOnClickListener {
            showDialog(factsList[0].text)
            handleElephantVisibility(elephantOne)
        }
        buttonNumberTwo.setOnClickListener {
            showDialog(factsList[1].text)
            handleElephantVisibility(elephantTwo)
        }
        buttonNumberThree.setOnClickListener {
            showDialog(factsList[2].text)
            handleElephantVisibility(elephantThree)
        }
        buttonNumberFour.setOnClickListener {
            showDialog(factsList[3].text)
            handleElephantVisibility(elephantFour)
        }
        buttonNumberFive.setOnClickListener {
            showDialog(factsList[4].text)
            handleElephantVisibility(elephantFive)
        }

    }

    private fun showDialog(text: String?) {

    }

    private fun handleElephantVisibility(elephant: LottieAnimationView) {
        when (elephant.id) {
            R.id.elephantOne -> {
                elephantOne.setVisible()
                elephantTwo.setGone()
                elephantThree.setGone()
                elephantFour.setGone()
                elephantFive.setGone()
            }
            R.id.elephantTwo -> {
                elephantOne.setGone()
                elephantTwo.setVisible()
                elephantThree.setGone()
                elephantFour.setGone()
                elephantFive.setGone()
            }
            R.id.elephantThree -> {
                elephantOne.setGone()
                elephantTwo.setGone()
                elephantThree.setVisible()
                elephantFour.setGone()
                elephantFive.setGone()
            }
            R.id.elephantFour -> {
                elephantOne.setGone()
                elephantTwo.setGone()
                elephantThree.setGone()
                elephantFour.setVisible()
                elephantFive.setGone()
            }
            R.id.elephantFive -> {
                elephantOne.setGone()
                elephantTwo.setGone()
                elephantThree.setGone()
                elephantFour.setGone()
                elephantFive.setVisible()
            }
        }

    }

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