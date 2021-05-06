package com.example.adidaschallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.adidaschallenge.TestCoroutineRule
import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.network.api.Api
import com.example.adidaschallenge.network.UIState
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiService: Api

    @Mock
    private var apiProductsObserver: Observer<Any>? = null

    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ProductsViewModel()
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        testCoroutineRule.runBlockingTest {
//            Mockito.doReturn(emptyList<Product>())
//                .`when`(apiService)
//                .getProductsListAsync(PRODUCT_URL)
//            apiProductsObserver?.let { viewModel.observeProductsList().observeForever(it) }
//            Mockito.verify(apiService).getProductsListAsync(PRODUCT_URL)
//            Mockito.verify(apiProductsObserver)?.onChanged(UIState.Completed)
//            apiProductsObserver?.let { viewModel.observeProductsList().removeObserver(it) }
//        }
    }

    companion object {
        const val PRODUCT_URL = "http://192.168.1.149:3001/product/"
        const val REVIEWS_URL = "http://192.168.1.149:3002/reviews/"
    }
}