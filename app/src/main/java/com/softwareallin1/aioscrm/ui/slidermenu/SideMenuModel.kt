package com.softwareallin1.aioscrm.ui.slidermenu

import android.graphics.drawable.Drawable

data class SideMenuModel(
    var id: String? = null,
    var imageId: Drawable? = null,
    var name: String? = "",
    var isSelected: Boolean? = false,
    var fragmentId: Int? = null,
    var isVisible: Boolean? = false
)