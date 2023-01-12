package com.example.bookshelf.ui


import android.app.ActionBar
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.bookshelf.MainActivity
import com.example.bookshelf.R
import com.example.bookshelf.bindImage
import com.example.bookshelf.databinding.FragmentBookBinding

class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var nameId: String
    private lateinit var titalId: String
    private lateinit var authorId: String
    private lateinit var priceId: String
    private lateinit var langId: String
    private lateinit var pagesId: String
    private lateinit var publisherId: String
    private lateinit var ratingCountId: String
    private lateinit var averageRatingId: String
    private lateinit var publicherDateId: String
    private lateinit var imageId:String
    private lateinit var descriptionId: String
    private lateinit var infoId:String
    private lateinit var webReaderId:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            nameId = it.getString("name").toString()
            titalId = it.getString("subtital").toString()
            authorId = it.getString("author").toString()
            priceId = it.getString("price").toString()
            langId = it.getString("lang").toString()
            pagesId = it.getString("pages").toString()
            publisherId = it.getString("publisher").toString()
            ratingCountId = it.getString("ratingCount").toString()
            averageRatingId = it.getString("averageRating").toString()
            publicherDateId = it.getString("publisherDate").toString()
            imageId = it.getString("image").toString()
            descriptionId = it.getString("description").toString()
            infoId = it.getString("info").toString()
            webReaderId = it.getString("webReader").toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var name: TextView = binding.tvName
        name.text = nameId

        var tital: TextView = binding.tvSubTital
        tital.text = titalId

        var author: TextView = binding.tvAuthor
        author.text = authorId

        var price: TextView = binding.tvPrice
        price.text = priceId

        var lang: TextView = binding.lang
        lang.text = langId

        var pages: TextView = binding.pages
        pages.text = pagesId

        var publisher: TextView = binding.publisher
        publisher.text = publisherId

        var ratingCount: TextView = binding.ratingCount
        ratingCount.text = ratingCountId

        var averageRating: TextView = binding.averageRating
        averageRating.text = averageRatingId

        var publisherDate: TextView = binding.publisherDate
        publisherDate.text = publicherDateId

        var description: TextView = binding.tvDescription
        description.text = descriptionId

        var image: ImageView = binding.bookImageView

        // bind image to conv http to https
        fun bindImage(imageView: ImageView,imgUri: String?){

            imgUri?.let {
                var imageUri = imgUri.toUri().buildUpon().scheme("https").build()
                imageView.load(imageUri)
            }
        }
        bindImage(image,imageId)

        // information link button to move user to book information page
        var infoLink = binding.infoLink
        infoLink.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${infoId}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }

        // read link button to move user to play book to read the book
        var readLink = binding.readLink
        readLink.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${webReaderId}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }

        // share link button to share book information page
        var shareLink = binding.sharLink
        shareLink.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND).apply {

                val queryUrl: Uri = Uri.parse("${infoId}")
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, "$nameId")
                putExtra(Intent.EXTRA_TEXT, "$queryUrl")
                type = "text/*"
            }
            startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}