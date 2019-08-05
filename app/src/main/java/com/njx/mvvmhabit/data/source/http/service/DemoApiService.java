package com.njx.mvvmhabit.data.source.http.service;

import com.njx.mvvmhabit.entity.DemoEntity;
import com.njx.mvvmhabit.entity.MenuListEntity;
import com.njx.mvvmhabit.entity.UserEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by goldze on 2017/6/15.
 */

public interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<BaseResponse<DemoEntity>> demoPost(@Field("catalog") String catalog);

    //登录
    @GET("api/login/loginByUserName")
    Observable<BaseResponse<UserEntity>> login(@Query("userName")String username, @Query("password")String password);

    //菜单
    @GET("api/user/getMenuList")
    Observable<BaseResponse<MenuListEntity>> getMenuList(@Query("userId")String userId);

    //上机台
    @POST("api/production/edit")
    Observable<BaseResponse<DemoEntity>> uplineSteelPlate(@Query("partNum")String partNum,@Query("lineClass")String lineClass,@Query("steelPlateType")String steelPlateType);


    //下机台
    @POST("api/production/leaveEditSave")
    Observable<BaseResponse<DemoEntity>> downlineSteelPlate(@Query("partNum")String partNum,@Query("lineClass")String lineClass,@Query("steelPlateType")String steelPlateType);
}
