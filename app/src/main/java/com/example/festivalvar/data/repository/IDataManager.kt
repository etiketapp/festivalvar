package com.example.festivalvar.data.repository

import com.example.festivalvar.data.local.ILocalDataManager
import com.example.festivalvar.data.remote.IRemoteDataManager


interface IDataManager : IRemoteDataManager, ILocalDataManager {

}