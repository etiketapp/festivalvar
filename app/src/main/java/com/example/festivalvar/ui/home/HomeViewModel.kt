package com.example.festivalvar.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.categories.Categories
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(dataManager: DataManager): BaseViewModel<IHomeNavigator>(dataManager) {


    val festivalDataList: MutableLiveData<ArrayList<FestivalModel>> = MutableLiveData()
    val categoryDataList: MutableLiveData<ArrayList<Categories>> = MutableLiveData()
    val festivalLike: MutableLiveData<FestivalLikes> = MutableLiveData()
    val festivalDislike: MutableLiveData<FestivalLikes> = MutableLiveData()

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

    fun getCategoryList() {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getCategoryAsync() }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    categoryDataList.value = result.data.data

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

    fun getFestivalLike(festivalId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getFestivalLikeActAsync(festivalId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalLike.value = result.data.data

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

    fun getFestivalDislike(festivalId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getFestivalDislikeActAsync(festivalId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalLike.value = result.data.data

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