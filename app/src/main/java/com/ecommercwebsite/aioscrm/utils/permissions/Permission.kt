package com.ecommercwebsite.aioscrm.utils.permissions

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {

    object RecordAudio : Permission(RECORD_AUDIO)
    object Contact : Permission(READ_CONTACTS, WRITE_CONTACTS)
    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
    object Camera : Permission(CAMERA)
    object Storage : Permission(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
    object PostNotification : Permission(POST_NOTIFICATIONS)
    object CallPhone : Permission(CALL_PHONE)


    companion object {
        fun from(permission: String) = when (permission) {
            RECORD_AUDIO -> RecordAudio
            READ_CONTACTS,WRITE_CONTACTS -> Contact
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
            WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE -> Storage
            POST_NOTIFICATIONS -> PostNotification
            CALL_PHONE -> CallPhone
            CAMERA -> Camera
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }
}