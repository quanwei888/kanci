package com.example.testjava;

public class MyClass {
/*

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

        @GET("/user.json")
        public Observable<User> getUser();
    }

    public static void main(String[] args) throws InterruptedException {
        //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        service.getUser()
                .subscribeOn(Schedulers.newThread())
                .subscribe((user) -> {
                            //Thread.sleep(1000);
                            System.out.println(user.userName);
                        }, throwable -> {
                            throwable.printStackTrace();
                        }
                );
        System.out.println(1111);
        Thread.sleep(5000);
    }
    */
}
