package com.aglhz.s1.history.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.DeviceLogBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface DeviceLogsContract {

    interface View extends BaseContract.View {
        void responseDeviceLogs(List<DeviceLogBean.DataBean.LogsBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDeviceLogs(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<DeviceLogBean> requestDeviceLogs(Params params);
    }
}