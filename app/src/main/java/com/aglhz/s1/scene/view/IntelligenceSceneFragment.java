package com.aglhz.s1.scene.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.SceneBean;
import com.aglhz.s1.scene.SceneListRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 14:41.
 * Email: liujia95me@126.com
 */

public class IntelligenceSceneFragment extends SupportFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private SceneListRVAdapter adapter;

    public static IntelligenceSceneFragment newInstance() {
        return new IntelligenceSceneFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intelligence_scene, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();

    }

    private void initData() {
        List<SceneBean> sceneBeans = new ArrayList<>();
        for (int i = 0;i<100;i++){
            sceneBeans.add(new SceneBean("起床"+i, true));
        }
        adapter = new SceneListRVAdapter(sceneBeans);
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_delete_item_scene:
                    adapter.remove(position);
                    break;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
