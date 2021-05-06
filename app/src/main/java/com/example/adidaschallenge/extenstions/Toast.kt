package com.example.adidaschallenge

import android.content.Context
import android.widget.Toast


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
