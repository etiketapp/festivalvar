package com.example.festivalvar.utils.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun uiScheduler() : Scheduler
    fun ioNewScheduler() : Scheduler
    fun ioScheduler() : Scheduler
}