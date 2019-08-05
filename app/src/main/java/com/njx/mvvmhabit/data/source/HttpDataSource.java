package com.njx.mvvmhabit.data.source;

import com.njx.mvvmhabit.entity.DemoEntity;
import com.njx.mvvmhabit.entity.MenuListEntity;
import com.njx.mvvmhabit.entity.UserEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);

    Observable<BaseResponse<UserEntity>> login(String username, String password);

    Observable<BaseResponse<MenuListEntity>> getMenuList(String userId);

    Observable<BaseResponse<DemoEntity>> uplineSteelPlate(String partNum, String lineClass, String steelPlateType);

    Observable<BaseResponse<DemoEntity>> downlineSteelPlate(String partNum, String lineClass, String steelPlateType);


}
