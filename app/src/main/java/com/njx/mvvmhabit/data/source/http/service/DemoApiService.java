package com.njx.mvvmhabit.data.source.http.service;

import com.njx.mvvmhabit.entity.DemoEntity;
import com.njx.mvvmhabit.entity.MenuEntity;
import com.njx.mvvmhabit.entity.MenuListEntity;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.OrderListEntity;
import com.njx.mvvmhabit.entity.SMTRecordEntity;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.entity.UserEntity;

import java.util.List;

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
    Observable<BaseResponse<UserEntity>> login(@Query("userName") String username, @Query("password") String password);

    //菜单
    @GET("api/user/getMenuList")
    Observable<BaseResponse<List<MenuEntity>>> getMenuList(@Query("userId") String userId);

    //上机台
    @POST("api/production/edit")
    Observable<BaseResponse<DemoEntity>> uplineSteelPlate(@Query("partNum") String partNum, @Query("lineClass") String lineClass, @Query("steelPlateType") String steelPlateType, @Query("jobNum") String jobNum);


    //下机台
    @POST("api/production/leaveEditSave")
    Observable<BaseResponse<DemoEntity>> downlineSteelPlate(@Query("lineClass") String lineClass, @Query("steelPlateType") String steelPlateType);

    //根据工单号查询线体
    @POST("api/production/selectWorkorderList")
    Observable<BaseResponse<OrderEntity>> queryOrder(@Query("workorderNumber") String workorderNumber);

    //获取在线钢板和刮刀
    @POST("api/production/scSteelPlateUplineList")
    Observable<BaseResponse<List<SteelEntity>>> getSteelList(@Query("lineClass") String lineClass);

    //查询所有线体
    @POST("api/production/selectScProductLineList")
    Observable<BaseResponse<List<String>>> queryAllLine();

    //fqc 上传生产条码
    @POST("api/fqc/add")
    Observable<BaseResponse<String>> commitProduceCode(@Query("palletNumber") String zhanbanId, @Query("number") String num, @Query("checkResult") String testType, @Query("productBarcode") String produceCode, @Query("operator") String operator);

    //SMT 清除工单 Todo
    @POST("api/production/clearLoadMaterial")
    Observable<BaseResponse<Object>> clearWorkItem(@Query("workorderNumber") String workItem,@Query("loadStatus") String loadStatus);

    //SMT 上传上料扫码记录
    @POST("api/production/insertLoadmaterial")
    Observable<BaseResponse<Object>> uploadScanRecord(@Query("loadType") String loadType,@Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);
    //SMT 上传接料扫码记录
    @POST("api/production/insertLoadmaterial")
    Observable<BaseResponse<Object>> uploadMaterChangeScanRecord(@Query("loadType") String loadType,@Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll,@Query("materialRollNew") String materialRollNew, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);
    //SMT 上传料枪变更扫码记录
    @POST("api/production/changeMaterialRack")
    Observable<BaseResponse<Object>> uploadGunChangeScanRecord(@Query("loadType") String loadType,@Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun,@Query("materialRackNew") String materialGunNew, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);
    //SMT查询扫码记录
    @POST("api/production/selectLoadMaterial")
    Observable<BaseResponse<List<SMTRecordEntity>>> queryScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem);

    //SMT 确认完毕
    @POST("api/production/comfirmLoadmaterial")
    Observable<BaseResponse<List<String>>> confirmEnd(@Query("loadType") String loadType,@Query("workorderNumber") String workItem);

    //查询工单列表
    @POST("api/production/selectWorkorderByPresetLine")
    Observable<BaseResponse<OrderListEntity>> queryWorkItem(@Query("presetLine") String lineClass,@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);


    //质检 上传扫码记录 //Todo
    @POST("api/production/insertPqc")
    Observable<BaseResponse<Object>> uploadQualityScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);

    //质检 查询扫码记录 Todo
    @POST("api/production/selectPqc")
    Observable<BaseResponse<List<SMTRecordEntity>>> queryQualityScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem);

    //SMT input
    @POST("api/production/addSmtInput")
    Observable<BaseResponse<String>> addSMTInput(@Query("remark") String orderID, @Query("operator ") String operator , @Query("productBarcode ") String productBarcode );
}
