package com.example.bookshelf.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentBookShelfsBinding
import com.example.bookshelf.databinding.FragmentFavoritesBinding
import com.example.bookshelf.viewModel.BookShelfsViewModel
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private val viewModel: BookShelfsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BookShelfsViewModel.Factory(activity.application))
            .get(BookShelfsViewModel::class.java)
    }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        // Giving the binding access to the BookShelfsViewModel
        binding.viewModel = viewModel

        binding.recyclerView.adapter = FavoritesAdapter(viewModel)
        return binding.root
    }

}