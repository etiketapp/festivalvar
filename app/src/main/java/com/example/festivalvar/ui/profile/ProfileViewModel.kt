package com.example.festivalvar.ui.profile

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(dataManager: DataManager): BaseViewModel<IProfileNavigator>(dataManager) {


    val festivalLikeDataList: MutableLiveData<ArrayList<LikedFestivalsModel>> = MutableLiveData()
    val festivalCommentDataList: MutableLiveData<ArrayList<CommentedFestivalModel>> = MutableLiveData()
    val userDrawsDataList: MutableLiveData<ArrayList<UserDraws>> = MutableLiveData()

    fun getFestivalLike(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getLikedFestivalsAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalLikeDataList.value = result.data.data

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

    fun getFestivalComment(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getCommentedFestivalAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalCommentDataList.value = result.data.data

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

    fun getUserDraws(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getUserDrawsAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    userDrawsDataList.value = result.data.data

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

    fun getProfileFestivalLike(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getUserProfileLikedFestivalAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalLikeDataList.value = result.data.data

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

    fun getProfileFestivalComment(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getUserProfileCommentedFestivalAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    festivalCommentDataList.value = result.data.data

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

    fun getProfileUserDraws(userId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getUserProfileDrawsAsync(userId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    userDrawsDataList.value = result.data.data

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