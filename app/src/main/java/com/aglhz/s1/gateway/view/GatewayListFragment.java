package com.aglhz.s1.gateway.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.gateway.contract.GatewayListContract;
import com.aglhz.s1.gateway.presenter.GatewayListPresenter;
import com.aglhz.s1.host.view.AddHostFragment;
import com.aglhz.s1.host.view.HostSettingsFragment;
import com.aglhz.s1.qrcode.ScanQRCodeFragment;
import com.aglhz.s1.scene.view.AddSceneFragment;
import com.aglhz.s1.widget.PtrHTFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.Decoration;
import cn.itsite.adialog.dialogfragment.SelectorDialogFragment;
import cn.itsite.statemanager.StateLayout;
import cn.itsite.statemanager.StateManager;

public class GatewayListFragment extends BaseFragment<GatewayListContract.Presenter> implements GatewayListContract.View {
    public static final String TAG = GatewayListFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    private GatewayListRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private StateManager mStateManager;
    private List<String> addHostTypes;

    public static GatewayListFragment newInstance() {
        return new GatewayListFragment();
    }

    @NonNull
    @Override
    protected GatewayListContract.Presenter createPresenter() {
        return new GatewayListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("管理主机");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new Decoration(_mActivity, Decoration.VERTICAL_LIST));
        adapter = new GatewayListRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestGateways(params);
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyText("还没有主机哦，赶紧添加吧！")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> showAddHostSelecotr())
                .setConvertListener((holder, stateLayout) ->
                        holder.setOnClickListener(R.id.bt_empty_state,
                                v -> _mActivity.start(AddSceneFragment.newInstance()))
                                .setText(R.id.bt_empty_state, "点击添加"))
                .build();
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position)
                -> start(HostSettingsFragment.newInstance(adapter.getData().get(position))));
    }

    private void showAddHostSelecotr() {
        if (addHostTypes == null) {
            addHostTypes = new ArrayList<>();
            addHostTypes.add(0, "扫码添加");
            addHostTypes.add(1, "手动输入添加");
        }
        new SelectorDialogFragment()
                .setTitle("请选择添加方式")
                .setItemLayoutId(R.layout.item_rv_simple_selector)
                .setData(addHostTypes)
                .setOnItemConvertListener((holder, position, dialog) ->
                        holder.setText(R.id.tv_item_rv_simple_selector, addHostTypes.get(position)))
                .setOnItemClickListener((view, baseViewHolder, position, dialog) -> {
                    dialog.dismiss();
                    switch (position) {
                        case 0:
                            _mActivity.start(ScanQRCodeFragment.newInstance());
                            break;
                        case 1:
                            _mActivity.start(AddHostFragment.newInstance("", null));
                            break;
                    }
                })
                .setAnimStyle(R.style.SlideAnimation)
                .setGravity(Gravity.BOTTOM)
                .show(getChildFragmentManager());
    }

    @Override
    public void onRefresh() {
        params.pageSize = 10;
        params.page = 1;
        mPresenter.requestGateways(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        super.error(errorMessage);
        ptrFrameLayout.refreshComplete();
        if (params.page == 1) {
            mStateManager.showError();
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
    }

    @Override
    public void responseGateways(List<GatewaysBean.DataBean> data) {
//        if (params.page == 1) {
//            adapter.setNewData(data);
//        } else {
//            adapter.addData(data);
//        }
//        if (data.size() < params.pageSize) {
//            adapter.loadMoreEnd(true);
//        } else {
//            adapter.loadMoreComplete();
//        }
//        ptrFrameLayout.refreshComplete();
        ptrFrameLayout.refreshComplete();
        if (data == null || data.isEmpty()) {
            if (params.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }
        if (params.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(data);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(data);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }
}