package com.ecommercwebsite.aioscrm.base

data class ToolbarModel(
    var isVisible: Boolean = false,
    var title: String? = "",
    var isTitleDropdown: Boolean? = false,
    var profileUrl: String? = "",
    var isBottomNavVisible: Boolean = false,
    var isMenuVisible: Boolean = false,
    var isUserProfileVisible: Boolean = false,
    var isBackButtonVisible: Boolean = false,
    var isSearchVisible: Boolean = false,
    var isCallVisible: Boolean = false,
    var isDownloadVisible: Boolean = false,
    var isMoreVisible: Boolean = false,
    var isNotificationVisible: Boolean = false
)