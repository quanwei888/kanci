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

package com.kanci.data;

import android.content.Context;

import com.google.gson.Gson;
import com.kanci.data.local.db.DbHelper;
import com.kanci.data.local.prefs.PreferencesHelper;
import com.kanci.data.model.api.BookResponse;
import com.kanci.data.model.db.Book;
import com.kanci.data.remote.AppApiHelper;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */
public class AppDataManager implements DataManager {

    private final AppApiHelper apiHelper;

    private final Context context;

    private final DbHelper dbHelper;

    private final Gson gson;

    private final PreferencesHelper preferencesHelper;

    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, AppApiHelper apiHelper, Gson gson) {
        this.context = context;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
        this.gson = gson;
    }

    @Override
    public Single<BookResponse> getBookList() {
        return apiHelper.getBookList();
    }

    public int getUserId() {
        return preferencesHelper.getUserId();
    }

    public String getUserName() {
        return preferencesHelper.getUserName();
    }

    public String getUserPic() {
        return preferencesHelper.getUserPic();
    }

    public Observable<List<Book>> getAllBooks() {
        return dbHelper.getAllBooks();
    }
}
