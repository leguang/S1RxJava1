package com.aglhz.s1.security.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.DetectorPropertyContract;
import com.aglhz.s1.security.model.DetectorPropertyModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/7/4 0004 15:12.
 * Email: liujia95me@126.com
 */

public class DetectorPropertyPresenter extends BasePresenter<DetectorPropertyContract.View,DetectorPropertyContract.Model> implements DetectorPropertyContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public DetectorPropertyPresenter(DetectorPropertyContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DetectorPropertyContract.Model createModel() {
        return new DetectorPropertyModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestDetectorProperty(Params params) {
        mRxManager.add(mModel.requestDetectorProperty(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    //todo:update
                }, this::error));
    }

    @Override
    public void requestNotifProperty(Params params) {

    }
}
