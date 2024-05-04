package com.softwareallin1.aioscrm.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object FillUtils {
    /**
     * get File path for Image & Thumb image
     *
     * @
     */
    fun getPathFromUri(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex)
            }
        }
        return filePath
    }


    /**
     * Compress Original image
     *
     * @param filePath original image file path
     * @param mContext Context
     * @return compressed Image file path
     */
  /*  fun compressOriginalImage(mContext: Context, filePath: String): String {
        val compressor = Compressor.
       *//* val compressor =
            Compressor(mContext).setQuality(85).setCompressFormat(Bitmap.CompressFormat.JPEG)*//*
        return try {
            compressor.compressToFile(File(filePath)).absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            filePath
        }
    }*/
}