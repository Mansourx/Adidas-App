package com.example.adidaschallenge.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adidaschallenge.R
import com.example.adidaschallenge.adapters.ProductsAdapter
import com.example.adidaschallenge.databinding.FragmentProductsListBinding
import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.network.UIState
import com.example.adidaschallenge.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products_list.*
import java.util.*


@Suppress("DEPRECATION")
class ProductsListFragment : Fragment(), SearchView.OnQueryTextListener,
    ProductsAdapter.ProductActionListener {

    private var productsAdapter: ProductsAdapter? = null
    private var _binding: FragmentProductsListBinding? = null
    private val displayList: MutableList<Product> = arrayListOf()
    private val products: MutableList<Product> = arrayListOf()
    var viewModel: ProductsViewModel? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_products_list, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.getAllProducts()
        registerObservers()
    }

    private fun registerObservers() {
        val productsObserver = Observer<List<Product>> { products ->
            products?.let {
                updateProductsList(products)
                this.products.addAll(products)
            }
        }
        viewModel?.observeProductsList()?.observe(viewLifecycleOwner, productsObserver)

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
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    private fun updateProductsList(products: List<Product>) {
        displayList.addAll(products)
        this.productsAdapter = ProductsAdapter(this@ProductsListFragment, requireContext()).also {
            it.setData(displayList)
        }
        binding.productsList.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        productsAdapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(query: String?): Boolean {
        displayList.clear()
        val search = query?.toLowerCase(Locale.getDefault())
        products.forEach {
            if (it.name?.toLowerCase(Locale.getDefault())?.contains(search!!) == true) {
                displayList.add(it)
            }
        }
        binding.productsList.adapter?.notifyDataSetChanged()
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun open(id: String) {
        view?.findNavController()?.navigate(ProductsListFragmentDirections.actionProductListFragmentToProductDetaisFragment(id))
    }
}