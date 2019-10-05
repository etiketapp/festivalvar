package com.example.festivalvar.ui.messages

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagesFragmentViewModel(dataManager: DataManager): BaseViewModel<IMessagesFragmentNavigator>(dataManager) {


    val messageDataList: MutableLiveData<ArrayList<MessageIndex>> = MutableLiveData()

    fun getMessageList() {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getMessageIndexAsync() }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    messageDataList.value = result.data.data

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