package com.example.perpuskita;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    //BookAPI
    @GET("api/android/book")
    Call<BaseResponse<Book>> callBook(@Query("token") String token, @Header("Authorization") String auth);

    @POST("/api/android/borrowbook")
    Call<BaseResponse<Book>> confirmBorrowBook(@Header("Content-Type") String contentType, @Header("Authorization") String auth, @Body Book data);

    @POST("/api/android/returnbook")
    Call<BaseResponse<Book>> confirmReturnBook(@Header("Content-Type") String contentType, @Header("Authorization") String auth, @Body Book data);

    //MemberAPI


}
