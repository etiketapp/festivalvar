package com.example.festivalvar.ui.comments

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUser
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import com.example.festivalvar.ui.base.IBaseNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentsViewModel(dataManager: DataManager) : BaseViewModel<ICommentsNavigator>(dataManager) {

    val festivalCommentList: MutableLiveData<FestivalCommentsUser> = MutableLiveData()

    fun getFestivalComments(festivalId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getFestivalCommentUserAsync(festivalId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalCommentList.value = result.data.data

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