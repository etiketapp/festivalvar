package com.example.festivalvar.data.remote

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

interface IRemoteDataManager {

    /** Auth **/
    suspend fun postRegisterAsync(request: RegisterRequest): ResultWrapper<RegisterResponse>

    suspend fun postLoginAsync(request: LoginRequest): ResultWrapper<LoginResponse>
    suspend fun postForgotPasswordAsync(request: ForgotPasswordRequest): ResultWrapper<ForgotPasswordResponse>
    suspend fun postLogoutAsync(): ResultWrapper<LogoutResponse>

    /** User **/
    suspend fun getUserMeAsync(): ResultWrapper<UserResponse>

    suspend fun updateProfileAsync(
        _method: RequestBody,
        image: MultipartBody.Part?,
        full_name: RequestBody,
        email: RequestBody
    ): ResultWrapper<UserUpdateResponse>

    suspend fun putPaswwordUpdateAsync(
        request: UserUpdatePasswordRequest,
        userId: Int
    ): ResultWrapper<UserUpdatePasswordResponse>

    suspend fun getLikedFestivalsAsync(userId: Int): ResultWrapper<LikedFestivalsModelResponse>
    suspend fun getCommentedFestivalAsync(userId: Int): ResultWrapper<CommentedFestivalModelResponse>
    suspend fun getUserDrawsAsync(userId: Int): ResultWrapper<UserDrawsResponse>
    suspend fun getUserProfileDrawsAsync(userId: Int): ResultWrapper<UserDrawsResponse>
    suspend fun getUserProfileLikedFestivalAsync(userId: Int): ResultWrapper<LikedFestivalsModelResponse>
    suspend fun getUserProfileCommentedFestivalAsync(userId: Int): ResultWrapper<CommentedFestivalModelResponse>

    /** Categories **/
    suspend fun getCategoryAsync(): ResultWrapper<CategoriesResponse>


    /** Festival**/
    suspend fun getFestivalAsync(): ResultWrapper<FestivalModelResponse>
    suspend fun getFestivalCommentUserAsync(festivalId: Int): ResultWrapper<FestivalCommentsUserResponse>
    suspend fun getFestivalLikeUserAsync(festivalId: Int): ResultWrapper<FestivalLikesModelResponse>
    suspend fun getFestivalLikeActAsync(festivalId: Int): ResultWrapper<FestivalLikesResponse>
    suspend fun getFestivalDislikeActAsync(festivalId: Int): ResultWrapper<FestivalLikesResponse>
    suspend fun postFestivalCommentAsync(request: PostCommentModelRequest): ResultWrapper<PostCommentModelResponse>


    /** Draws **/
    suspend fun getDrawsAsync(): ResultWrapper<DrawsModelResponse>
    suspend fun getDrawsJoinAsync(drawId: Int): ResultWrapper<DrawsJoinModelResponse>
    suspend fun getDrawsDisjoinAsync(drawId: Int): ResultWrapper<DrawsJoinModelResponse>
    suspend fun getDrawsUserAsync(drawId: Int): ResultWrapper<UserDrawsResponse>

    /** Messages **/
     suspend fun postSendMessageAsync(request: MessageSendModelRequest): ResultWrapper<MessageSendModelResponse>
     suspend fun getMessageIndexAsync(): ResultWrapper<MessageIndexResponse>
     suspend fun getMessageDetailAsync(userTwoId: Int): ResultWrapper<MessageDetailModelResponse>


}