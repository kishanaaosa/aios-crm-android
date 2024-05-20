package com.softwareallin1.aioscrm.ui.notification.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NotificationResponse {
    @JsonProperty("notifications")
    var notifications: Boolean? = false
}