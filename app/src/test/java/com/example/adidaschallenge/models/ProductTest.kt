package com.example.adidaschallenge.models

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


class ProductTest {

    private var mCurrency = "EU"
    private var mPrice = 300
    private var mId = "F123"
    private var mName = "Adidas"
    private var mDescription = "Adidas Floy Floy is the Full Natural Material"
    private var mImageUrl = ""

    @MockK
    lateinit var productModel: Product

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productModel.apply {
            every { currency } returns mCurrency
            every { price } returns mPrice
            every { id } returns mId
            every { name } returns mName
            every { description } returns mDescription
            every { imgUrl } returns mImageUrl
        }
    }

    @Test
    fun getCurrency() {
        val expected = mCurrency
        val actual = productModel.currency
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getPrice() {
        val expected = mPrice
        val actual = productModel.price
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getId() {
        val expected = mId
        val actual = productModel.id
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getName() {
        val expected = mName
        val actual = productModel.name
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getDescription() {
        val expected = mDescription
        val actual = productModel.description
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getImgUrl() {
        val expected = mImageUrl
        val actual = productModel.imgUrl
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getCurrencyIncorrect() {
        val expected = "mCurrency"
        val actual = productModel.currency
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun getPriceIncorrect() {
        val expected = "mPrice"
        val actual = productModel.price
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun getIdIncorrect() {
        val expected = "mId"
        val actual = productModel.id
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun getNameIncorrect() {
        val expected = "mName"
        val actual = productModel.name
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun getDescriptionIncorrect() {
        val expected = "mDescription"
        val actual = productModel.description
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun getImgUrlIncorrect() {
        val expected = "mImageUrl"
        val actual = productModel.imgUrl
        assertThat(actual).isNotEqualTo(expected)
    }

    @After
    fun teardown() {
    }
}