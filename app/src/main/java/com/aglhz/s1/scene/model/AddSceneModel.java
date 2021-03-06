package com.aglhz.s1.scene.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.scene.contract.AddSceneContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AddSceneModel extends BaseModel implements AddSceneContract.Model {


    @Override
    public Observable<BaseBean> requestAddScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestAddScene(ApiService.requestAddScene,
                        params.token,
                        params.name,
                        params.paramJson)
                .subscribeOn(Schedulers.io());
    }
}