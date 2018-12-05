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

package com.mindorks.framework.mvvm.data.model.api;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by amitshekhar on 07/07/17.
 */

public class BookListResponse {

    @Expose private List<Item> data;
    @Expose private String message;
    @Expose private String statusCode;

    public static class Item {
        @Expose public int bid;
        @Expose public String name;
        @Expose public String pic;
        @Expose public String desc;
        @Expose public String tag;
        @Expose public String userStatus;
        @Expose public int wordCount;
        @Expose public boolean offline;
    }
}
