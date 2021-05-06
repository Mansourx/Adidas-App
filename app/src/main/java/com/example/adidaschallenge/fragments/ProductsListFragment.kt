@file:Suppress("DEPRECATION")

package com.example.adidaschallenge.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
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
import com.example.adidaschallenge.dialog.AdidasDialog
import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.network.UIState
import com.example.adidaschallenge.toast
import com.example.adidaschallenge.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products_list.*
import java.util.*


class ProductsListFragment : Fragment(), SearchView.OnQueryTextListener,
        ProductsAdapter.ProductActionListener, AdidasDialog.AlertAction {

    private var productsAdapter: ProductsAdapter? = null
    private var _binding: FragmentProductsListBinding? = null
    private val displayList: MutableList<Product> = arrayListOf()
    private val products: MutableList<Product> = arrayListOf()
    var viewModel: ProductsViewModel? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_products_list, container, false
        )
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
                this.products.clear()
                this.products.addAll(products)
            }
        }
        viewModel?.observeProductsList()?.observe(viewLifecycleOwner, productsObserver)

        val loadingObserver = Observer<UIState> {
            when (it) {
                is UIState.Loading -> showHideTransparentProgressBar(true)
                is UIState.NetworkError -> {
                    showHideTransparentProgressBar(false)
                    showNetworkError()
                }
                is UIState.Completed -> showHideTransparentProgressBar(false)
                is UIState.GenericError -> {
                    showNetworkError()
                    showHideTransparentProgressBar(false)
                    val errMsg = if (it.errorMsg.isNotEmpty()) it.errorMsg else
                        getString(R.string.error)
                    context?.toast(errMsg)
                }
                else -> Unit
            }
        }
        viewModel?.observeUiState()?.observe(viewLifecycleOwner, loadingObserver)
    }

    private fun showNetworkError() {
        AdidasDialog().apply {
            getAlertDialog(
                    requireContext(), getString(R.string.error),
                    getString(R.string.internet_lost_message),
                    getString(R.string.retry), getString(R.string.cancel), false,
                    this@ProductsListFragment
            )
        }
    }

    private fun showHideTransparentProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    private fun updateProductsList(products: List<Product>) {
        displayList.clear()
        displayList.addAll(products)
        this.productsAdapter = ProductsAdapter(
                this@ProductsListFragment,
                requireContext()
        ).also {
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
            if (it.id?.toLowerCase(Locale.getDefault())?.contains(search!!) == true) {
                displayList.add(it)
            }
        }
        binding.productsList.adapter?.notifyDataSetChanged()
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun navigateToFragment(id: String) {
        view?.findNavController()?.navigate(
                ProductsListFragmentDirections.actionProductListFragmentToProductDetaisFragment(id)
        )
    }

    override fun positiveMethod(dialog: DialogInterface?, id: Int) {
        viewModel?.getAllProducts()
    }

    override fun negativeMethod(dialog: DialogInterface?, id: Int) = Unit

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}