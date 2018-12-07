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

package com.mindorks.framework.mvvm.ui.login;

import android.content.Context;
import android.widget.Toast;

import com.mindorks.framework.mvvm.data.model.api.BookResponse;
import com.mindorks.framework.mvvm.ui.base.BaseViewModel;

import org.reactivestreams.Subscriber;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by amitshekhar on 08/07/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(Context context) {
        super(context);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        return true;
    }

    public void login(String email, String password) {
        getDataManager().getBookList()
                .subscribeOn(getSchedulerProvider().io())
                .doOnSubscribe(o -> setIsLoading(true))
                .doFinally(() -> setIsLoading(false))
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        bookResponse -> {
                            System.out.println(bookResponse);
                        },
                        e -> {
                            getNavigator().handleError(e);
                            e.printStackTrace();
                        });
    }

    public void onFbLoginClick() {
        setIsLoading(true);
    }

    public void onGoogleLoginClick() {
        setIsLoading(true);
    }

    public void onServerLoginClick() {
        getNavigator().login();
    }
}
