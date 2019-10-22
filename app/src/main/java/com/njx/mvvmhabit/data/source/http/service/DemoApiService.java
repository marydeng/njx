package com.njx.mvvmhabit.data.source.http.service;

import com.njx.mvvmhabit.entity.BackDepotResultEntity;
import com.njx.mvvmhabit.entity.BackRecordResultEntity;
import com.njx.mvvmhabit.entity.BusiLocationEntity;
import com.njx.mvvmhabit.entity.BusiOrderResultEntity;
import com.njx.mvvmhabit.entity.BusinessOutRecordEntity;
import com.njx.mvvmhabit.entity.BusinessStorageRecordEntity;
import com.njx.mvvmhabit.entity.DemoEntity;
import com.njx.mvvmhabit.entity.MenuEntity;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.OrderListEntity;
import com.njx.mvvmhabit.entity.OutOrderEntity;
import com.njx.mvvmhabit.entity.OutPartRecordEntity;
import com.njx.mvvmhabit.entity.ReturnDataEntity;
import com.njx.mvvmhabit.entity.SMTRecordEntity;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
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
    Observable<BaseResponse<String>> commitProduceCode(@Query("checkResult") String testType, @Query("productBarcode") String produceCode, @Query("operator") String operator);

    //SMT 清除工单
    @POST("api/production/clearLoadMaterial")
    Observable<BaseResponse<Object>> clearWorkItem(@Query("workorderNumber") String workItem, @Query("loadStatus") String loadStatus);

    //SMT 检查状态
    @POST("api/production/checkStatus")
    Observable<BaseResponse<Object>> checkSMTStatus(@Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation);

    //SMT 上传上料扫码记录
    @POST("api/production/insertLoadmaterial")
    Observable<BaseResponse<Object>> uploadScanRecord(@Query("loadType") String loadType, @Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);

    //SMT 上传接料扫码记录
    @POST("api/production/ReLoadmaterial")
    Observable<BaseResponse<Object>> uploadMaterChangeScanRecord(@Query("loadType") String loadType, @Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialRollNew") String materialRollNew, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);

    //SMT 上传料枪变更扫码记录
    @POST("api/production/changeMaterialRack")
    Observable<BaseResponse<Object>> uploadGunChangeScanRecord(@Query("loadType") String loadType, @Query("loadStatus") String loadStatus, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRackNew") String materialGunNew, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);

    //SMT查询扫码记录
    @POST("api/production/selectLoadMaterial")
    Observable<BaseResponse<List<SMTRecordEntity>>> queryScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem);

    //SMT 确认完毕
    @POST("api/production/comfirmLoadmaterial")
    Observable<BaseResponse<List<String>>> confirmEnd(@Query("loadStatus") String loadType, @Query("workorderNumber") String workItem);

    //查询工单列表
    @POST("api/production/selectWorkorderByPresetLine")
    Observable<BaseResponse<OrderListEntity>> queryWorkItem(@Query("presetLine") String lineClass, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);


    //质检 上传扫码记录
    @POST("api/production/insertPqc")
    Observable<BaseResponse<Object>> uploadQualityScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem, @Query("materialRack") String materialGun, @Query("materialRoll") String materialRoll, @Query("materialStation") String materialStation, @Query("loadPeople") String loadPeople);

    //质检 查询扫码记录
    @POST("api/production/selectPqc")
    Observable<BaseResponse<List<SMTRecordEntity>>> queryQualityScanRecord(@Query("loadType") String loadType, @Query("workorderNumber") String workItem);

    //SMT input
    @POST("api/production/addSmtInput")
    Observable<BaseResponse<String>> addSMTInput(@Query("remark") String orderID, @Query("operator") String operator, @Query("productBarcode") String productBarcode, @Query("lastWorkOrder") String lastWorkOrder);

    //************仓库开始***************
    //出库订单查询
    @POST("api/ckMaterialOutputTable/selectWorkorderNumberList")
    Observable<BaseResponse<List<OutOrderEntity>>> queryOutOrder();

    //查询出库记录列表
    @POST("api/ckMaterialOutputTable/selectCkMaterialOutputTableList")
    Observable<BaseResponse<List<OutPartRecordEntity>>> queryOutList(@Query("workorderNumber") String workorderNumber, @Query("warehouseName") String warehouseName);

    //出库扫码
    @POST("api/ckMaterialOutputTable/addCkScanningOutRecordTable")
    Observable<BaseResponse<String>> outScan(@Query("workorderNumber") String workorderNumber, @Query("materialCoilNum") String materialCoilNum, @Query("confirmPerson") String confirmPerson, @Query("coilNum") String coilNum);

    //调拨转仓单号模糊查询
    @POST("api/ckTransferWareRecord/selectLikeTransferWareNum")
    Observable<BaseResponse<List<String>>> queryTransferOrder(@Query("transferWareNum") String transferWareNum);

    //根据转仓单号查询目标仓库
    @POST("api/ckTransferWareRecord/selectTargetWarehouseNameByTransferWareNum")
    Observable<BaseResponse<List<String>>> queryTransferDepat(@Query("transferWareNum") String transferWareNum);

    //查询调拨记录
    @POST("api/ckTransferWareRecord/transferWareRecordList")
    Observable<BaseResponse<List<TransferPartRecordEntity>>> queryTransferList(@Query("transferWareNum") String transferWareNum,@Query("targetWarehouseName") String targetWarehouseName);

    //根据仓库查询储位列表
    @POST("api/ckTransferWareRecord/selectCkLocationListByCkWarehouse")
    Observable<BaseResponse<List<String>>> queryTransferLocation(@Query("ckWarehouse") String ckWarehouse);

    //调拨扫码
    @POST("api/ckTransferWareRecord/scanSubinventoryTransfer")
    Observable<BaseResponse<String>> transferScan(@Query("transferWareNum") String transferWareNum, @Query("targetWarehouseName") String targetWarehouseName, @Query("locationNo") String locationNo, @Query("materialCoilNum") String materialCoilNum);

    //查询退货订单
    @POST("api/ckReturnOrder/selectCgReturnNoteList")
    Observable<BaseResponse<List<String>>> queryReturnOrderList();

    //查询退货记录
    @POST("api/ckReturnOrder/ckReturnOrderList")
    Observable<BaseResponse<ReturnDataEntity>> queryReturnRecordList(@Query("correlationOrder") String correlationOrder);

    //退货扫码
    @POST("api/ckReturnOrder/addCkReturnOrder")
    Observable<BaseResponse<String>> returnScan(@Query("correlationOrder") String correlationOrder, @Query("materialCoilNum") String materialCoilNum);

    //查询退库订单
    @POST("api/ckWithdrawalForm/selectCorrelationOrderList")
    Observable<BaseResponse<List<String>>> queryBackOrderList();

    //查询退库仓库
    @POST("api/ckWithdrawalForm/ckWarehouseList")
    Observable<BaseResponse<BackDepotResultEntity>> queryBackDepot(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    //查询退库储位
    @POST("api/ckWithdrawalForm/selectCkLocationListByCkWarehouse")
    Observable<BaseResponse<List<String>>> queryBackLocation(@Query("ckWarehouse") String ckWarehouse);

    //查询退库记录
    @POST("api/ckWithdrawalForm/ckWithdrawalFormlist")
    Observable<BaseResponse<BackRecordResultEntity>> queryBackRecordList(@Query("correlationOrder") String correlationOrder);

    //退库扫码
    @POST("api/ckWithdrawalForm/saveCkWithdrawalForm")
    Observable<BaseResponse<String>> backScan(@Query("correlationOrder") String correlationOrder, @Query("materialCoilNum") String materialCoilNum,@Query("materialNo") String materialNo, @Query("warehouseName") String warehouseName, @Query("locationNo") String locationNo);
    // ************仓库结束***************

    // ************成品开始***************
    //查询成品库位
    @POST("api/ckMaterialOutputTable/selectProductWareHouseTable")
    Observable<BaseResponse<List<BusiLocationEntity>>> queryBusinessLocation();

    //成品入库扫码
    @POST("api/ckMaterialOutputTable/addProductInputTable")
    Observable<BaseResponse<String>> businessStorageScan(@Query("productLocation") String locationId, @Query("boxBarcode") String boxId);

   //成品入库记录
   @POST("api/ckMaterialOutputTable/selectProductInputTable")
   Observable<BaseResponse<List<BusinessStorageRecordEntity>>> queryBusinessStorageRecord();


   //成品出库单号查询
   @POST("api/ckMaterialOutputTable/selectProductOut")
   Observable<BaseResponse<BusiOrderResultEntity>> queryBusinessOutOrder(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

   //成品出库扫码
   @POST("api/ckMaterialOutputTable/addProductOutDetailTable")
   Observable<BaseResponse<String>> businessOutScan(@Query("outNumber") String orderId,@Query("boxBarcode") String boxId);

    //成品出库库记录
    @POST("api/ckMaterialOutputTable/selectProductOutDetailTable")
    Observable<BaseResponse<List<BusinessOutRecordEntity>>> queryBusinessOutRecord(@Query("outNumber") String orderId);

    // ************成品结束***************

}
