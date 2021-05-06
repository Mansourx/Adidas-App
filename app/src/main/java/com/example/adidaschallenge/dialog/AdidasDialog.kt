package com.example.adidaschallenge.dialog

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


class AdidasDialog {

    fun getAlertDialog(mContext: Context, title: String?, msg: String?, positiveBtnCaption: String?,
                       negativeBtnCaption: String?, isCancelable: Boolean, listener: AlertAction) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
        val imageResource = R.drawable.ic_delete
        val image: Drawable? = ContextCompat.getDrawable(mContext, imageResource)
        builder.setTitle(title).setMessage(msg).setIcon(image).setCancelable(false)
                .setPositiveButton(positiveBtnCaption) { dialog, id ->
                    listener.positiveMethod(dialog, id)
                }.setNegativeButton(negativeBtnCaption
                ) { dialog, id -> listener.negativeMethod(dialog, id) }
        val alert: AlertDialog = builder.create()
        alert.setCancelable(isCancelable)
        alert.show()
        if (isCancelable) {
            alert.setOnCancelListener { listener.negativeMethod(null, 0) }
        }
    }

    interface AlertAction {
        fun positiveMethod(dialog: DialogInterface?, id: Int)
        fun negativeMethod(dialog: DialogInterface?, id: Int)
    }

}

