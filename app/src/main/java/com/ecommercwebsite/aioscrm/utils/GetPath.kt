package com.ecommercwebsite.aioscrm.utils

import android.os.Environment
import java.io.File

object GetPath {

    var list: ArrayList<File?>? = arrayListOf()


    fun getPath(folderPath: String): ArrayList<File?>? {

        // Define the folder name
        val folderName = folderPath

        // Get the external storage directory
        val externalStorageDirectory = Environment.getExternalStorageDirectory()

        // Create a File object representing the directory where you want to create the folder
        val directory = File(externalStorageDirectory, folderName)

        if (!directory.exists()) {
            // Create the directory if it does not exist
            val isDirectoryCreated = directory.mkdirs()

            if (isDirectoryCreated) {
                // Directory created successfully
            } else {
                // Failed to create directory
            }
        } else {
            // Directory already exists

            directory.listFiles()?.forEach { file ->
                // Check if the file is an MP3 file
                DebugLog.d(file.name)
                //TODO:: need to addd more format of audios
                if (file.isFile && file.extension.equals("mp3", ignoreCase = true)) {
                    list?.add(file)
                }

            }

        }
        return list

    }
}