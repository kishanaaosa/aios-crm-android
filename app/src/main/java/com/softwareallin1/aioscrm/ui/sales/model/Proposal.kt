package com.softwareallin1.aioscrm.ui.sales.model

data class Proposal(
    var title: String,
    var status: String,
    var to: String,
    var dateCreated: String,
    var openTill: String,
    var tags: ArrayList<String>
    )
