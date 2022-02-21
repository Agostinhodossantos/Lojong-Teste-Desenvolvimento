package io.lojong.com.data

import io.lojong.com.AppClass
import io.lojong.com.data.local.FactDao
import io.lojong.com.data.remote.FactRemoteDataSource
import io.lojong.com.model.Result
import io.lojong.com.model.FactResponse
import io.lojong.com.util.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */
class FactRepository @Inject constructor(
    private val factsRemoteDataSource: FactRemoteDataSource,
    private val factDao: FactDao
) {

    suspend fun fetchFacts(): Flow<Result<Any>?> {
        return flow {
            emit(fetchFactsCached())
            emit(Result.loading())
            val result = factsRemoteDataSource.fetchAllFacts()


            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                emit(Result.requesting("API OK"))
                result.data?.results?.let { it ->
                    factDao.deleteAll(it)
                    factDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchFactsCached(): Result<FactResponse>? =
            factDao.getAll()?.let {
                Result.success(FactResponse(it))
            }


}