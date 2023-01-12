package com.example.bookshelf.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.databinding.FragmentBookShelfsBinding
import com.example.bookshelf.viewModel.BookShelfsViewModel
import kotlinx.coroutines.launch


class BookShelfsFragment : Fragment() {

    private val viewModel: BookShelfsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BookShelfsViewModel.Factory(activity.application))
            .get(BookShelfsViewModel::class.java)
    }
    private var _binding: FragmentBookShelfsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookShelfsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        // Giving the binding access to the BookShelfsViewModel
        binding.viewModel = viewModel

        binding.recyclerView.adapter = BookShelfAdapter(viewModel)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Observer for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        // Activate refresh layout
        binding.mySwipeRefreshLayout.setOnRefreshListener {
            //
            viewLifecycleOwner.lifecycleScope.launch{
                //connect the fragment to database throe the viewModel
                viewModel.getDataFromRepository()
                // stop refreshing after refresh the page
                binding.mySwipeRefreshLayout.isRefreshing = false
            }
        }


    }



    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
    // Set Menu option to move from BookShelfs fragment to Favorites fragment
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_move_to_favorites_layout -> {

                val action = BookShelfsFragmentDirections.actionBookShelfsFragmentToFavoritesFragment()
                findNavController().navigate(action)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_move_to_favorites_layout)
        setIcon(layoutButton)
    }
}