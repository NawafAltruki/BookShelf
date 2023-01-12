package com.example.bookshelf.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.database.getDatabase
import com.example.bookshelf.repository.BooksRepository
import kotlinx.coroutines.launch
import java.io.IOException

class BookShelfsViewModel(application: Application) : AndroidViewModel(application) {

    // get database information from repository
    private val _booksRepository = BooksRepository(getDatabase(application))
    val booksRepository get() = _booksRepository

    // get list of books from database
    val addBook = _booksRepository.books

    // get favorite books from database
    val addBookToFavorite = _booksRepository.bookFavorite

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getDataFromRepository()
    }


    //fun to get bookShelf page data from database
    fun getDataFromRepository() {

            viewModelScope.launch {
                try {
                    _booksRepository.addBook()
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false

                } catch (networkError: IOException) {
                    // Show a Toast error message and hide the progress bar.
                    if (addBook.value.isNullOrEmpty())
                        _eventNetworkError.value = true
                }
            }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookShelfsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BookShelfsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}