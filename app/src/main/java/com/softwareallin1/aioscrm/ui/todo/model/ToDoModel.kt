package com.softwareallin1.aioscrm.ui.todo.model

data class ToDoModel(
    val position: String = "0",
    val title: String,
    var dateTime: String,
    var isFinished: Boolean = false
)