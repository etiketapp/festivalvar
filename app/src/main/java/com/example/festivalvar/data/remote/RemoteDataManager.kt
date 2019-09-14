package com.example.festivalvar.data.remote


import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.logout.LogoutResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.model.user.UserResponse
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdateResponse
import com.example.festivalvar.data.remote.network.RemoteDataException
import com.example.festivalvar.data.remote.network.ResultWrapper
import com.example.festivalvar.data.remote.service.IAuthService
import com.example.festivalvar.data.remote.service.IFestivalService
import com.example.festivalvar.data.remote.service.user.IUserService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class RemoteDataManager(
    private val authService: IAuthService,
    private val festivalService: IFestivalService,
    private val userService: IUserService
) : IRemoteDataManager {
    override suspend fun getUserMeAsync(): ResultWrapper<UserResponse> = withContext(Dispatchers.Main) {
        resultWrapper(userService.getUserMe())

    }


    override suspend fun updateProfileAsync(
        _method: RequestBody,
        image: MultipartBody.Part?,
        full_name: RequestBody,
        email: RequestBody
    ): ResultWrapper<UserUpdateResponse> = withContext(Dispatchers.Main) {
        resultWrapper(
            userService.postUserUpdate(
                _method,
                image,
                full_name,
                email)
        )
    }

    override suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse>  = withContext(Dispatchers.Main){
        resultWrapper(festivalService.getFestivals())
    }

    override suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse> = withContext(Dispatchers.Main) {
        resultWrapper(authService.postForgotPassword(request))
    }

    override suspend fun postLogoutAsync(): ResultWrapper<LogoutResponse> = withContext(Dispatchers.Main) {
        resultWrapper(authService.postLogout())
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