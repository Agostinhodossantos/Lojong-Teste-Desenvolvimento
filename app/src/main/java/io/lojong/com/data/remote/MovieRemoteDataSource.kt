package io.lojong.com.data.remote

import android.util.Log
import io.lojong.com.model.Result
import io.lojong.com.model.FactResponse
import io.lojong.com.network.services.FactService
import io.lojong.com.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchTrendingMovies(): Result<FactResponse> {
        val movieService = retrofit.create(FactService::class.java);
        return getResponse(
                request = { movieService.getPopularMovies() },
                defaultErrorMessage = "Error fetching Movie list")

    }

//    suspend fun fetchMovie(id: Int): Result<MovieDesc> {
//        val movieService = retrofit.create(MovieService::class.java);
//        return getResponse(
//                request = { movieService.getMovie(id) },
//                defaultErrorMessage = "Error fetching Movie Description")
//    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {

        return try {
            Log.d("TAGG", "Test")
//            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            Log.d("TAGG", result.toString())
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Log.d("TAGG", e.toString())
            Result.error("Unknown Error", null)
        }
    }
}