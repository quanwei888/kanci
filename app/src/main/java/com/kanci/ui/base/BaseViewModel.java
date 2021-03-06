/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.kanci.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.persistence.room.Room;
import android.databinding.ObservableBoolean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanci.data.AppDataManager;
import com.kanci.data.DataManager;
import com.kanci.data.local.db.AppDatabase;
import com.kanci.data.local.db.AppDbHelper;
import com.kanci.data.local.db.DbHelper;
import com.kanci.data.local.prefs.AppPreferencesHelper;
import com.kanci.data.remote.AppApiHelper;
import com.kanci.utils.AppConstants;
import com.kanci.utils.NetworkUtils;
import com.kanci.utils.rx.AppSchedulerProvider;
import com.kanci.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amitshekhar on 07/07/17.
 */

public abstract class BaseViewModel<N> extends AndroidViewModel {

    private static DataManager dataManager;

    private final ObservableBoolean isLoading = new ObservableBoolean(false);

    private static SchedulerProvider schedulerProvider;

    private CompositeDisposable compositeDisposable;

    private WeakReference<N> navigator;

    public BaseViewModel(Application context) {
        super(context);
        if (dataManager == null && schedulerProvider == null) {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppConstants.DB_NAME).fallbackToDestructiveMigration().build();
            DbHelper dbHelper = new AppDbHelper(db);
            AppPreferencesHelper preferenceHelper = new AppPreferencesHelper(context, AppConstants.PREF_NAME);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            AppApiHelper appService = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(AppApiHelper.class);
            DataManager dataManager = new AppDataManager(context, dbHelper, preferenceHelper, appService, gson);
            SchedulerProvider schedulerProvider = new AppSchedulerProvider();
            BaseViewModel.dataManager = dataManager;
            BaseViewModel.schedulerProvider = schedulerProvider;
        }
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }
}
