package com.ecommercwebsite.aioscrm.bind

import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.ecommercwebsite.aioscrm.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

/**
 * Bind data used for data binding
 */
class BindAdapters {
    companion object {

        /**
         * Set first char
         */
        @BindingAdapter("firstChar")
        @JvmStatic
        fun setFirstCharacter(textView: TextView, text: String?) {
            text?.let {
                if (it.isNotEmpty()) {
                    textView.text = it.substring(0, 1)
                }
            }
        }


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

        /**
         * set drawable tint & background tint
         */
        @JvmStatic
        @BindingAdapter("bind:setTint")
        fun setTint(view: MaterialButton, isSelected: Boolean) {
            if (isSelected == true) {
                view.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(view.context, R.color.color6DC99B)
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.compoundDrawableTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(view.context, R.color.white)
                    )
                } else {
                    val drawablesprev: Array<Drawable> = view.compoundDrawables
                    drawablesprev[1].colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(view.context, R.color.white),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }

            } else {
                view.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(view.context, R.color.color106E394A)
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.compoundDrawableTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(view.context, R.color.color6DC99B)
                    )
                } else {
                    val drawablesprev: Array<Drawable> = view.compoundDrawables
                    drawablesprev[1].colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            R.color.color6DC99B
                        ), PorterDuff.Mode.SRC_ATOP
                    )
                }
            }
        }

        /**
         * load profile image
         */
        @JvmStatic
        @BindingAdapter("bind:loadProfileImage")
        fun loadProfileImage(view: ImageView, url: String?) {
            view.load(url) {
                crossfade(true)
                fallback(R.drawable.ic_profile)
                error(R.drawable.ic_profile)
                placeholder(R.drawable.ic_profile)
            }
        }




    }
}