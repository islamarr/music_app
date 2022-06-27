package com.islam.music.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Fragment.setKeyboardVisibility(isShow: Boolean = true) {
    view?.let { activity?.setKeyboardVisibility(it, isShow) }
}

fun Context.setKeyboardVisibility(view: View, isShow: Boolean) {
    val inputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (isShow)
        inputMethodManager.showSoftInput(
            view,
            InputMethodManager.SHOW_FORCED
        )
    else
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showShowSnackBar(text: String, actionMessage: String, onClick: () -> Unit = {}) {
    val snackBar = this.view?.let {
        Snackbar
            .make(it, text, Snackbar.LENGTH_SHORT)
            .setAction(
                actionMessage
            ) {
                onClick()
            }
    }
    snackBar?.show()
}