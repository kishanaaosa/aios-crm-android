package com.softwareallin1.aioscrm.ui.tasks.model


import com.fasterxml.jackson.annotation.JsonProperty

data class Task(
    @JsonProperty("description")
    var description: String?,
    @JsonProperty("enddate")
    var enddate: String?,
    @JsonProperty("id")
    var id: String?,
    @JsonProperty("name")
    var name: String?,
    @JsonProperty("priority")
    var priority: String?,
    @JsonProperty("startdate")
    var startdate: String?,
    @JsonProperty("status")
    var status: String?,
    @JsonProperty("tags_list")
    var tagsList: ArrayList<String?>? = arrayListOf()
)