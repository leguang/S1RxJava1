package com.aglhz.s1.scene.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.widget.PtrHTFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.statemanager.StateManager;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 场景模块。
 */

public class AddSceneFragment extends BaseFragment {
    public static final String TAG = AddSceneFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    @BindView(R.id.et_name_add_scene_fragment)
    EditText etName;
    @BindView(R.id.ll_add_device_add_scene_fragment)
    LinearLayout llAddDevice;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private AddSceneRVAdapter adapter;
    private StateManager mStateManager;

    public static AddSceneFragment newInstance() {
        return new AddSceneFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_scene, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initStateManager();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("场景编辑");
        toolbarMenu.setText("确定");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new AddSceneRVAdapter();
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyText("请点击添加设备！")
                .setEmptyOnClickListener(v -> {
                    // TODO: 2017/8/24 0024

                })
                .build();
        mStateManager.showEmpty();
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.toolbar_menu, R.id.ll_add_device_add_scene_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_menu:

                break;
            case R.id.ll_add_device_add_scene_fragment:
                start(DeviceListFragment.newInstance());
                break;
        }
    }
}
