package com.example.adidaschallenge.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.adidaschallenge.R
import com.example.adidaschallenge.adapters.ReviewsAdapter
import com.example.adidaschallenge.databinding.FragmentProductDetailsBinding
import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.UIState
import com.example.adidaschallenge.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment(), View.OnClickListener {

    var viewModel: ProductsViewModel? = null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    var productId = ""
    private var reviewsAdapter: ReviewsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_details,
            container, false
        )
        val args = ProductDetailsFragmentArgs.fromBundle(requireArguments())
        productId = args.productId
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        binding.addReviewBtn.setOnClickListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.getProduct(productId)
        viewModel?.getReviews(productId)
        registerObservers()
    }

    private fun displayProductReviews(reviews: List<Review>?) {
        reviewsAdapter = ReviewsAdapter(reviews)
        binding.reviewsList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.reviewsList.adapter?.notifyDataSetChanged()
    }

    private fun registerObservers() {
        val productObserver = Observer<Product> { product ->
            product?.let {
                displayProductDetails(product)
            }
        }
        viewModel?.observeProduct()?.observe(viewLifecycleOwner, productObserver)

        val reviewsObserver = Observer<List<Review>> { reviews ->
            reviews?.let {
                displayProductReviews(reviews)
            }
        }
        viewModel?.observeReviews()?.observe(viewLifecycleOwner, reviewsObserver)

        val reviewObserver = Observer<Review> { review ->
            review?.let {
                viewModel?.getReviews(productId)
            }
        }
        viewModel?.observeReviewDetails()?.observe(viewLifecycleOwner, reviewObserver)

        val loadingObserver = Observer<UIState> {
            when (it) {
                is UIState.Loading -> showHideTransparentProgressBar(true)
                is UIState.NetworkError -> {
                    showHideTransparentProgressBar(false)
                }
                is UIState.Completed -> showHideTransparentProgressBar(false)
                is UIState.GenericError -> {
                    showHideTransparentProgressBar(false)
                    val errMsg = if (it.errorMsg.isNotEmpty()) it.errorMsg else
                        getString(R.string.no_date_error)
                    Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }
        viewModel?.observeUiState()?.observe(viewLifecycleOwner, loadingObserver)
    }

    private fun showHideTransparentProgressBar(show: Boolean) {
        if (show) {
            progressBar2?.visibility = View.VISIBLE
        } else {
            progressBar2?.visibility = View.GONE
        }
    }

    private fun displayProductDetails(product: Product?) {
        binding.apply {
            priceText.text = product?.price.toString()
            descriptionText.text = product?.description
            nameProduct.text = product?.name
            Glide.with(requireContext()).load(product?.imgUrl)
                .apply(RequestOptions().centerInside())
                .into(productImage)
        }
    }

    private fun showAddReviewDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.layout_review_dialog, null)
        val reviewEditText = dialogLayout.findViewById<EditText>(R.id.review_dialog_Edit_text)

        with(builder) {
            setTitle(getString(R.string.add_review))
            setPositiveButton(getString(R.string.add)) { _, _ ->
                val review = Review(productId, "NL", 5, reviewEditText.text.toString())
                viewModel?.addReview(productId, review)
            }
            setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            setView(dialogLayout)
            show()
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.add_review_btn -> {
                showAddReviewDialog()
            }
        }
    }
}