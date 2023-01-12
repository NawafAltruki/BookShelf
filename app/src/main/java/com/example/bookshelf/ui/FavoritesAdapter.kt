package com.example.bookshelf.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.databinding.FavoritesRecyclerViewBinding
import com.example.bookshelf.domain.ModelBook
import com.example.bookshelf.viewModel.BookShelfsViewModel
import kotlinx.coroutines.launch

class FavoritesAdapter(viewModel: BookShelfsViewModel) : ListAdapter<ModelBook,
        FavoritesAdapter.ModelBookViewHolder>(DiffCallback) {

    val viewModel = viewModel
    companion object DiffCallback : DiffUtil.ItemCallback<ModelBook>() {
        override fun areItemsTheSame(oldItem: ModelBook, newItem: ModelBook): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelBook, newItem: ModelBook): Boolean {
            return oldItem.title == newItem.title
            return oldItem.subtitle == newItem.subtitle
            return oldItem.author == newItem.author
            return oldItem.price == newItem.price
            return oldItem.lang == newItem.lang
            return oldItem.pages == newItem.pages
            return oldItem.publisher == newItem.publisher
            return oldItem.ratingCount == newItem.ratingCount
            return oldItem.averageRating == newItem.averageRating
            return oldItem.publisherDate == newItem.publisherDate
            return oldItem.description == newItem.description
            return oldItem.info == newItem.info
            return oldItem.thumbnail == newItem.thumbnail
            return oldItem.wepReader == newItem.wepReader
            return oldItem.favorite == newItem.favorite
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ModelBookViewHolder {
        return ModelBookViewHolder(FavoritesRecyclerViewBinding.inflate( LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ModelBookViewHolder, position: Int) {
        val modelBook = getItem(position)
        holder.bind(modelBook)

        // Card view click listener
        holder.card.setOnClickListener{

            val action = FavoritesFragmentDirections.actionFavoritesFragmentToBookFragment(name = modelBook.title.toString(), subtital = modelBook.subtitle.toString(), author = modelBook.author, price = modelBook.price.toString(), modelBook.lang.toString(), modelBook.pages.toString(), modelBook.publisher.toString(), modelBook.ratingCount.toString(), modelBook.averageRating.toString(), modelBook.publisherDate.toString(), description = modelBook.description.toString(), image = modelBook.thumbnail.toString(), info = modelBook.info.toString(), webReader = modelBook.wepReader.toString() )
            holder.itemView.findNavController().navigate(action)


        }

        // Favorite button to cancel the book favorite
        holder.favoriteBtn.setOnClickListener{

            if(modelBook.favorite == true){
                modelBook.favorite = false
                viewModel.viewModelScope.launch {
                    viewModel.booksRepository.update(
                        DatabaseBooks(
                        modelBook.id,
                        modelBook.title,
                        modelBook.subtitle,
                        modelBook.author,
                        modelBook.price,
                        modelBook.lang,
                        modelBook.pages,
                        modelBook.publisher,
                        modelBook.ratingCount,
                        modelBook.averageRating,
                        modelBook.publisherDate,
                        modelBook.description,
                        modelBook.thumbnail,
                        modelBook.info,
                        modelBook.wepReader,
                        modelBook.favorite!!)
                    )
                }
                holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_border)

            }



        }


    }

    class ModelBookViewHolder(private var binding:
                              FavoritesRecyclerViewBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        val card : CardView =binding.bookCard

        val favoriteBtn: ImageButton = binding.favoriteBtn
        // Data binding to bind the ModelBook into recyclerView Xml
        fun bind(modelBook: ModelBook) {
            binding.books = modelBook
            binding.executePendingBindings()
        }
    }
}