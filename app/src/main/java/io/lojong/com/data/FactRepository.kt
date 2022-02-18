package io.lojong.com.data

import io.lojong.com.data.local.FactDao
import io.lojong.com.data.remote.MovieRemoteDataSource
import io.lojong.com.model.Result
import io.lojong.com.model.FactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */
class FactRepository @Inject constructor(
        private val movieRemoteDataSource: MovieRemoteDataSource,
        private val movieDao: FactDao
) {

    suspend fun fetchTrendingMovies(): Flow<Result<Any>?> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchTrendingMovies()

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.deleteAll(it)
                    movieDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): Result<FactResponse>? =
            movieDao.getAll()?.let {
                Result.success(FactResponse(it))
            }

//    suspend fun fetchMovie(id: Int): Flow<Result<MovieDesc>> {
//        return flow {
//            emit(Result.loading())
//            emit(movieRemoteDataSource.fetchMovie(id))
//        }.flowOn(Dispatchers.IO)
//    }
}