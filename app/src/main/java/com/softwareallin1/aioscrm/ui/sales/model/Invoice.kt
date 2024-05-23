package com.softwareallin1.aioscrm.ui.sales.model

data class Invoice(
    var status: String,
    var customer: String,
    var amount: String,
    var project: String,
    var totalTex: String,
    var tags: ArrayList<String>,
    var date: String,
    var dueDate: String
)
