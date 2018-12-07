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

package com.kanci;

import com.kanci.data.model.api.BookResponse;
import com.kanci.data.remote.AppApiHelper;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static class User {
        public int id;
        public String userName;
        public List<Integer> tag;
        public List<Book> book;
    }

    public static class Book {
        public String bookName;
    }

    public static interface UserService {

        @GET("/bookList.php")
        public Observable<User> getUser();
    }

    @Test
    public void f() throws InterruptedException {
        AppApiHelper appService = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(AppApiHelper.class);
        appService.getBookList()
                .doOnSubscribe(o -> {
                    System.out.println(Thread.currentThread().getId() + "doOnSubscribe");
                })
                .observeOn(Schedulers.computation())
                .subscribe(new SingleObserver<BookResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("BEGIN");
                        System.out.println(Thread.currentThread().getId());
                    }

                    @Override
                    public void onSuccess(BookResponse bookResponse) {
                        System.out.println("OK");
                        System.out.println(Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(1111);
        Thread.sleep(5000);
    }
}