package com.softwareallin1.aioscrm.ui.sales.model

data class CreditNote(
    var status: String,
    var customer: String,
    var amount: String,
    var project: String,
    var reference: String,
    var remainingAmount: String,
    var createdNotDate: String
)
