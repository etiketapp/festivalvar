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
import com.example.festivalvar.data.remote.model.draws.join.DrawsJoinModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModelResponse
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesResponse
import com.example.festivalvar.data.remote.model.messages.MessageIndexResponse
import com.example.festivalvar.data.remote.model.messages.messagedetail.MessageDetailModelResponse
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelRequest
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelResponse
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
    override suspend fun getUserProfileCommentedFestivalAsync(userId: Int): ResultWrapper<CommentedFestivalModelResponse> = remoteDataManager.getUserProfileCommentedFestivalAsync(userId)

    override suspend fun getUserProfileLikedFestivalAsync(userId: Int): ResultWrapper<LikedFestivalsModelResponse> = remoteDataManager.getUserProfileLikedFestivalAsync(userId)

    override suspend fun getUserProfileDrawsAsync(userId: Int): ResultWrapper<UserDrawsResponse> = remoteDataManager.getUserProfileDrawsAsync(userId)

    override suspend fun getMessageDetailAsync(userTwoId: Int): ResultWrapper<MessageDetailModelResponse> = remoteDataManager.getMessageDetailAsync(userTwoId)

    override suspend fun getMessageIndexAsync(): ResultWrapper<MessageIndexResponse> = remoteDataManager.getMessageIndexAsync()

    override suspend fun postSendMessageAsync(request: MessageSendModelRequest): ResultWrapper<MessageSendModelResponse> = remoteDataManager.postSendMessageAsync(request)

    override suspend fun getFestivalDislikeActAsync(festivalId: Int): ResultWrapper<FestivalLikesResponse> = remoteDataManager.getFestivalDislikeActAsync(festivalId)

    override suspend fun getDrawsUserAsync(drawId: Int): ResultWrapper<UserDrawsResponse> = remoteDataManager.getDrawsUserAsync(drawId)

    override suspend fun getFestivalLikeActAsync(festivalId: Int): ResultWrapper<FestivalLikesResponse> = remoteDataManager.getFestivalLikeActAsync(festivalId)

    override suspend fun getDrawsDisjoinAsync(drawId: Int): ResultWrapper<DrawsJoinModelResponse> = remoteDataManager.getDrawsDisjoinAsync(drawId)

    override suspend fun getDrawsJoinAsync(drawId: Int): ResultWrapper<DrawsJoinModelResponse> = remoteDataManager.getDrawsJoinAsync(drawId)

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