package com.example.bookshelf.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.bookshelf.database.BookDao
import com.example.bookshelf.database.BooksDatabase
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.database.asDomainModel


import com.example.bookshelf.domain.ModelBook
import com.example.bookshelf.network.ApiNetwork
import com.example.bookshelf.network.asDatabaseModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class BooksRepository(private val database : BooksDatabase) {
    // Actions to get all info from DatabaseBooks
    val books: LiveData<List<ModelBook>> = Transformations.map(database.bookDao.getBooks()) {
        it.asDomainModel()
    }

    // Action to insert All info from Api to DatabaseBooks
    suspend fun addBook(){
        withContext(Dispatchers.IO) {
           val book = ApiNetwork.retrofitService.getBook()
            database.bookDao.insertAll(book.asDatabaseModel()!!)
        }
    }

    // Action to update database values
    suspend fun update(book: DatabaseBooks) = database.bookDao.update(book)

    // Action to list data in favorite page
    val bookFavorite : LiveData<List<ModelBook>> = Transformations.map(database.bookDao.insertToFavorite()){
        it.asDomainModel()
    }
}