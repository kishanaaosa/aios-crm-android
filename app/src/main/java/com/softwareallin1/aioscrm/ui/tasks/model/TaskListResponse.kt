package com.softwareallin1.aioscrm.ui.tasks.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TaskListResponse {
    @JsonProperty("task_list")
    var taskList: ArrayList<Task>? = arrayListOf()
}