package com.marquez.detroitlabs.detroitlabs.weatherapp

import android.os.AsyncTask

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.runner.Description
import org.junit.rules.TestRule
import org.junit.runners.model.Statement

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class AsyncTaskSchedulerRule : TestRule {
    val asyncTaskScheduler = Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)


    override fun apply(base: Statement, description:Description): Statement {
        return object :  Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { _ ->asyncTaskScheduler}
                RxJavaPlugins.setComputationSchedulerHandler {_ ->asyncTaskScheduler}
                RxJavaPlugins.setNewThreadSchedulerHandler { _ ->asyncTaskScheduler}
                RxJavaPlugins.setSingleSchedulerHandler { _ ->asyncTaskScheduler}
                RxAndroidPlugins.setMainThreadSchedulerHandler { _ ->asyncTaskScheduler}
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        }
    }
}