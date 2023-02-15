package com.example.asm_networking.ASM.Constant.Retrofits;

import com.example.asm_networking.ASM.Constant.Requests.NewProductRequest;
import com.example.asm_networking.ASM.Constant.Requests.RegisterRequest;
import com.example.asm_networking.ASM.Constant.Requests.UpdateProductRequest;
import com.example.asm_networking.ASM.Constant.Requests.UploadRequest;
import com.example.asm_networking.ASM.Constant.Respone.NewProductResponse;
import com.example.asm_networking.ASM.Constant.Respone.RegisterResponse;
import com.example.asm_networking.ASM.Constant.Respone.UpdateProductResponse;
import com.example.asm_networking.ASM.Constant.Respone.UploadResponse;
import com.example.asm_networking.ASM.Models.Category;
import com.example.asm_networking.ASM.Models.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface iRetrofitService {

    @POST("/views/user-register.php")
    Call<RegisterResponse> register(@Body RegisterRequest body);

    @POST("/views/user-login.php")
    Call<RegisterResponse> login(@Body RegisterRequest body);

    @GET("/views/get-all-product.php")
    Call<List<Product>> getAllProduct();

    @GET("/views/get-categories.php")
    Call<List<Category>> getCategories();

    @GET("/views/get-one-category.php")
    Call<List<Category>> getOneCategory(@Query("id") Integer id);

    @POST("/views/upload.php")
    Call<UploadResponse> upload(@Body UploadRequest body);

    @POST("/views/insert-product.php")
    Call<NewProductResponse> insertProduct(@Body NewProductRequest body);

    @POST("/views/update-product.php")
    Call<UpdateProductResponse> updateProduct(@Body UpdateProductRequest body);

    @GET("/views/delete-product.php")
    Call<RegisterResponse> deleteProduct(@Query("id") Integer id);

    @GET("/views/forgot-password.php")
    Call<RegisterResponse> forgotPassword(@Query("email") String email);
}
