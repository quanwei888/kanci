package com.kanci.data.remote;

import com.kanci.data.model.api.BookResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface AppApiHelper {
    @GET("/bookList.php")
    Single<BookResponse> getBookList();
}
