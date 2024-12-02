package com.example.rowlingapplication

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RowlingRepo @Inject constructor(private var service: ApiService)  {

    suspend fun getBooks(): Result<List<Book>> {
        return try {
            val response = service.getBooks()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}