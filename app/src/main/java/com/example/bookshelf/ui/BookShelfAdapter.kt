package com.example.bookshelf.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.databinding.BooksRecyclerViewBinding
import com.example.bookshelf.domain.ModelBook
import com.example.bookshelf.viewModel.BookShelfsViewModel
import kotlinx.coroutines.launch

class BookShelfAdapter(viewModel: BookShelfsViewModel) : ListAdapter<ModelBook,
        BookShelfAdapter.ModelBookViewHolder>(DiffCallback) {

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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookShelfAdapter.ModelBookViewHolder {
        return ModelBookViewHolder(BooksRecyclerViewBinding.inflate( LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: BookShelfAdapter.ModelBookViewHolder, position: Int) {
        val modelBook = getItem(position)
        holder.bind(modelBook)

        // Card view click listener
        holder.card.setOnClickListener{

            val action = BookShelfsFragmentDirections.actionBookShelfsFragmentToBookFragment(name = modelBook.title.toString(), subtital = modelBook.subtitle.toString(), author = modelBook.author, price = modelBook.price.toString(), modelBook.lang.toString(), modelBook.pages.toString(), modelBook.publisher.toString(), modelBook.ratingCount.toString(), modelBook.averageRating.toString(), modelBook.publisherDate.toString(), description = modelBook.description.toString(), image = modelBook.thumbnail.toString(), info = modelBook.info.toString(), webReader = modelBook.wepReader.toString() )
            holder.itemView.findNavController().navigate(action)


        }

        // check the favorite to set the favorite button image
        Log.e("TAG", "onBindViewHolder: ${modelBook.favorite}", )
        if(modelBook.favorite == false){
            holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_border)
        }else{
            holder.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
        }


        // favorite button to switch the favorite value in database to allow favorite page to get the favorite book
        holder.favoriteBtn.setOnClickListener{

                if(modelBook.favorite == true){
                    modelBook.favorite = false

                    viewModel.viewModelScope.launch {
                        viewModel.booksRepository.update(DatabaseBooks(
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
                            modelBook.favorite!!))
                    }
                    holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_border)

                }else {
                    modelBook.favorite = true

                    viewModel.viewModelScope.launch {
                        viewModel.booksRepository.update(DatabaseBooks(
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
                            modelBook.favorite!!))
                    }
                    holder.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)

                }

            holder.name.text

        }


    }

    class ModelBookViewHolder(private var binding:
                              BooksRecyclerViewBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        val card : CardView =binding.bookCard

        val favoriteBtn: ImageButton = binding.favoriteBtn

        val name: TextView = binding.tvName

        // binding data to bind the modelBook data into recyclerView Xml
        fun bind(Models: ModelBook) {
            binding.books = Models
            binding.executePendingBindings()
        }
    }
}