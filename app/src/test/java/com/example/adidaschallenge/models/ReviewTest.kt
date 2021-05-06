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


class ReviewTest {

    private val mProductId = "FA112"
    private val mLocale = "Netherlands"
    private val mRating = 5
    private val mText = "FA112"

    @MockK
    lateinit var reviewModel: Review

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        reviewModel.apply {
            every { productId } returns mProductId
            every { locale } returns mLocale
            every { rating } returns mRating
            every { text } returns mText
        }
    }

    @Test
    fun testProductId() {
        val expected = mProductId
        val actual = reviewModel.productId
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testLocal() {
        val expected = mLocale
        val actual = reviewModel.locale
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testRating() {
        val expected = mRating
        val actual = reviewModel.rating
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testTextIncorrect() {
        val expected = "mText"
        val actual = reviewModel.text
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun testProductIdIncorrect() {
        val expected = "mProductId"
        val actual = reviewModel.productId
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun testLocalIncorrect() {
        val expected = "mLocale"
        val actual = reviewModel.locale
        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun testRatingIncorrect() {
        val expected = "mRating"
        val actual = reviewModel.rating
        assertThat(actual).isNotEqualTo(expected)
    }

    @After
    fun teardown() {
        // Clean Up
    }
}