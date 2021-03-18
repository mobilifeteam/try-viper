package com.mobilife.employyim.view.widget

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mobilife.employyim.R
import kotlinx.android.synthetic.main.view_input.view.*


class CustomInputView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_input, this)
    }

    fun setUpView(title: String,
                  hint: String,
                  isPassword: Boolean = false,
                  alertMsg: String? = null) {
        tv_title.text = title
        et_value.hint = hint
        et_value.inputType = if (isPassword) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT
        }
        alertMsg?.let {
            tv_alert.text = it
        } ?: run {
            tv_alert.visibility = View.INVISIBLE
        }

    }

}