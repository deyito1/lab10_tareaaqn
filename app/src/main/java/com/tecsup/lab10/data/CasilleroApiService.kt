package com.tecsup.lab10.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CasilleroApiService {
    @GET("casilleros/")
    suspend fun obtenerCasilleros(): List<CasilleroModel>

    @GET("casilleros/{id}")
    suspend fun obtenerCasillero(@Path("id") id: String): Response<CasilleroModel>

    @Headers("Content-Type: application/json")
    @POST("casilleros/")
    suspend fun crearCasillero(@Body casillero: CasilleroModel): Response<CasilleroModel>

    @PUT("casilleros/{id}")
    suspend fun actualizarCasillero(@Path("id") id: String, @Body casillero: CasilleroModel): Response<CasilleroModel>

    @DELETE("casilleros/{id}")
    suspend fun eliminarCasillero(@Path("id") id: String): Response<CasilleroModel>
}
