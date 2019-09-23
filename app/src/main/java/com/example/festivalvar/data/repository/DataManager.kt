package com.example.festivalvar.data.repository

import com.example.festivalvar.data.local.LocalDataManager
import com.example.festivalvar.data.remote.RemoteDataManager
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModelResponse
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordRequest
import com.example.festivalvar.data.remote.model.auth.forgotpassword.ForgotPasswordResponse
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.login.LoginResponse
import com.example.festivalvar.data.remote.model.auth.logout.LogoutResponse
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterResponse
import com.example.festivalvar.data.remote.model.categories.CategoriesResponse
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUserResponse
import com.example.festivalvar.data.remote.model.comments.PostCommentModelRequest
import com.example.festivalvar.data.remote.model.comments.PostCommentModelResponse
import com.example.festivalvar.data.remote.model.draws.DrawsModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModelResponse
import com.example.festivalvar.data.remote.model.user.UserResponse
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModelResponse
import com.example.festivalvar.data.remote.model.user.draws.UserDrawsResponse
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModelResponse
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordRequest
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordResponse
import com.example.festivalvar.data.remote.model.user.userupdate.UserUpdateResponse
import com.example.festivalvar.data.remote.network.ResultWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody

class DataManager(
    private val remoteDataManager: RemoteDataManager,
    private val localDataManager: LocalDataManager
) : IDataManager {
    override suspend fun postFestivalCommentAsync(request: PostCommentModelRequest): ResultWrapper<PostCommentModelResponse> = remoteDataManager.postFestivalCommentAsync(request)

    override suspend fun getFestivalLikeUserAsync(festivalId: Int): ResultWrapper<FestivalLikesModelResponse> = remoteDataManager.getFestivalLikeUserAsync(festivalId)

    override suspend fun getFestivalCommentUserAsync(festivalId: Int): ResultWrapper<FestivalCommentsUserResponse> = remoteDataManager.getFestivalCommentUserAsync(festivalId)

    override suspend fun getCategoryAsync(): ResultWrapper<CategoriesResponse> = remoteDataManager.getCategoryAsync()

    override suspend fun getUserDrawsAsync(userId: Int): ResultWrapper<UserDrawsResponse> = remoteDataManager.getUserDrawsAsync(userId)

    override suspend fun getCommentedFestivalAsync(userId: Int): ResultWrapper<CommentedFestivalModelResponse> = remoteDataManager.getCommentedFestivalAsync(userId)

    override suspend fun getLikedFestivalsAsync(userId: Int): ResultWrapper<LikedFestivalsModelResponse> = remoteDataManager.getLikedFestivalsAsync(userId)

    override suspend fun putPaswwordUpdateAsync(
        request: UserUpdatePasswordRequest,
        userId: Int
    ): ResultWrapper<UserUpdatePasswordResponse> = remoteDataManager.putPaswwordUpdateAsync(request, userId)

    override suspend fun getDrawsAsync(): ResultWrapper<DrawsModelResponse> = remoteDataManager.getDrawsAsync()

    override suspend fun postLogoutAsync(): ResultWrapper<LogoutResponse> = remoteDataManager.postLogoutAsync()

    override suspend fun getUserMeAsync(): ResultWrapper<UserResponse> = remoteDataManager.getUserMeAsync()

    override suspend fun updateProfileAsync(
        _method: RequestBody,
        image: MultipartBody.Part?,
        full_name: RequestBody,
        email: RequestBody
    ): ResultWrapper<UserUpdateResponse> =
        remoteDataManager.updateProfileAsync(_method, image, full_name, email)

    override suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse> = remoteDataManager.getFestivalAsync()

    override suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse> =
        remoteDataManager.postLoginAsync(request)

    override suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse> =
        remoteDataManager.postRegisterAsync(request)


    override suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse> =
        remoteDataManager.postForgotPasswordAsync(request)

}