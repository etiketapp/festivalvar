package com.example.festivalvar.data.remote


import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.network.RemoteDataException
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.remote.service.IAuthService
import com.example.festivalvar.data.remote.service.IFestivalService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteDataManager(
    private val authService: IAuthService,
    private val festivalService: IFestivalService
) : IRemoteDataManager {
    override suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse>  = withContext(Dispatchers.Main){
        resultWrapper(festivalService.getFestivals())
    }

    override suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse> = withContext(Dispatchers.Main) {
        resultWrapper(authService.postForgotPassword(request))
    }

    override suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse> = withContext(Dispatchers.Main){
        resultWrapper(authService.postLogin(request))

    }

    override suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse> = withContext(Dispatchers.Main) {
        resultWrapper(authService.postRegister(request))
    }




    private suspend inline fun <reified T : Any> resultWrapper(request: Deferred<Response<T>>): ResultWrapper<T> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error(RemoteDataException(response.errorBody()))
            }
        } catch (ex: Throwable) {
            ResultWrapper.Error(RemoteDataException(ex))
        }
    }


}