package com.example.festivalvar.ui.draws

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrawFragmentViewModel(dataManager: DataManager): BaseViewModel<IDrawFragmentNavigator>(dataManager) {


    val drawsDataList: MutableLiveData<ArrayList<DrawsModel>> = MutableLiveData()

    fun getDrawsList() {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getDrawsAsync() }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    drawsDataList.value = result.data.data

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