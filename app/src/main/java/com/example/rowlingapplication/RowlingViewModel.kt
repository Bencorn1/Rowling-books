package com.example.rowlingapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RowlingViewModel @Inject constructor(private var repo: RowlingRepo): ViewModel() {
    private val _books = MutableLiveData<Result<List<Book>>>()
    val books: LiveData<Result<List<Book>>> get() = _books


    fun fetchBooks() {
        viewModelScope.launch {
            _books.value = repo.getBooks()
        }
    }
}