package com.example.festivalvar.data.remote.service.categories

import com.example.festivalvar.data.remote.model.categories.CategoriesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ICategoryService {


    @GET("category")
    fun getCategroies(): Deferred<Response<CategoriesResponse>>
}