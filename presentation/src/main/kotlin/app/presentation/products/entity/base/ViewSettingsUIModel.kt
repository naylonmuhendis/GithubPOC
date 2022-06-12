package app.presentation.products.entity.base

class ViewSettingsUIModel(
    val isHeaderVisible: Boolean,
    val title: String,
    val isBackButtonVisible: Boolean = false,
    val isCloseBtnVisible: Boolean = false,
    val isBottomNavVisible: Boolean = true,
    val isThemeChangeButtonVisible: Boolean = true,
)