package com.aglhz.s1.settings.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SettingsBean;
import com.aglhz.s1.settings.HostSettingsRVAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/5/2 0002 20:14.
 * Email: liujia95me@126.com
 */

public class HostSettingsFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private HostSettingsRVAdapter adapter;

    public static HostSettingsFragment newInstance() {
        return new HostSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("主机设置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        List<SettingsBean> settingsBeans = new ArrayList<>();
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new HostSettingsRVAdapter(settingsBeans);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        start(SetWifiFragment.newInstance());
                        break;
                    case 2:
                        start(AddHostFragment.newInstance());
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
