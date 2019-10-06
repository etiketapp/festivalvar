package com.example.festivalvar.ui.messages.messagedetail

import androidx.lifecycle.MutableLiveData
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.example.festivalvar.data.remote.model.messages.messagedetail.MessageDetailModel
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModel
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelRequest
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.repository.DataManager
import com.example.festivalvar.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageDetailViewModel(dataManager: DataManager): BaseViewModel<IMessageDetailNavigator>(dataManager) {


    val messageSendDataList: MutableLiveData<MessageSendModel> = MutableLiveData()
    val messageDetailDataList: MutableLiveData<ArrayList<MessageDetailModel>> = MutableLiveData()


    fun postSendMessage(request: MessageSendModelRequest) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.postSendMessageAsync(request) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    messageSendDataList.value = result.data.data

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

    fun getMessageDetailList(userTwoId: Int) {
        getNavigator()?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { dataManager.getMessageDetailAsync(userTwoId) }) {
                is ResultWrapper.Success -> {
                    getNavigator()?.hideLoading()
                    messageDetailDataList.value = result.data.data

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