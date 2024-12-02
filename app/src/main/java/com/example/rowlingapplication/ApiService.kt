package com.example.rowlingapplication

import retrofit2.http.GET

interface ApiService {
    @GET("fr/books")
    suspend fun getBooks() : List<Book>
}