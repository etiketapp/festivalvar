package com.example.festivalvar.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(dataManager: DataManager): BaseViewModel<IHomeNavigator>(dataManager) {


    val festivalDataList: MutableLiveData<ArrayList<FestivalModel>> = MutableLiveData()

    fun getFestivalList() {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getFestivalAsync() }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalDataList.value = result.data.data

                }
                is ResultWrapper.Error -> {
                    getNavigator()?.hideLoading()
                    getNavigator()?.onError(
                        result.exception.message,
                        result.exception.code
                    )

                }
            }
        }
    }
}