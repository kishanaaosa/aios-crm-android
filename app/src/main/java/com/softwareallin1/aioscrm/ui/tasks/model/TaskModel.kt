package com.softwareallin1.aioscrm.ui.tasks.model

data class TaskModel(
    var priority: String,
    var title: String,
    var description: String,
    var status: String,
    var tags: ArrayList<String>,
    var startDate: String,
    var endDate: String
)