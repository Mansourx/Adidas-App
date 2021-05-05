//package com.example.adidaschallenge.dialog
//
//import android.R
//import android.app.Dialog
//import android.content.Context
//import android.content.DialogInterface
//import android.os.Bundle
//import android.view.View
//import android.widget.EditText
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatDialogFragment
//
//
///**
// * Created by Ahmad Mansour on 5/5/21
// * Dubai, UAE.
// */
//
//class ReviewDialog : AppCompatDialogFragment() {
//
//    private var reviewEditText: EditText? = null
//    private var listener: ReviewDialogListener? = null
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        val inflater = requireActivity().layoutInflater
//        val view: View = inflater.inflate(R.layout.l, null)
//        builder.setView(view)
//            .setTitle("Add Review")
//            .setNegativeButton("cancel",
//                DialogInterface.OnClickListener { _, i -> })
//            .setPositiveButton("ok",
//                DialogInterface.OnClickListener { _, i ->
//                    val review = reviewEditText?.text.toString()
//                    listener!!.applyReview(review)
//                })
//        reviewEditText = view.findViewById(R.id.rev)
//        return builder.create()
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = try {
//            context as ReviewDialogListener
//        } catch (e: ClassCastException) {
//            throw ClassCastException(
//                context.toString() +
//                        "must implement ExampleDialogListener"
//            )
//        }
//    }
//
//}
//
//interface ReviewDialogListener {
//    fun applyReview(review: String?)
//}
//