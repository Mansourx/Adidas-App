package com.example.adidaschallenge

import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


class APIAvailabilityTest {

    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val connection = URL("http://192.168.1.149:3001/product/").openConnection()
        val response = connection.getInputStream()
        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }
        assert(buffer.isNotEmpty())
    }

}