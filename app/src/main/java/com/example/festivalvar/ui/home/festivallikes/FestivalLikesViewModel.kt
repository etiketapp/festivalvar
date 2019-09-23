package com.example.festivalvar.ui.home.festivallikes

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUser
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModel
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FestivalLikesViewModel(dataManager: DataManager): BaseViewModel<IFestivalLikesNavigator>(dataManager) {


    val festivalLikedList: MutableLiveData<FestivalLikesModel> = MutableLiveData()

    fun getFestivalLikes(festivalId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getFestivalLikeUserAsync(festivalId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalLikedList.value = result.data.data

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