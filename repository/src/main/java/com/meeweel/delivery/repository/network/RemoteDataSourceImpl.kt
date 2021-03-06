package com.meeweel.delivery.repository.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.model.Mapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceImpl(private val map: Mapper = Mapper()) :
    com.meeweel.delivery.repository.DataSource<List<DataModel>> {

    override fun insertData(list: List<DataModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getData(): List<DataModel> {
        return map.convertNetworkDTOListToDataModelList(getService(BaseInterceptor.interceptor).search(
            pizza, order
        ))
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://private-anon-fca3cac9cf-pizzaapp.apiary-mock.com/restaurants/11/"
        private const val order = "rank"
        private const val pizza = "Pizza"
    }
}