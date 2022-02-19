package io.lojong.com.data.remote

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
class FactRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchAllFacts(): Result<FactResponse> {
        val factService = retrofit.create(FactService::class.java);
        return getResponse(
                request = { factService.getAllFacts() },
                defaultErrorMessage = "Error fetching Movie list")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {

        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}