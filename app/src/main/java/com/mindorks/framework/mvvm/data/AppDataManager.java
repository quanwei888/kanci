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

package com.mindorks.framework.mvvm.data;

import android.content.Context;

import com.google.gson.Gson;
import com.mindorks.framework.mvvm.data.local.db.DbHelper;
import com.mindorks.framework.mvvm.data.local.prefs.PreferencesHelper;
import com.mindorks.framework.mvvm.data.model.api.BookListRequest;
import com.mindorks.framework.mvvm.data.model.api.BookListResponse;
import com.mindorks.framework.mvvm.data.model.db.Book;
import com.mindorks.framework.mvvm.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Single<BookListResponse> getBookListCall(BookListRequest request) {
        return mApiHelper.getBookListCall(request);
    }

    @Override
    public int getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public String getUserName() {
        return mPreferencesHelper.getUserName();
    }

    @Override
    public String getUserPic() {
        return mPreferencesHelper.getUserPic();
    }

    @Override
    public Observable<List<Book>> getAllBooks() {
        return mDbHelper.getAllBooks();
    }
}