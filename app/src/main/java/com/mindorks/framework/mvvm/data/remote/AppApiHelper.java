package com.mindorks.framework.mvvm.data.remote;

import com.mindorks.framework.mvvm.data.model.api.BookResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface AppApiHelper {
    @GET("/bookList.json")
    Single<BookResponse> getBookList();
}
