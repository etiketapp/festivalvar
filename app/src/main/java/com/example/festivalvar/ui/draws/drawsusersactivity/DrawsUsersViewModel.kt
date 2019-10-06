package com.example.festivalvar.ui.draws.drawsusersactivity

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrawsUsersViewModel(dataManager: DataManager):BaseViewModel<IUserDrawsNavigator>(dataManager) {


    val userDrawsList: MutableLiveData<ArrayList<UserDraws>> = MutableLiveData()

    fun getUserDraws(drawId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getDrawsUserAsync(drawId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    userDrawsList.value = result.data.data

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