package com.ecommercwebsite.aioscrm.bind

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ecommercwebsite.aioscrm.R
import com.google.android.material.textfield.TextInputLayout

/**
 * Bind data used for data binding
 */
class BindAdapters {
    companion object {


        /**
         * load image from url
         */
        @JvmStatic
        @BindingAdapter(
            "loadImage",
            "isCircleCrop",
            "placeholder",
            requireAll = false // make the attributes optionals
        )
        fun loadImage(
            view: ImageView, url: String?,
            circleCrop: Boolean = false,
            placeholder: Drawable?,
        ) {
            if (!url.isNullOrEmpty()) {
                Glide.with(view.context).load(url).let { request ->
                    if (circleCrop) {
                        request.circleCrop()
                    }
                    if (placeholder != null) {
                        request.placeholder(placeholder)
                    }
                    request.into(view)
                }
            }
            else{
                view.setImageResource(R.mipmap.ic_launcher)
            }
        }


        @BindingAdapter("bind:hintWithAsterisk")
        @JvmStatic
        fun setHintWithAsterisk(view: TextInputLayout, hint: String) {
            val spannableString = SpannableString("$hint*")



            spannableString.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(view.context, R.color.purple_500)
                ),
                spannableString.length - 1,
                spannableString.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )

            view.hint = spannableString
        }


        /**
         * load image from url
         */
        @JvmStatic
        @BindingAdapter("isStrikeThruText")
        fun isStrikeThruText(
            view: TextView,
            isStrikeThruText: Boolean
        ) {
            if (isStrikeThruText) {
                view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }



    }
}