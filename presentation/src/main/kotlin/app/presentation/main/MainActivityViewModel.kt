package app.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.presentation.products.entity.base.ViewSettingsUIModel

class MainActivityViewModel : ViewModel() {
    val viewSettingsUIModel = MutableLiveData<ViewSettingsUIModel>()

}