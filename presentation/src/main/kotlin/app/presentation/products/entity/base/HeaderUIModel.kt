package app.presentation.products.entity.base

class HeaderUIModel(
    val isVisible: Boolean,
    val title: String,
    val isBackButtonVisible: Boolean = false,
    val isCloseBtnVisible: Boolean = false,
    val isBottomNavVisible: Boolean = true,
)