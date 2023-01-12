package com.example.bookshelf

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.domain.ModelBook
import com.example.bookshelf.ui.BookShelfAdapter
import com.example.bookshelf.ui.FavoritesAdapter

// to conv http link to https link
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
        }
    }
}

// adapter list to list data in recyclerView in bookShelf page
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<ModelBook>?) {
    val adapter = recyclerView.adapter as BookShelfAdapter
    adapter.submitList(data)
}

// adapter list to list data in recyclerView in Favorite page
@BindingAdapter("listDataFavorites")
fun bindFavoritesRecyclerView(recyclerView: RecyclerView,
                     data: List<ModelBook>?) {
    val adapter = recyclerView.adapter as FavoritesAdapter
    adapter.submitList(data)
}