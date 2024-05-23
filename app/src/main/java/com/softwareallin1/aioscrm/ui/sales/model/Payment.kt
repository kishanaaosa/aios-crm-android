package com.softwareallin1.aioscrm.ui.sales.model

data class Payment(
    var customer: String,
    var amount: String,
    var paymentMode: String,
    var invoice: String,
    var transactionId: String,
    var date: String
)
