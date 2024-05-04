package com.softwareallin1.aioscrm.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
open class ResponseListData<T> {

    @JsonProperty("data")
    var data: ArrayList<T>? = null

    override fun toString(): String {
        return "ResponseWrapper{" + "data=" + data.toString()
    }

    @JsonProperty("meta")
    val meta: Meta? = null

}