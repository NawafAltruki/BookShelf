package com.example.bookshelf.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookshelf.domain.ModelBook
import kotlinx.coroutines.selects.select

@Dao
interface BookDao {
    // Queries for DatabaseBooks:
    @Query("select * from databasebooks")
    fun getBooks(): LiveData<List<DatabaseBooks>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(books: List<DatabaseBooks>)

    @Query("select * from Databasebooks where favorite = 1 ")
    fun insertToFavorite(): LiveData<List<DatabaseBooks>>

    @Update
    suspend fun update(book:DatabaseBooks)

}

@Database(entities = [DatabaseBooks::class], version = 4)
abstract class BooksDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
}

private lateinit var INSTANCE: BooksDatabase

fun getDatabase(context: Context): BooksDatabase {
    synchronized(BooksDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                BooksDatabase::class.java,
                "Books").build()
        }
    }
    return INSTANCE
}