package com.example.festivalvar.ui.draws.drawsdetail

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.draws.join.DrawsJoinModel
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrawsDetailViewModel(dataManager: DataManager): BaseViewModel<IDrawsDetailNavigator>(dataManager) {

    val isJoinedData: MutableLiveData<DrawsJoinModel> = MutableLiveData()

    fun getDrawJoin(drawId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getDrawsJoinAsync(drawId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    isJoinedData.value = result.data.data
                    getNavigator()?.drawJoinSuccess()

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

    fun getDrawDisjoin(drawId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getDrawsDisjoinAsync(drawId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    isJoinedData.value = result.data.data
                    getNavigator()?.drawJoinSuccess()

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