package com.aglhz.s1.linkage.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.SceneBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/8/28 0028 10:28.
 * Email: liujia95me@126.com
 */

public interface AddLinkageContract  {

    interface View extends BaseContract.View {
        void responseAddSuccess(BaseBean bean);
        void responseSceneList(SceneBean bean);
        void responseDeviceList(List<DeviceListBean.DataBean.SubDevicesBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestNewLinkage(Params params);
        void requestSceneList(Params params);
        void requestDeviceList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestNewLinkage(Params param);
        Observable<SceneBean> requestSceneList(Params params);
        Observable<DeviceListBean> requestDeviceList(Params params);
    }
}
