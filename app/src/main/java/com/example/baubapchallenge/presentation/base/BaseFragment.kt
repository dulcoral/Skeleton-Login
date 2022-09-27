package com.example.baubapchallenge.presentation.base

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun showProgressDialog(msg: String? = null) {
        try {
            ProgressDialog.show(childFragmentManager, msg)
        } catch (ex: Exception) {
            Log.e(TAG_ERROR, ex.message, ex)
        }
    }

    fun dismissProgressDialog() {
        ProgressDialog.dismiss()
    }

    fun showMessage(message: String) {
        activity?.let { Toast.makeText(it, message, Toast.LENGTH_LONG).show() }
    }

    companion object {
        const val TAG_ERROR = "PROGRESS_DIALOG_ERROR"
    }
}
