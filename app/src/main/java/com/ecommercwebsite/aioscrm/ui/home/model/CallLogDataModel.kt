package com.ecommercwebsite.aioscrm.ui.home.model

import java.time.Duration
import java.time.LocalDate

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
