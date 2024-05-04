package com.softwareallin1.aioscrm.ui.home.model

data class CallLogItem(
    val number: String,
    val date: Long,
    val type: String,
    val duration : String
)

data class OnlyMsgModel(
    val message: String,
    val success: Boolean,
)
