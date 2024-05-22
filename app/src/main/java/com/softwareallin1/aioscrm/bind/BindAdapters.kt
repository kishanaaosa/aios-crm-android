package com.softwareallin1.aioscrm.bind

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.softwareallin1.aioscrm.R

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
        fun setFirstCharacter(textView: TextView, text: String? = "A") {
            text?.let {
                if (it.isNotEmpty()) {
                    textView.text = it.substring(0, 1)
                } else {
                    textView.text = "A"
                }
            }
        }

        @BindingAdapter("setAppointmentStatusWiseColors")
        @JvmStatic
        fun setAppointmentStatusWiseColors(textView: TextView, text: String?) {
            when (text) {
                "Finished" -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.outline_green)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.colorGradientStart
                        )
                    )
                }

                "Pending Approval" -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.outline_orange)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.colorGradientStartC73B1C
                        )
                    )
                }

                "Missed" -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.outline_pink)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.color_F50057
                        )
                    )
                }

                "Cancelled" -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.outline_blue)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.color_1976D2
                        )
                    )
                }

                else -> {
                    textView.background =
                        ContextCompat.getDrawable(textView.context, R.drawable.outline_blue)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.colorGradientEnd131D32
                        )
                    )
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
            } else {
                view.setImageResource(R.mipmap.ic_launcher)
            }
        }

        @JvmStatic
        @BindingAdapter("app:loadImageGlide")
        fun loadImageGlide(imageView: ImageView, url: String?) {
            Glide.with(imageView)
                .asBitmap()
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : SimpleTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                    ) {
                        Palette.from(resource).generate { palette ->
                            imageView.setBackgroundColor(palette!!.getLightVibrantColor(com.google.android.material.R.attr.colorAccent))
                        }
                    }
                })

            imageView.clipToOutline = true
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
                spannableString.length,
                SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )

            view.hint = spannableString
        }


        @JvmStatic
        @BindingAdapter("isStrikeThruText")
        fun isStrikeThruText(
            view: TextView, isStrikeThruText: Boolean
        ) {
            if (isStrikeThruText) {
                view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        /*   */
        /**
         * set drawable tint & background tint
         *//*
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
        }*/

        @JvmStatic
        @BindingAdapter("bind:setTintToMTV")
        fun setTintToMTV(view: MaterialTextView, text: String) {
            val colorList = arrayListOf(
                "#0070FF",
                "#00B140",
                "#FF6EC7",
                "#FFD100",
                "#9D50FF",
                "#FFA500",
                "#000000",
                "#008080"
            )
            colorList.random().let {
                //val color = ContextCompat.getColor(view.context, it)
                val color = Color.parseColor(it) // Example color, you can use any color
                view.backgroundTintList = ColorStateList.valueOf(color)
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