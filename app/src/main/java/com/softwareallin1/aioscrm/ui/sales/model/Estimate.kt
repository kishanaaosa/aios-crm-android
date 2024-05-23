package com.softwareallin1.aioscrm.ui.sales.model

data class Estimate(
    var status: String,
    var date: String,
    var expiryDate: String,
    var customer: String,
    var reference: String,
    var project: String,
    var estimate: String,
    var amount: String,
    var totalTax: String,
    var tags: ArrayList<String>
)
